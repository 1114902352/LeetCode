package com.algorithm.data.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * (����)ͼ
 */
public class Graph {
	// ͼ�����
	private String firstVertax;

	// �ڽӱ�
	private Map<String, List<String>> adj = new HashMap<>();

	// �����㷨
	private Algorithm algorithm;

	public Graph(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * ִ���㷨
	 */
	public void done() {
		algorithm.perform(this, firstVertax);
	}

	/**
	 * �õ�����㵽{@code vertex}������·��
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
	 * ���һ����
	 */
	public void addEdge(String fromVertex, String toVertex) {
		if (firstVertax == null) {
			firstVertax = fromVertex;
		}

		adj.get(fromVertex).add(toVertex);
		adj.get(toVertex).add(fromVertex);
	}

	/**
	 * ���һ������
	 */
	public void addVertex(String vertex) {
		adj.put(vertex, new ArrayList<String>());
	}

	public Map<String, List<String>> getAdj() {
		return adj;
	}
}
