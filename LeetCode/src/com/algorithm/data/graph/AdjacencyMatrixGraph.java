package com.algorithm.data.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjacencyMatrixGraph {

	/**
	 * @return 以i元素为根节点，下属所有节点的数量
	 */
	public static int longest_dfs(int[][] mat, int i, boolean[] visited) {
		if (visited[i])
			return 0;
		visited[i] = true;
		int count = 1;
		for (int j = 0; j < visited.length; j++)
			if (i != j && mat[i][j] == 1)
				count += longest_dfs(mat, j, visited);
		return count;
	}

	public static int shortest_bfs(int[][] mat, int i, boolean[] visited) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(i);
		visited[i] = true;
		while (!que.isEmpty()) {
			i = que.poll();
//			if (!visited[i]) {
//				visited[i] = true;
//			}
			int[] temp = mat[i];
			for (int j = 0; j < temp.length; j++) {
				if (i != j && mat[i][j] == 1 && !visited[j]) {
					que.add(j);
					visited[j] = true;
					System.out.println(i+" ");
				}
			}
		}
		return 0;
	}

	public static int shortest_dfs(int[][] mat, int i, boolean[] visited) {
		if (visited[i])
			return 0;
		visited[i] = true;
		List<Integer> temp = new ArrayList<>();
		for (int j = 0; j < visited.length; j++)
			if (i != j && mat[i][j] == 1)
				temp.add(1+shortest_dfs(mat, j, visited));
		temp.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0-arg1;
			}
		});
		return temp.get(0);
	}

	public static void bfs(int[][] mat, int i, boolean[] visited) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(i);
		while (!que.isEmpty()) {
			i = que.poll();
			if (!visited[i]) {
				System.out.print(i + " ");
				visited[i] = true;
			}
			int[] temp = mat[i];
			for (int j = 0; j < temp.length; j++) {
				if (i != j && mat[i][j] == 1 && !visited[j]) {
					que.add(j);
				}
			}
		}
	}

	public static int getShortestPath(int[][] mat) {
		int result = 0;
		int temp = 0;
		for (int i = 0; i < mat.length; i++) {
			boolean[] visited = new boolean[mat.length];
			temp = shortest_bfs(mat, i, visited);
			if (temp > result) {
				result = temp;
			}
		}
		return result;
	}

	public static int getlongestPath(int[][] mat) {
		int result = 0;
		int temp = 0;
		for (int i = 0; i < mat.length; i++) {
			boolean[] visited = new boolean[mat.length];
			temp = longest_dfs(mat, i, visited);
			if (temp > result) {
				result = temp;
			}
		}
		return result;
	}

	public static void main(String[] args) {
//		int[][] mat = { { 1, 1, 0, 1 }, { 1, 1, 1, 0 }, { 0, 1, 1, 0 }, { 1, 0, 0, 1 } };
		// int[][] mat = {{1,1,0},{1,1,1},{0,1,1}};
		 int[][] mat = {{0,1,1,1},{1,0,0,1},{1,0,0,1},{1,1,1,0}};
//		System.out.println(AdjacencyMatrixGraph.shortest_dfs(mat, 0, new boolean[mat.length]));
		System.out.println(AdjacencyMatrixGraph.longest_dfs(mat, 0, new boolean[mat.length]));
		// System.out.println(AdjacencyMatrixGraph.getShortestPath(mat));
	}
}
