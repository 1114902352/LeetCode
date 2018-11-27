package com.algorithm.data.topK;

public class TopK {
    /**
     * 从data数组中获取最大的k个数
     *
     * @param data 原数据
     * @param k    最大值个数
     * @return 最大值数组
     */
    private static int[] topK(int[] data, int k) {
        // 先取K个元素放入一个数组topk中
        int[] topk = new int[k];
        for (int i = 0; i < k; i++) {
            topk[i] = data[i];
        }

        // 将前K个元素转换成最小堆
        MinHeap heap = new MinHeap(topk);

        // 从K开始，将源数组中的元素data[i]依次与最小堆的根节点root比较
        for (int i = k; i < data.length; i++) {
            int root = heap.getRoot();
            // 当数据大于堆中最小的数（根节点）时，替换堆中的根节点，再转换成堆
            if (data[i] > root) {
                heap.setRoot(data[i]);
            }
        }
        return topk;
    }

    public static void main(String[] args) {
        // 源数据
        int[] data = {5, 5, 46, 3, 4, 54, 90, 3, 2, 1, 3, 3, 20, 21, 25};
        // 获取Top5
        int[] top5 = topK(data, 5);
        for (int i = 0; i < 5; i++) {
            System.out.println(top5[i]);
        }
    }
}
