package com.algorithm.data.topK;

public class MaxHeap {

    private int[] data;

    public MaxHeap(int[] data){
        this.data = data;
        buildMaxHeap(data.length);
    }

    public void buildMaxHeap(int heapLength){
        int lastHasChildIndex = getLastHasChild(heapLength);
        for(int i = lastHasChildIndex;i>=0;i--){
            heapify(i,heapLength);
        }
    }

    private void heapify(int rootIndex,int heapLength){
        int leftIndex = getLeft(rootIndex);
        int rightIndex = getRight(rootIndex);
        int maximumIndex = rootIndex;
        if(leftIndex < heapLength && data[leftIndex] > data[maximumIndex]){
            maximumIndex = leftIndex;
        }
        if(rightIndex < heapLength && data[rightIndex] > data[maximumIndex]){
            maximumIndex = rightIndex;
        }
        if(maximumIndex == rootIndex){
            return;
        }
        swap(maximumIndex,rootIndex);
        heapify(maximumIndex,heapLength);
    }

    // 获取右结点的数组下标
    private int getRight(int i)
    {
        return (i + 1) << 1;
    }

    // 获取左结点的数组下标
    private int getLeft(int i)
    {
        return ((i + 1) << 1) - 1;
    }

    // 获取最后一个拥有子节点的节点
    private int getLastHasChild(int heapLength){
        return (heapLength)/2-1;
    }

    // 交换元素位置
    private void swap(int maximumIndex, int rootIndex)
    {
        int tmp = data[maximumIndex];
        data[maximumIndex] = data[rootIndex];
        data[rootIndex] = tmp;
    }
}
