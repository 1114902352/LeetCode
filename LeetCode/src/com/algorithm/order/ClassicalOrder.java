package com.algorithm.order;

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
	 * 希尔排序：最好~=最坏，平均O(nlogn) 原理：以n/2为分组，每次对组员进行插入排序
	 */
	public static void shellSort(int a[]) {
		int n = a.length;
		int j, gap;
		for (gap = n / 2; gap > 0; gap /= 2)
			for (j = gap; j < n; j++)// 从数组第gap个元素开始
				if (a[j] < a[j - gap])// 每个元素与自己组内的数据进行直接插入排序
				{
					int temp = a[j];
					int k = j - gap;
					while (k >= 0 && a[k] > temp) {
						a[k + gap] = a[k];
						k -= gap;
					}
					a[k + gap] = temp;
				}
	}

	public static void main(String[] args) {

	}
}
