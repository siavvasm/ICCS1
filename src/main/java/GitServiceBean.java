/**
 * 
 */


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */

@Component
@Scope("prototype")
public class GitServiceBean implements GitService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected File directory;
	protected Git git;
	protected String uri;

	protected String username;
	protected String password;
	protected String oauth;
	protected CredentialsProvider cp;

	public GitServiceBean() { }
	//		this.cp = new UsernamePasswordCredentialsProvider("ma-los-sis@hotmail.com", "P3uHwNuQ9G1F1yd5FDUN");
	//	}

	public GitServiceBean(File directory) {
		this.directory = directory;
		//		this.cp = new UsernamePasswordCredentialsProvider("ma-los-sis@hotmail.com", "P3uHwNuQ9G1F1yd5FDUN");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.digkas.softwareanalysis.service.jgit.GitService#gitClone(java.lang.
	 * String)
	 */
	@Override
	public Git gitClone(String uri) {
		logger.info("gitClone uri: {}", uri);

		this.uri = uri;

		deleteGitRepoDirectory();
		createGitRepoDirectory();

		try {
			git = Git
					.cloneRepository()
					.setURI(uri)
					.setDirectory(directory)
					.setCredentialsProvider(cp)
					.call();
			return git;
		} catch (GitAPIException e) {
			e.printStackTrace();
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.jgit.GitService#gitCloneBranch(java.lang.String, java.lang.String)
	 */
	@Override
	public Git gitCloneBranch(String uri, String branch) {
		logger.info("gitCloneBranch uri: {}, branch: {}", uri, branch);
		deleteGitRepoDirectory();
		createGitRepoDirectory();
		try {
			git = Git.cloneRepository()
					.setURI(uri)
					.setDirectory(directory)
					.setCredentialsProvider(cp)
					//.setBranchesToClone(Arrays.asList(branch))
					.setBranch(branch)
					.call();
			return git;
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Git gitCloneAllBranches(String uri) {
		logger.info("gitClone uri: {}", uri);
		deleteGitRepoDirectory();
		createGitRepoDirectory();
		try {
			git = Git.cloneRepository().setURI(uri).setDirectory(directory).setCredentialsProvider(cp).setCloneAllBranches(true).call();
			return git;
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.jgit.GitService#getAllCommitsIterable()
	 */
	@Override
	public Iterable<RevCommit> getAllCommitsIterable() {
		logger.info("> getAllCommitsIterable");

		Iterable<RevCommit> revCommits = null;
		try {
			revCommits = git.log().call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("< getAllCommitsIterable");
		return revCommits;
	}

	@Override
	public List<RevCommit> getRevCommitsList() {
		logger.info("> getRevCommitsList");

		List<RevCommit> revCommits = Lists.newArrayList(this.getAllCommitsIterable());

		logger.info("< getRevCommitsList");
		return revCommits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.jgit.GitService#gitCheckout(java.lang.
	 * String)
	 */
	@Override
	public Ref gitCheckout(String sha) {
		logger.info("gitCheckout sha: {}", sha);
		try {
			return git.checkout().setName(sha).call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.digkas.softwareanalysis.service.jgit.GitService#gitCheckout(org.eclipse.
	 * jgit.revwalk.RevCommit)
	 */
	@Override
	public Ref gitCheckout(RevCommit revCommit) {
		logger.info("gitCheckout revCommit: {}", revCommit);
		return gitCheckout(revCommit.getName());
	}

	public boolean deleteGitRepoDirectory() {
		logger.info("deleteGitRepoDirectory: {}", directory);
		return FileUtils.deleteQuietly(directory);
	}

	private void createGitRepoDirectory() {
		if (!directory.exists())
			if (directory.mkdirs())
				logger.info(directory.getAbsolutePath() + " created!");
			else
				logger.error(directory.getAbsolutePath() + " fail to be created..");
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.jgit.GitService#getBranchList()
	 */
	@Override
	public List<Ref> getBranchList() {
		logger.info("getBranchList git: {}", git);

		ifRepositoryExistsOpenAndPullOtherwiseClone();

		try {
			return git
					.branchList()
					.setListMode(ListMode.ALL)
					.call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void ifRepositoryExistsOpenAndPullOtherwiseClone() {

		//Check if Directory Exists and open it
		if (Files.exists(getDirectory().toPath())) {
			logger.info("Directory: {}, DOES EXIST, so open and pull", getDirectory().toPath());
			try {
				logger.info("Open Git Repository: {}", getDirectory());
				this.git = Git.open(getDirectory());
				logger.info("Pull");
				this.git.pull().setCredentialsProvider(cp).call();
			} catch (IOException | GitAPIException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//Clone
		else {
			logger.info("Directory: {}, DOES NOT EXIST, so clone", getDirectory().toPath());
			this.gitCloneAllBranches(this.uri);
		}
	}

	/* (non-Javadoc)
	 * @see com.digkas.softwareanalysis.service.jgit.GitService#getBranchNamesSet()
	 */
	@Override
	public Set<String> getBranchNamesSet() {
		return this.getBranchList()
				.stream()
				.map(ref -> ref.getName().replace("refs/remotes/origin/", "").replace("refs/heads/", ""))
				.collect(Collectors.toSet());
	}

	@Override
	public List<Ref> getTagsRefList() {

		try (Git newGitRepository = new Git(git.getRepository())) {
			try {
				return newGitRepository.tagList().call();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Set<String> getTaggedCommitsSet() {
		return this.getTagsRefList().stream().map(t -> t.getObjectId().getName()).collect(Collectors.toSet());
	}

	public File getDirectory() {
		return directory;
	}

	public GitService setDirectory(File directory) {
		logger.info("> setDirectory: {}", directory);
		this.directory = directory;
		logger.info("< setDirectory: {}", directory);
		return this;
	}

	public Git getGit() {
		return git;
	}

	public void setGit(Git git) {
		this.git = git;
	}

	public CredentialsProvider getCp() {
		return cp;
	}

	@Override
	public RevCommit getRevCommitBySHA(String sha) {
		List<RevCommit> rcs = this.getRevCommitsList();
		for (RevCommit revCommit : rcs)
			if (Objects.equals(revCommit.getName(), sha))
				return revCommit;
		return null;
	}

	//	public GitService setCp(String username, String password) {
	//		if (!username.isEmpty() && !password.isEmpty()) {
	//			this.username = username;
	//			this.password = password;
	//		}
	//		else if (!username.isEmpty() && password.isEmpty()) {
	//			this.oauth = username;
	//			this.password = "";
	//		}
	//
	//		this.cp = new UsernamePasswordCredentialsProvider(username, password);
	//		return this;
	//	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public GitServiceBean setUsernamePassword(String username, String password) {
		this.username = username;
		this.password = password;

		this.cp = new UsernamePasswordCredentialsProvider(username, password);

		return this;
	}

	public String getOauth() {
		return oauth;
	}

	public GitServiceBean setOauth(String oauth) {
		this.oauth = oauth;

		this.cp = new UsernamePasswordCredentialsProvider(oauth, "");

		return this;
	}

	public GitServiceBean setUri(String uri) {
		logger.info("> setUri: {}", uri);
		this.uri = uri;
		logger.info("< setUri: {}", uri);
		return this;
	}

	public String getUri() {
		return uri;
	}

}
