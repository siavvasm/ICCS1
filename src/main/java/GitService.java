/**
 * 
 */


import java.util.List;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public interface GitService {

	Git gitClone(String uri);

	Git gitCloneBranch(String uri, String branch);

	Git gitCloneAllBranches(String uri);

	Iterable<RevCommit> getAllCommitsIterable();

	List<RevCommit> getRevCommitsList();	

	Ref gitCheckout(String sha);

	Ref gitCheckout(RevCommit revCommit);

	List<Ref> getBranchList();

	Set<String> getBranchNamesSet();

	List<Ref> getTagsRefList();

	Set<String> getTaggedCommitsSet();

	RevCommit getRevCommitBySHA(String sha);

	/*
	 * //http://stackoverflow.com/questions/13399990/usage-of-pull-command-in-jgit
	 * PullResult gitPull();
	 * 
	 * FetchResult gitFetch();
	 * 
	 * MergeResult gitMerge();
	 * 
	 * List<RevCommit> getAllCommitsList();
	 * 
	 * List<RevCommit> getDailyCommitsList();
	 * 
	 * List<RevCommit> getWeeklyCommitsList();
	 * 
	 * List<RevCommit> getMonthlyCommitsList();
	 * 
	 * List<RevCommit> getYearlyCommitsList();
	 * 
	 * List<RevCommit> getCommitsListByMode(String mode);
	 */

}
