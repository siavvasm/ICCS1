/**
 * 
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.eclipse.jgit.revwalk.RevCommit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class DijkstraLongestPath {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final int DIJKSTRA_WEIGHT = 1;

	private List<RevCommit> revCommits;
	private RevCommit firstRevCommit;
	private RevCommit lastRevCommit;

	public DijkstraLongestPath(List<RevCommit> revCommits, RevCommit firstRevCommit, RevCommit lastRevCommit) {
		this.revCommits = revCommits;
		this.firstRevCommit = firstRevCommit;
		this.lastRevCommit = lastRevCommit;
	}

	public DijkstraLongestPath(List<RevCommit> revCommits) {
		this(revCommits, revCommits.get(revCommits.size() - 1), revCommits.get(0));
	}

	private List<Vertex> getDijkstraLongestPath() {
		List<Vertex> nodes = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		HashMap<String, Vertex> vertecesHashMap = addNodes(nodes, revCommits);
		HashMap<String, Edge> edgesHashMap = addEdges(edges, revCommits, vertecesHashMap);
		HashMap<String, Integer> revCommitsChildrenMap = getRevCommitsChildrenMap(revCommits);

		return getLongestPath(lastRevCommit, firstRevCommit, nodes, edges, vertecesHashMap, edgesHashMap, revCommitsChildrenMap);
	}

	public List<RevCommit> getDijkstraLongestPathAsRevCommitsList() {
		List<Vertex> path = this.getDijkstraLongestPath();
		return transformVertexListToRevCommitsList(path);
	}

	private List<RevCommit> transformVertexListToRevCommitsList(List<Vertex> path) {
		List<RevCommit> revCommitsList = new ArrayList<>();
		HashMap<String, RevCommit> revCommitsHashMap = transformRevCommitListToHashMap();
		for (Vertex vertex : path)
			revCommitsList.add(revCommitsHashMap.get(vertex.getName()));
		return revCommitsList;
	}

	private HashMap<String, RevCommit> transformRevCommitListToHashMap() {
		HashMap<String, RevCommit> revCommitsHashMap = new HashMap<>();
		for (RevCommit revCommit : revCommits)
			revCommitsHashMap.put(revCommit.getName(), revCommit);
		return revCommitsHashMap;
	}

	private LinkedList<Vertex> getLongestPath(RevCommit lastRevCommit, RevCommit firstRevCommit, List<Vertex> nodes, List<Edge> edges, HashMap<String, Vertex> vertecesHashMap, HashMap<String, Edge> edgesHashMap, HashMap<String, Integer> revCommitsChildrenMap) {

		LinkedList<Vertex> path = getDijkstraPath(lastRevCommit, firstRevCommit, nodes, edges, vertecesHashMap);
		HashMap<String, Edge> droppedEdgesMap = new HashMap<>();
		String sourceSHA;
		String destinationSHA;
		Edge edge;
		for (int i = 0; i < path.size() - 1; i++) {
			sourceSHA = path.get(i).getName();
			destinationSHA = path.get(i + 1).getName();
			if (hasMoreThanOneChildren(sourceSHA, revCommitsChildrenMap) && !belongsToDroppedMap(sourceSHA, destinationSHA, droppedEdgesMap)) {
				edge = edgesHashMap.get(sourceSHA + destinationSHA);

				logger.info("Edge: {} dropped", edge);
				droppedEdgesMap.put(sourceSHA + destinationSHA, edge);
				updateNoOfChildren(sourceSHA, revCommitsChildrenMap);
				edges.remove(edge);

				path = getDijkstraPath(lastRevCommit, firstRevCommit, nodes, edges, vertecesHashMap);
				i = 0;
			}
		}
		return path;
	}

	private HashMap<String, Vertex> addNodes(List<Vertex> nodes, List<RevCommit> revCommits) {
		HashMap<String, Vertex> vertecesHashMap = new HashMap<>();
		for (RevCommit revCommit : revCommits) {
			Vertex vertex = new Vertex(revCommit.getName(), revCommit.getName());
			vertecesHashMap.put(revCommit.getName(), vertex);
			nodes.add(vertex);
		}
		return vertecesHashMap;
	}

	private HashMap<String, Edge> addEdges(List<Edge> edges, List<RevCommit> revCommits, HashMap<String, Vertex> vertecesHashMap) {
		HashMap<String, Edge> edgesHashMap = new HashMap<>();

		for (RevCommit revCommit : revCommits)
			for (int i = 0; i < revCommit.getParentCount(); i++)
				addEdgeForParent(edges, vertecesHashMap, revCommit, edgesHashMap, i);

		/*
		switch (revCommit.getParentCount()) {
		case 1:
			addEdgeForParent(edges, vertecesHashMap, revCommit, edgesHashMap, 0);
			break;
		case 2:
			addEdgeForParent(edges, vertecesHashMap, revCommit, edgesHashMap, 0);
			addEdgeForParent(edges, vertecesHashMap, revCommit, edgesHashMap, 1);
			break;
		default:
			break;
		}
		 */
		return edgesHashMap;
	}

	private void addEdgeForParent(List<Edge> edges, HashMap<String, Vertex> vertecesHashMap, RevCommit revCommit, HashMap<String, Edge> edgesHashMap, int parentNo) {
		Edge edge;
		String laneId;
		Vertex destination;
		Vertex source;

		String destinationSHA = revCommit.getName();
		String sourceSHA = revCommit.getParent(parentNo).getName();

		laneId = revCommit.getParent(parentNo).getName() + revCommit.getName();
		destination = vertecesHashMap.get(destinationSHA);
		source = vertecesHashMap.get(sourceSHA);
		edge = new Edge(laneId, source, destination, DIJKSTRA_WEIGHT);

		edgesHashMap.put(sourceSHA + destinationSHA, edge);
		edges.add(edge);
	}

	private HashMap<String, Integer> getRevCommitsChildrenMap(List<RevCommit> revCommits) {
		HashMap<String, Integer> revCommitsChildrenMap = new HashMap<>();

		for (RevCommit revCommit : revCommits)
			for (int i = 0; i < revCommit.getParentCount(); i++)
				addChildToMap(revCommitsChildrenMap, revCommit, i);
		/*
		for (RevCommit revCommit : revCommits) {
			switch (revCommit.getParentCount()) {
			case 1:
				addChildToMap(revCommitsChildrenMap, revCommit, 0);
				break;
			case 2:
				addChildToMap(revCommitsChildrenMap, revCommit, 0);
				addChildToMap(revCommitsChildrenMap, revCommit, 1);
				break;
			default:
				break;
			}
		}
		 */
		return revCommitsChildrenMap;
	}

	private void addChildToMap(HashMap<String, Integer> revCommitsChildrenMap, RevCommit revCommit, int parentNo) {
		String sha;
		Integer children;
		sha = revCommit.getParent(parentNo).getName();
		children = revCommitsChildrenMap.get(sha);
		if (Objects.equals(children, null))
			children = 0;
		children++;
		revCommitsChildrenMap.put(sha, children);
	}

	private LinkedList<Vertex> getDijkstraPath(RevCommit lastRevCommit, RevCommit firstRevCommit,  List<Vertex> nodes, List<Edge> edges, HashMap<String, Vertex> vertecesHashMap) {
		Graph graph = new Graph(nodes, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(vertecesHashMap.get(firstRevCommit.getName()));
		return dijkstra.getPath(vertecesHashMap.get(lastRevCommit.getName()));
	}

	private boolean hasMoreThanOneChildren(String name, HashMap<String, Integer> revCommitsChildrenMap) {
		return (Objects.equals(revCommitsChildrenMap.get(name), 1)) ?  false : true; 
	}

	private boolean belongsToDroppedMap(String sourceSHA, String destinationSHA, HashMap<String, Edge> droppedEdgesMap) {
		return !Objects.equals(droppedEdgesMap.get(sourceSHA + destinationSHA), null);
	}

	private void updateNoOfChildren(String sourceSHA, HashMap<String, Integer> revCommitsChildrenMap) {
		Integer noOfChildren = revCommitsChildrenMap.get(sourceSHA);
		noOfChildren--;
		revCommitsChildrenMap.put(sourceSHA, noOfChildren);
	}

}
