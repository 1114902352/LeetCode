package com.algorithm.data.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * (无向)图
 */
public class Graph {
	// 图的起点
	private String firstVertax;

	// 邻接表
	private Map<String, List<String>> adj = new HashMap<>();

	// 遍历算法
	private Algorithm algorithm;

	public Graph(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * 执行算法
	 */
	public void done() {
		algorithm.perform(this, firstVertax);
	}

	/**
	 * 得到从起点到{@code vertex}点的最短路径
	 * 
	 * @param vertex
	 * @return
	 */
	public Stack<String> findPathTo(String vertex) {
		Stack<String> stack = new Stack<>();
		stack.add(vertex);

		Map<String, String> path = algorithm.getPath();
		for (String location = path.get(vertex); false == location.equals(firstVertax); location = path.get(location)) {
			stack.push(location);
		}
		stack.push(firstVertax);

		return stack;
	}

	/**
	 * 添加一条边
	 */
	public void addEdge(String fromVertex, String toVertex) {
		if (firstVertax == null) {
			firstVertax = fromVertex;
		}

		adj.get(fromVertex).add(toVertex);
		adj.get(toVertex).add(fromVertex);
	}

	/**
	 * 添加一个顶点
	 */
	public void addVertex(String vertex) {
		adj.put(vertex, new ArrayList<String>());
	}

	public Map<String, List<String>> getAdj() {
		return adj;
	}
}
