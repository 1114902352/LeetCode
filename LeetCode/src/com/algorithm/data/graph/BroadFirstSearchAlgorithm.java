package com.algorithm.data.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 封装BFS算法
 */
public class BroadFirstSearchAlgorithm implements Algorithm {
	// 保存已经访问过的地点
	private List<String> visitedVertex;
	/**
	 * Map<K,V>:K表示终点，V表示起点，使用map来保存将导致
	 */
	private Map<String, String> path;

	@Override
	public void perform(Graph g, String sourceVertex) {
		if (null == visitedVertex) {
			visitedVertex = new ArrayList<>();
		}
		if (null == path) {
			path = new HashMap<>();
		}

		BFS(g, sourceVertex);
	}

	@Override
	public Map<String, String> getPath() {
		return path;
	}

	private void BFS(Graph g, String sourceVertex) {
		Queue<String> queue = new LinkedList<>();
		// 标记起点
		visitedVertex.add(sourceVertex);
		// 起点入列
		queue.add(sourceVertex);

		while (false == queue.isEmpty()) {
			String ver = queue.poll();
			List<String> toBeVisitedVertex = g.getAdj().get(ver);
			for (String v : toBeVisitedVertex) {
				if (false == visitedVertex.contains(v)) {
					visitedVertex.add(v);
					path.put(v, ver);
					queue.add(v);
				}
			}
		}
	}

	public static void main(String[] args) {
//		String[] vertexs = { "North Gate", "South Gate", "Classroom", "Square", "Toilet", "Canteen" };
//		Graph g = new Graph(new BroadFirstSearchAlgorithm());
//		for (String vertex : vertexs) {
//			g.addVertex(vertex);
//		}
//		g.addEdge("North Gate", "Classroom");
//		g.addEdge("North Gate", "Square");
//		g.addEdge("Classroom", "Toilet");
//		g.addEdge("Square", "Toilet");
//		g.addEdge("Square", "Canteen");
//		g.addEdge("Toilet", "South Gate");
//		g.addEdge("Canteen", "South Gate");
//		g.done();

		String[] vertexs = { "0","1","2" };
		Graph g = new Graph(new BroadFirstSearchAlgorithm());
		for (String vertex : vertexs) {
			g.addVertex(vertex);
		}
		g.addEdge("0", "1");
		g.addEdge("0", "2");
		g.addEdge("1", "2");
		g.done();
		Stack<String> result = g.findPathTo("2");
		
		System.out.println("BFS: From [North Gate] to [Canteen]:");
		while (!result.isEmpty()) {
			System.out.println(result.pop());
		}
	}
}