package com.algorithm.data.graph;

import java.util.Map;

public interface Algorithm {
    /**
     * ִ���㷨
     */
    void perform(Graph g, String sourceVertex);

    /**
     * �õ�·��
     */
    Map<String, String> getPath();
}
