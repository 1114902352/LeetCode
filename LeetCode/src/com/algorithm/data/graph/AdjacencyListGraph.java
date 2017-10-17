package com.algorithm.data.graph;

import java.util.*;

//0---2
//|\  |  
//| \ |
//|  \|
//1---3
/**
 * 图可以同过三种方式表示：邻接集合，邻接矩阵 ，邻接列表
 */
/**
 * 邻接矩阵：设N为图的节点数，则可以形成N*N的矩阵，其中，节点直接相连的为1，不相连为0，自己本身为0
 */
//    0  1  2  3
// 0  0  1  1  1
// 1  1  0  0  1
// 2  1  0  0  1
// 3  1  1  1  0

/**
 * 邻接列表：数组中的每个元素都是一个单链表，其中数组元素称为顶点，单链表的连接关系称为边，边拥有起点和终点。
 * 设计结构时应该设计一个VNode顶点类(起点(数组下标)、第一条边)，一个边类Edge(终点(数组下标)，下一条边)，一个图类(图的顶点数，顶点列表，与顶点列表对应的布尔列表：标识对应顶点元素是否被访问过)
 */
// 0 --> 1 --> 2 --> 3
// 1 --> 0 --> 3
// 2 --> 0 --> 3
// 3 --> 0 --> 1 --> 2

class VNode {
	int from;// 边的起点
	Edge first;// 以from为起点的第一条边

	public VNode(int from) {
		this.from = from;
		first = null;
	}
}

class Edge {
	int to;// 边的终点
	Edge next;// 具有同一起点的下一条边

	public Edge(int to) {
		this.to = to;
		next = null;
	}
}

public class AdjacencyListGraph {
	int k;// 图的顶点数
	VNode[] V;// 顶点数组
	boolean[] visted;// 记录某个顶点是否遍历过

	public AdjacencyListGraph(int k, VNode[] v) {
		this.k = k;
		this.V = v;
		visted = new boolean[k];
	}

	// 从v0顶点出发广度优先遍历图，利用队列控制访问顺序
	// 队列顺序 0 1 2 3 3 3
	public void BFS(int v0) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(v0);
		while (!que.isEmpty()) {
			v0 = que.poll();
			if (!visted[v0]) {
				System.out.print(v0 + "  ");
				visted[v0] = true;
			}
			Edge e = V[v0].first;
			while (e != null) {
				if (!visted[e.to])
					que.add(e.to);
				e = e.next;
			}

		}
	}

	// 邻接表深度优先搜索图
	// 遍历顺序 0 1 3 2
	public void DFS(int v0) {
		visted[v0] = true;
		// 访问顶点v0
		System.out.print(v0 + "  ");

		Edge p = V[v0].first;
		while (p != null) {
			if (!visted[p.to]) {
				DFS(p.to);
			}
			p = p.next;
		}
	}
	
	public int getShortestPath(int[][] M){
		return 0;
	}
}

class Test {
	public static void main(String[] args) {
		VNode[] vNodes = new VNode[4];
		Edge e03 = new Edge(3);
		Edge e02 = new Edge(2);
		e02.next = e03;
		Edge e01 = new Edge(1);
		e01.next = e02;
		VNode v0 = new VNode(0);
		v0.first = e01;
		vNodes[0] = v0;

		Edge e12 = new Edge(3);
		Edge e11 = new Edge(0);
		e11.next = e12;
		VNode v1 = new VNode(1);
		v1.first = e11;
		vNodes[1] = v1;

		Edge e22 = new Edge(3);
		Edge e21 = new Edge(0);
		e21.next = e22;
		VNode v2 = new VNode(2);
		v2.first = e21;
		vNodes[2] = v2;

		Edge e33 = new Edge(2);
		Edge e32 = new Edge(1);
		e32.next = e33;
		Edge e31 = new Edge(0);
		e31.next = e32;
		VNode v3 = new VNode(3);
		v3.first = e31;
		vNodes[3] = v3;

		AdjacencyListGraph graph = new AdjacencyListGraph(4, vNodes);
		graph.BFS(0);
	}

	public static void main0(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();// 图的顶点数
		int m = sc.nextInt();// 图的边数

		VNode[] V = new VNode[k];
		for (int i = 0; i < k; i++)
			V[i] = new VNode(i);
		Edge e = null;
		Edge e1 = null;
		int u = 0;
		int v = 0;
		for (int i = 0; i < m; i++) {
			u = sc.nextInt();// 起点
			v = sc.nextInt();// 终点
			e = new Edge(v);
			e.next = V[u].first;// 插入到链表开头
			V[u].first = e;

			// 对于无向图作对称处理，无向图没有方向0->1，也应该1->0
			e1 = new Edge(u);
			e1.next = V[v].first;
			V[v].first = e1;
		}
		sc.close();
		AdjacencyListGraph gra = new AdjacencyListGraph(k, V);
		// gra.BFS(0);
		gra.DFS(0);
	}
}
