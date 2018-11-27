package com.algorithm.data.topK;

public class MinHeap {
    private int[] data;

    public MinHeap(int[] data) {
        this.data = data;
        buildMinHeap();
    }

    /**
     * 构建最小堆
     */
    private void buildMinHeap() {
        // 从最后一个有子节点的下标往0遍历
        for (int i = (data.length) / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * 将以i为根节点的子树构建为一个最小堆
     *
     * @param i 有孩子的节点索引
     */
    private void heapify(int i) {
        // 获取左右结点的数组下标
        int l = left(i);
        int r = right(i);
        // 临时变量，表示 跟结点、左结点、右结点中最小的值的结点的下标
        int smallest = i;
        // 存在左结点，且左结点的值小于根结点的值
        if (l < data.length && data[l] < data[i])
            smallest = l;
        // 存在右结点，且右结点的值小于以上比较的较小值
        if (r < data.length && data[r] < data[smallest])
            smallest = r;
        // 左右结点的值都大于根节点，直接return，不做任何操作
        if (i == smallest)
            return;
        // 交换根节点和左右结点中最小的那个值，把根节点的值替换下去
        swap(i, smallest);
        // 由于替换后左右子树会被影响，所以要对受影响的子树再进行heapify
        // 跟与smallesst替换后，需要注意smallest下的子节点必须重新heapify
        heapify(smallest);
    }

    /**
     * 获取右结点的数组下标
     *
     * @param i 父节点下标
     * @return 右节点下标
     */
    private int right(int i) {
        return (i + 1) << 1;
    }

    /**
     * 获取左结点的数组下标
     *
     * @param i 父节点下标
     * @return 左节点下标
     */
    private int left(int i) {
        return ((i + 1) << 1) - 1;
    }

    /**
     * 交换元素位置
     *
     * @param i 元素1下标
     * @param j 元素2下标
     */
    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    /**
     * 获取堆中的最小的元素=根元素
     *
     * @return 根元素
     */
    public int getRoot() {
        return data[0];
    }

    /**
     * 替换根元素值，并重新heapify
     *
     * @param root
     */
    public void  setRoot(int root) {
        data[0] = root;
        heapify(0);
    }
}
