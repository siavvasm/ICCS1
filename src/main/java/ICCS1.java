/**
 * 
 */


import java.io.File;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.refactoringminer.api.GitService;
import org.refactoringminer.util.GitServiceImpl;

import com.google.common.collect.Lists;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
//@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@EnableConfigurationProperties
public class ICCS1 {

	private static final String GIT_OWNER = "apache";
	private static final String GIT_REPO = "commons-io";

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//		ApplicationContext ctx = SpringApplication.run(ICCS1.class, args);

		GitService gitService = new GitServiceImpl();
		Repository repo = gitService.cloneIfNotExists("tmp/" + GIT_REPO, "https://github.com/" + GIT_OWNER +"/"+ GIT_REPO);

		GitServiceBean gitServiceBean = new GitServiceBean();
		gitServiceBean.setGit(Git.open(new File("tmp/" + GIT_REPO)));
		gitServiceBean.setDirectory(new File("tmp/" + GIT_REPO));

		List<RevCommit> revCommits = null;
		try {
			revCommits = Lists.newArrayList(gitServiceBean.getGit().log().call());
			RevCommitsFilterICCS revCommitsFilter = new RevCommitsFilterICCS();

			revCommitsFilter
			.setFilterRevCommits(new FilterRevCommitsMavenProject())
			.setOwner(GIT_OWNER)
			.setRepository(GIT_REPO).setProjectKey(GIT_OWNER+":"+GIT_REPO)
			.setRevCommits(revCommits)
			.setGitServiceBean(gitServiceBean)
			.setGit(gitServiceBean.getGit());

			revCommits = revCommitsFilter.getRevCommitsForAnalysis();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		System.out.println(revCommits.size());
	}

}
