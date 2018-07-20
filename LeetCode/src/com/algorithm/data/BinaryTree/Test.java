package com.algorithm.data.BinaryTree;

/**
 * @author zhangqingc
 */
public class Test {

    public static void main(String[] args) {
        int[] arr = {3,7,2,9,1,4,6,8,10,5};
        quickSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i+"\t");
        }
    }

    public static void quickSort(int arr[],int i,int j){
        if(i >= j){
            return;
        }
        int lo = i;
        int hi = j;
        int standard = arr[i];
        while(lo < hi){
            while(lo < hi && arr[hi] >= standard){
                hi--;
            }
            if(lo < hi){
                arr[lo] = arr[hi];
                lo++;
            }
            while(lo < hi && arr[lo] < standard){
                lo++;
            }
            if(lo < hi){
                arr[hi] = arr[lo];
                hi--;
            }
        }
        arr[lo] = standard;
        quickSort(arr,i,lo-1);
        quickSort(arr,lo+1,j);
    }
}
