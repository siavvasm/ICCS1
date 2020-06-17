/**
 * 
 */


import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class FilterRevCommitsMavenProject extends FilterRevCommits {

	private static final String LANGUAGE = "java";
	private static final String JAVA_FILE = ".java";

	public FilterRevCommitsMavenProject() {
		super();
	}

	public FilterRevCommitsMavenProject(GitServiceBean gitServiceBean, Git git, List<RevCommit> revCommits, RevCommit firstRevCommit, RevCommit lastRevCommit) {
		super(gitServiceBean, git, revCommits, firstRevCommit, lastRevCommit, LANGUAGE);
	}

	public FilterRevCommitsMavenProject(GitServiceBean gitServiceBean, Git git, List<RevCommit> revCommits) {
		super(gitServiceBean, git, revCommits, LANGUAGE);
	}

	@Override
	public List<RevCommit> getFilteredRevCommits() {
		CanonicalTreeParser newCanonicalTreeParser;
		CanonicalTreeParser oldCanonicalTreeParser;
		List<DiffEntry> diffEntries;
		List<RevCommit> filteredRevCommits = new ArrayList<>();

		if (doesTheFirstCommitContainsFiles(Stream.of(JAVA_FILE).collect(Collectors.toSet())))
			filteredRevCommits.add(firstRevCommit);
		revCommits.remove(firstRevCommit);

		for (RevCommit revCommit : revCommits) {
			if (pomXmlExists(revCommit)) {
				//FIXME Now I think only checks if the 1st commit has no parents. Maybe I should check if the edge to the first commit exists
				RevCommit oldRevCommitParent = getParent(revCommit.getParents(), revCommitsMap);
				if (revCommit.getParents().length > 0) {
					oldCanonicalTreeParser = getCanonicalTreeParserFromRevCommit(git.getRepository(), oldRevCommitParent);
					newCanonicalTreeParser = getCanonicalTreeParserFromRevCommit(git.getRepository(), revCommit);
					diffEntries = getDiffEntries(newCanonicalTreeParser, oldCanonicalTreeParser);
					if (diffEntriesContainFiles(diffEntries, Stream.of(JAVA_FILE).collect(Collectors.toSet())))
						filteredRevCommits.add(revCommit);
				}
				else if (revCommit.getParents().length == 0)
					filteredRevCommits.add(revCommit);
			}
		}

		return filteredRevCommits;
	}

	private boolean pomXmlExists(RevCommit revCommit) {
		gitServiceBean.gitCheckout(revCommit);
		return Paths.get(gitServiceBean.getDirectory().getAbsolutePath() + File.separator +"pom.xml").toFile().exists();
	}

}
