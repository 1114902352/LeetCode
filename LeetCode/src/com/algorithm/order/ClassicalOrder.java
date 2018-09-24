package com.algorithm.order;

import com.algorithm.data.topK.MaxHeap;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

/**
 * 经典排序:插入排序（直接插入排序，希尔排序），选择排序（简单选择排序，堆排序），交换排序（冒泡排序，快速排序），归并排序，基数排序
 */
public class ClassicalOrder {

	/**
	 * 直接插入排序：最坏(n2)，最好(n)，平均O(n2) 原理：将最左边的数视一个有序部分，将后面的数依次插入到这部分的合适位置
	 * 排序的次数与数组本身的顺序和个数有关
	 */
	public static void straightInsertionSort(int[] a) {
		// 直接插入排序
		for (int i = 1; i < a.length; i++) {
			// 待插入元素
			int temp = a[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				// 将大于temp的往后移动一位
				if (a[j] > temp) {
					a[j + 1] = a[j];
				} else {
					break;
				}
			}
			a[j + 1] = temp;
		}
	}

	/**
	 * 二分法插入排序：最坏(n2/2)，最好(n)，平均O(n2) 原理：与直接插入法类似，只是在寻找有序部分时，运用二分法查找
	 * 排序的次数只与数组本身的个数相关
	 */
	public static void binaryInsertionSorting(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int temp = a[i];
			int left = 0;
			int right = i - 1;
			int mid = 0;
			while (left <= right) {
				mid = (left + right) / 2;
				if (temp < a[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			for (int j = i - 1; j >= left; j--) {
				a[j + 1] = a[j];
			}
			if (left != i) {
				a[left] = temp;
			}
		}
	}

	/**
	 * 希尔排序
	 */
	public static void shellSort(int[] arr){
		int gap = 1, len = arr.length;
		while (gap < len/3){
			gap = gap * 3 + 1;// <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
		}
		//步长1,4,13,40  ...
		for(;gap>0;gap/=3){
			for(int i=0;i<gap;i++){
				insertSort(arr,i,gap);
			}
		}
	}

	/**
	 * 可定起始位置和步进的插入排序
	 * @param nums
	 * @param start 起始位置
	 * @param gap 步长
	 */
	public static void insertSort(int[] nums,int start,int gap){
		for(int i = start + gap;i < nums.length;i+=gap){
			for(int j= start;j < i;j+=gap){
				if(nums[i] <= nums[j]){
					int temp = nums[i];
					for(int k=i;k>j;k-=gap){
						nums[k] = nums[k-gap];
					}
					nums[j] = temp;
				}
			}
		}
	}

	/**
	 * 归并排序
	 * 1、把 n 个记录看成 n 个长度为 l 的有序子表
	 * 2、进行两两归并使记录关键字有序，得到 n/2 个长度为 2 的有序子表
	 * 3、重复第 2 步直到所有记录归并成一个长度为 n 的有序表为止。
	 * 另外，为了节省空间，通常由一个数组的first,mid,last来表示两个数组
	 */
	private static void mergeSort(int[] nums){
		divide(nums,0,nums.length -1 ,new int[nums.length]);
	}

	/**
	 * 划分数组，并在最后一步再合并
	 */
	private static void divide(int[] nums,int first,int last,int[] temp){
		if(first >= last){
			return;
		}
		int mid = (first+last)/2;
		divide(nums,first,mid,temp);
		divide(nums,mid+1,last,temp);
		merge(nums,first,mid,last,temp);
	}

	/**
	 * 合并两个有序数组，temp为临时数组
	 * 将nums中的两个有序区间[first,mid],[mid+1,last]合并到temp中，最后再用temp中的值覆盖
	 * nums中的区间[first,last]
	 */
	private static void merge(int[] nums, int first, int mid, int last,int[] temp){
		int i = first,j = mid + 1;
		int k = 0;
		while(i <= mid && j<= last){
			if(nums[i] > nums[j]){
				temp[k++] = nums[j++];
			}else{
				temp[k++] = nums[i++];
			}
		}
		while(i <= mid){
			temp[k++]=nums[i++];
		}
		while(j <= last){
			temp[k++]=nums[j++];
		}
		for (int x = 0; x < k; x++) {
			nums[first+x] = temp[x];
		}
	}

	/**
	 * 堆排序
	 * a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
	 * b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
	 * c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
	 */
	public static void heapSort(int[] nums){
		MaxHeap maxHeap = new MaxHeap(nums);
		for (int i = nums.length - 1; i>=0 ; i--) {
			int temp = nums[0];
			nums[0] = nums[i];
			nums[i] = temp;
			maxHeap.buildMaxHeap(i);
		}
	}

	public static void main(String[] args) {
		int[] nums1 = {51,32,13,4,4,1,4,9,3,1,20,30,10,9,8,7};
//		int[] nums1 = {10,9,8,7,6,5,4,3,2,1};
		int[] nums2 = Arrays.copyOf(nums1,nums1.length);
		int[] nums3 = Arrays.copyOf(nums1,nums1.length);
		int[] nums4 = Arrays.copyOf(nums1,nums1.length);
		straightInsertionSort(nums1);
		System.out.println(JSONObject.toJSONString(nums1));
		mergeSort(nums2);
		System.out.println(JSONObject.toJSONString(nums2));
		heapSort(nums3);
		System.out.println(JSONObject.toJSONString(nums3));
		shellSort(nums4);
		System.out.println(JSONObject.toJSONString(nums4));
	}
}
