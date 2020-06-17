/**
 * 
 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
//@Component
//@Scope("prototype")
public class RevCommitsFilterICCS {

	private static Logger logger = LoggerFactory.getLogger(RevCommitsFilterICCS.class);

//	@Autowired
//	private ProjectService projectService;
//	@Autowired
//	private CommitService commitService;
//	@Autowired
//	private EventsService eventsService;

	private String owner;
	private String repository;
	private String projectKey;
	private List<RevCommit> revCommits;
	private GitServiceBean gitServiceBean;
	private Git git;
	private FilterRevCommits filterRevCommits;

	public RevCommitsFilterICCS() { }


	public RevCommitsFilterICCS setOwner(String owner) {
		this.owner = owner;
		return this;
	}

	public RevCommitsFilterICCS setRepository(String repository) {
		this.repository = repository;
		return this;
	}

	public RevCommitsFilterICCS setProjectKey(String projectKey) {
		this.projectKey = projectKey;
		return this;
	}

	public RevCommitsFilterICCS setRevCommits(List<RevCommit> revCommits) {
		this.revCommits = revCommits;
		return this;
	}


	public RevCommitsFilterICCS setGitServiceBean(GitServiceBean gitServiceBean) {
		this.gitServiceBean = gitServiceBean;
		return this;
	}

	public RevCommitsFilterICCS setGit(Git git) {
		this.git = git;
		return this;
	}

	public FilterRevCommits getFilterRevCommits() {
		return filterRevCommits;
	}


	public RevCommitsFilterICCS setFilterRevCommits(FilterRevCommits filterRevCommits) {
		this.filterRevCommits = filterRevCommits;
		return this;
	}


	public List<RevCommit> getRevCommitsForAnalysis() {
		logger.info("> getRevCommitsForAnalysis");
//		List<Events> analyzedVersions = new ArrayList<>(eventsService.findAllVersionsByProjectKee(projectKey));
		Project gitProject = checkIfProjectExistsOtherwiseCreateIt();

		Events lastAnalyzedEvent = null;
//		if (!analyzedVersions.isEmpty())
//			lastAnalyzedEvent = analyzedVersions.get(analyzedVersions.size() - 1);

		Map<String, Commit> alreadyPersistedCommitsMap = new HashMap<>();
//		Map<String, Commit> alreadyPersistedCommitsMap = commitService.findByIdProjectId(gitProject).stream().collect(Collectors.toMap(Commit::getSha, Function.identity()));

		//Filter out Already Persisted Commits
		List<RevCommit> revCommitsToBePersisted = revCommits.stream().filter(revCommit -> Objects.isNull(alreadyPersistedCommitsMap.get(revCommit.getName()))).collect(Collectors.toList());
//		commitService.create(revCommitsToBePersisted.stream().map(rc -> new Commit(rc, gitProject)).collect(Collectors.toList()));
		//		Collection<Commit> commits = commitService.findByIdProjectId(gitProject);
		//		Map<String, Commit> commitsMap = commits.parallelStream().collect(Collectors.toMap(Commit::getSha, Function.identity()));

		//Dijkstra Longest Path
		DijkstraLongestPath dlp = new DijkstraLongestPath(revCommits);
		List<RevCommit> candidateRevCommitsForAnalysis = dlp.getDijkstraLongestPathAsRevCommitsList();

		//Filter RevCommits. Keep only those with pom.xml (Maven Projects)
		//FilterRevCommits filterRevCommits = new FilterRevCommitsMavenProject(gitServiceBean, git, candidateRevCommitsForAnalysis);
		filterRevCommits.setGitServiceBean(gitServiceBean).setGit(git).setRevCommitsAndRevCommitsMap(candidateRevCommitsForAnalysis);
		candidateRevCommitsForAnalysis = filterRevCommits.getFilteredRevCommits();

		candidateRevCommitsForAnalysis = candidateRevCommitsForAnalysis.stream().filter(Objects::nonNull).sorted(Comparator.comparingInt(RevCommit::getCommitTime)).collect(Collectors.toList());
		candidateRevCommitsForAnalysis = filterOutRevCommitsWithTheSameCommitTime(candidateRevCommitsForAnalysis);

		List<RevCommit> revCommitsForAnalysis = filterOutAlreadyAnalyzedCommits(candidateRevCommitsForAnalysis, lastAnalyzedEvent);

		//Checks if the last analyzed commit is parent one of the new commits
		if ((revCommitsForAnalysis.size() == candidateRevCommitsForAnalysis.size()) && Objects.nonNull(lastAnalyzedEvent))
			return new ArrayList<>();

		logger.info("revCommitsForAnalysis.size(): {}", revCommitsForAnalysis.size());
		logger.info("< getRevCommitsForAnalysis");
		return revCommitsForAnalysis;
	}


	private Project checkIfProjectExistsOtherwiseCreateIt() {
		logger.info("> checkIfProjectExistsOtherwiseCreateIt name: {}, kee: {}", repository, projectKey);
//		Project project = projectService.findByKee(projectKey);
		Project project = null;
		if (Objects.nonNull(project)) {
			logger.info("project: {}, name: {}, kee: {}, found!", project, repository, projectKey);
			return project;
		}
		else {
			logger.info("project NOT found name: {}, kee: {}!", repository, projectKey);

			//
			//			Project p = projectService.create(new Project("commons-mathM", "apache:commons-mathM"));
			//			ProjectDetails pd = new ProjectDetails(p, "https://github.com/apache/commons-math", "java");
			//			pd.setUsername("username").setPassword("password");
			//			p.setProjectDetails(pd);
			//			projectService.update(p);
			//

//			Project createdProject = projectService.create(new Project(repository, projectKey));
			Project createdProject = new Project(repository, projectKey);
//			ProjectDetails pd = new ProjectDetails(createdProject, gitServiceBean.getUri(), filterRevCommits.getLanguage());
//			return projectService.update(createdProject);
			return createdProject;
		}
	}


	private List<RevCommit> filterOutAlreadyAnalyzedCommits(List<RevCommit> candidateRevCommitsForAnalysis, Events lastAnalyzedEvent) {
		if (Objects.nonNull(lastAnalyzedEvent))
			for (int i = 0; i < candidateRevCommitsForAnalysis.size(); i++)
				if (Objects.equals(candidateRevCommitsForAnalysis.get(i).getName(), lastAnalyzedEvent.getName()))
					return candidateRevCommitsForAnalysis.subList(i, candidateRevCommitsForAnalysis.size());//include the lastAnalyzedCommit in order to be analyzed again
		return candidateRevCommitsForAnalysis;
	}


	private List<RevCommit> filterOutRevCommitsWithTheSameCommitTime(List<RevCommit> candidateRevCommitsForAnalysis) {
		List<RevCommit> revCommitsNew = new ArrayList<>();

		logger.info("> filterOutRevCommitsWithTheSameCommitTime: {}", candidateRevCommitsForAnalysis.size());

		for (int i = 0; i < candidateRevCommitsForAnalysis.size() - 1; i++) {
			logger.info("Commit(i).SHA: {}, Commit(i).Time: {}, Commit(i + 1).SHA: {}, Commit(i + 1).Time: {}", candidateRevCommitsForAnalysis.get(i).getName(), candidateRevCommitsForAnalysis.get(i).getCommitTime(), candidateRevCommitsForAnalysis.get(i + 1).getName(), candidateRevCommitsForAnalysis.get(i + 1).getCommitTime());
			if (candidateRevCommitsForAnalysis.get(i).getCommitTime() < candidateRevCommitsForAnalysis.get(i + 1).getCommitTime())
				revCommitsNew.add(candidateRevCommitsForAnalysis.get(i));
		}

		logger.info("< filterOutRevCommitsWithTheSameCommitTime: {}", revCommitsNew.size());

		return revCommitsNew;
	}

	public List<RevCommit> filterRevCommitStartingPoint(List<RevCommit> rcs, RevCommit startRevCommit) {
		logger.info("> filterRevCommitStartingPoint: {}", startRevCommit);

		for (int i = 0; i < rcs.size(); i++)
			if (Objects.equals(startRevCommit.getName(), rcs.get(i).getName()))
				rcs = rcs.subList(i + 1, rcs.size());

		logger.info("< filterRevCommitStartingPoint: {}", startRevCommit);
		return rcs;
	}

}
