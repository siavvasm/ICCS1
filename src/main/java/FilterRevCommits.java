/**
 * 
 */


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public abstract class FilterRevCommits {

	protected List<RevCommit> revCommits;
	protected Map<String, RevCommit> revCommitsMap;
	protected RevCommit firstRevCommit;
	protected RevCommit lastRevCommit;
	protected GitServiceBean gitServiceBean;
	protected Git git;
	protected String language;

	public FilterRevCommits() {}

	public FilterRevCommits(GitServiceBean gitServiceBean, Git git, List<RevCommit> revCommits, RevCommit firstRevCommit, RevCommit lastRevCommit, String language) {
		this.gitServiceBean = gitServiceBean;
		this.git = git;
		this.revCommits = revCommits;
		this.revCommitsMap = this.revCommits.stream().collect(Collectors.toMap(RevCommit::getName, Function.identity()));
		this.firstRevCommit = firstRevCommit;
		this.lastRevCommit = lastRevCommit;
		this.language = language;
	}

	public FilterRevCommits(GitServiceBean gitServiceBean, Git git, List<RevCommit> revCommits, String language) {
		this(gitServiceBean, git, revCommits, revCommits.get(revCommits.size() - 1), revCommits.get(0), language);
	}

	public abstract List<RevCommit> getFilteredRevCommits();


	public FilterRevCommits setRevCommitsAndRevCommitsMap(List<RevCommit> revCommits) {
		this.revCommits = revCommits;
		revCommitsMap = revCommits.stream().collect(Collectors.toMap(RevCommit::getName, Function.identity()));
		return this;
	}

	public FilterRevCommits setGitServiceBean(GitServiceBean gitServiceBean) {
		this.gitServiceBean = gitServiceBean;
		return this;
	}

	public FilterRevCommits setGit(Git git) {
		this.git = git;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public FilterRevCommits setLanguage(String language) {
		this.language = language;
		return this;
	}

	protected RevCommit getParent(RevCommit[] parents, Map<String, RevCommit> revCommitsMap) {
		for (RevCommit revCommit : parents) {
			RevCommit rc = revCommitsMap.get(revCommit.getName());
			if (Objects.nonNull(rc))
				return rc;
		}
		return null;
	}

	protected List<DiffEntry> getDiffEntries(CanonicalTreeParser newCanonicalTreeParser, CanonicalTreeParser oldCanonicalTreeParser) {
		try {
			return git.diff().setOldTree(oldCanonicalTreeParser).setNewTree(newCanonicalTreeParser).setShowNameAndStatusOnly(true).call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected CanonicalTreeParser getCanonicalTreeParserFromRevCommit(Repository repository, RevCommit revCommit) {
		CanonicalTreeParser nCanonicalTreeParser = null;
		try (RevWalk walk = new RevWalk(repository)) {
			RevCommit commit = walk.parseCommit(revCommit);
			ObjectId treeId = commit.getTree().getId();
			try (ObjectReader reader = repository.newObjectReader()) {
				nCanonicalTreeParser = new CanonicalTreeParser(null, reader, treeId);
			}
		} catch (MissingObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectObjectTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCanonicalTreeParser;
	}

	protected boolean doesTheFirstCommitContainsFiles(Set<String> fileExtensions) {
		Path path = Paths.get(gitServiceBean.getDirectory().getAbsolutePath());
		for (String extension : fileExtensions) {
			try (Stream<Path> paths = Files.walk(path)) {
				if (!paths.filter(Files::isRegularFile).filter(p -> p.toAbsolutePath().toString().endsWith(extension)).collect(Collectors.toList()).isEmpty())
					return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	protected boolean diffEntriesContainFiles(List<DiffEntry> diffEntries, Set<String> fileExtensions) {
		for (String extension : fileExtensions)
			if (!diffEntries.stream().filter(diffEntry -> diffEntry.getNewPath().endsWith(extension) || diffEntry.getOldPath().endsWith(extension)).collect(Collectors.toList()).isEmpty())
				return true;
		//		diffEntries = diffEntries.stream().filter(diffEntry -> diffEntry.getNewPath().endsWith(JAVA_FILE) || diffEntry.getOldPath().endsWith(JAVA_FILE)).collect(Collectors.toList());
		//		return !diffEntries.isEmpty();
		return false;
	}

}
