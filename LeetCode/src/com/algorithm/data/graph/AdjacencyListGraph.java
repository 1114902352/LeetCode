package com.algorithm.data.graph;

import java.util.*;

//0---2
//|\  |  
//| \ |
//|  \|
//1---3
/**
 * ͼ����ͬ�����ַ�ʽ��ʾ���ڽӼ��ϣ��ڽӾ��� ���ڽ��б�
 */
/**
 * �ڽӾ�����NΪͼ�Ľڵ�����������γ�N*N�ľ������У��ڵ�ֱ��������Ϊ1��������Ϊ0���Լ�����Ϊ0
 */
//    0  1  2  3
// 0  0  1  1  1
// 1  1  0  0  1
// 2  1  0  0  1
// 3  1  1  1  0

/**
 * �ڽ��б������е�ÿ��Ԫ�ض���һ����������������Ԫ�س�Ϊ���㣬����������ӹ�ϵ��Ϊ�ߣ���ӵ�������յ㡣
 * ��ƽṹʱӦ�����һ��VNode������(���(�����±�)����һ����)��һ������Edge(�յ�(�����±�)����һ����)��һ��ͼ��(ͼ�Ķ������������б��붥���б��Ӧ�Ĳ����б���ʶ��Ӧ����Ԫ���Ƿ񱻷��ʹ�)
 */
// 0 --> 1 --> 2 --> 3
// 1 --> 0 --> 3
// 2 --> 0 --> 3
// 3 --> 0 --> 1 --> 2

class VNode {
	int from;// �ߵ����
	Edge first;// ��fromΪ���ĵ�һ����

	public VNode(int from) {
		this.from = from;
		first = null;
	}
}

class Edge {
	int to;// �ߵ��յ�
	Edge next;// ����ͬһ������һ����

	public Edge(int to) {
		this.to = to;
		next = null;
	}
}

public class AdjacencyListGraph {
	int k;// ͼ�Ķ�����
	VNode[] V;// ��������
	boolean[] visted;// ��¼ĳ�������Ƿ������

	public AdjacencyListGraph(int k, VNode[] v) {
		this.k = k;
		this.V = v;
		visted = new boolean[k];
	}

	// ��v0�������������ȱ���ͼ�����ö��п��Ʒ���˳��
	// ����˳�� 0 1 2 3 3 3
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

	// �ڽӱ������������ͼ
	// ����˳�� 0 1 3 2
	public void DFS(int v0) {
		visted[v0] = true;
		// ���ʶ���v0
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
		int k = sc.nextInt();// ͼ�Ķ�����
		int m = sc.nextInt();// ͼ�ı���

		VNode[] V = new VNode[k];
		for (int i = 0; i < k; i++)
			V[i] = new VNode(i);
		Edge e = null;
		Edge e1 = null;
		int u = 0;
		int v = 0;
		for (int i = 0; i < m; i++) {
			u = sc.nextInt();// ���
			v = sc.nextInt();// �յ�
			e = new Edge(v);
			e.next = V[u].first;// ���뵽����ͷ
			V[u].first = e;

			// ��������ͼ���Գƴ�������ͼû�з���0->1��ҲӦ��1->0
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
