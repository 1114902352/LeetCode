package com.algorithm.data.topK;

public class MaxHeap {

    private int[] data;

    public MaxHeap(int[] data){
        this.data = data;
        buildMaxHeap(data.length);
    }

    /**
     * 构建最大堆
     * @param heapLength 堆的长度而不是下标(topK算法时，该值即为K)
     */
    public void buildMaxHeap(int heapLength){
        // 拿到有子节点的最后一个元素下标
        int lastHasChildIndex = getLastHasChild(heapLength);
        for(int i = lastHasChildIndex;i>=0;i--){
            heapify(i,heapLength);
        }
    }

    /**
     * 将以rootIndex为根节点的子树构建为一个最大堆
     * @param rootIndex 根节点下标
     * @param heapLength 堆长度
     */
    public void heapify(int rootIndex,int heapLength){
        int leftIndex = getLeft(rootIndex);
        int rightIndex = getRight(rootIndex);
        // 比较根节点/左子节点/右子节点，找到最大值
        int maximumIndex = rootIndex;
        if(leftIndex < heapLength && data[leftIndex] > data[maximumIndex]){
            maximumIndex = leftIndex;
        }
        if(rightIndex < heapLength && data[rightIndex] > data[maximumIndex]){
            maximumIndex = rightIndex;
        }
        // 最大值就是根，无变化直接返回
        if(maximumIndex == rootIndex){
            return;
        }
        // 交换最大值与根的值
        swap(maximumIndex,rootIndex);
        // 由于替换后以maximumIndex未根节点的子树不在为最大堆，需要重新构建
        heapify(maximumIndex,heapLength);
    }

    /**
     * 获取右结点的数组下标
     * @param i 父节点下标
     * @return 右节点下标
     */
    private int getRight(int i)
    {
        return (i + 1) << 1;
    }

    /**
     * 获取左结点的数组下标
     * @param i 父节点下标
     * @return 左节点下标
     */
    private int getLeft(int i)
    {
        return ((i + 1) << 1) - 1;
    }

    /**
     * 完全二叉树只有数组下标小于或等于 heapLength / 2 - 1 的元素有孩子结点
     * @param heapLength 堆长度
     * @return 最后一个有孩子的根节点
     */
    private int getLastHasChild(int heapLength){
        return (heapLength)/2-1;
    }

    /**
     * 交换元素位置
     *
     * @param i 元素1下标
     * @param j 元素2下标
     */
    public void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    /**
     * 替换根元素值，并重新heapify
     *
     * @param root
     */
    public void  setRoot(int root,int heapLenght) {
        data[0] = root;
        heapify(0,heapLenght);
    }
}
