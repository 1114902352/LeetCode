package com.algorithm.data.BinaryTree;

import com.algorithm.data.topK.MaxHeap;
import com.alibaba.fastjson.JSONObject;

public class HeapSort{


    /**
     * 排序，最大值放在末尾，data虽然是最大堆，在排序后就成了递增的
     *
     * @param data 要排序的数组
     */
    private static void heapSort(int[] data) {
        MaxHeap maxHeap = new MaxHeap(data);
        System.out.println(JSONObject.toJSONString(data));
        for (int i = data.length - 1; i > 0; i--) {
            System.out.println("before i="+i+",data="+JSONObject.toJSONString(data));
            maxHeap.swap(0,i);
            System.out.println("after  i="+i+",data="+JSONObject.toJSONString(data));
            maxHeap.heapify(0,i);
        }
    }

    public static void main(String[] args) {
        int[] sort ={1,2,3,4,5,6,7,8,9};
        heapSort(sort);
        System.out.println(JSONObject.toJSONString(sort));
    }
}
