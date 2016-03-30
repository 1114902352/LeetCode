package com.algorithm.order;

/**
 * ��������:��������ֱ�Ӳ�������ϣ�����򣩣�ѡ�����򣨼�ѡ�����򣬶����򣩣���������ð�����򣬿������򣩣��鲢���򣬻�������
 */
public class ClassicalOrder {

	/**
	 * ֱ�Ӳ��������(n2)�����(n)��ƽ��O(n2) ԭ��������ߵ�����һ�����򲿷֣�������������β��뵽�ⲿ�ֵĺ���λ��
	 * ����Ĵ��������鱾���˳��͸����й�
	 */
	public static void straightInsertionSort(int[] a) {
		// ֱ�Ӳ�������
		for (int i = 1; i < a.length; i++) {
			// ������Ԫ��
			int temp = a[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				// ������temp�������ƶ�һλ
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
	 * ���ַ����������(n2/2)�����(n)��ƽ��O(n2) ԭ����ֱ�Ӳ��뷨���ƣ�ֻ����Ѱ�����򲿷�ʱ�����ö��ַ�����
	 * ����Ĵ���ֻ�����鱾��ĸ������
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
	 * ϣ���������~=���ƽ��O(nlogn) ԭ����n/2Ϊ���飬ÿ�ζ���Ա���в�������
	 */
	public static void shellSort(int a[]) {
		int n = a.length;
		int j, gap;
		for (gap = n / 2; gap > 0; gap /= 2)
			for (j = gap; j < n; j++)// �������gap��Ԫ�ؿ�ʼ
				if (a[j] < a[j - gap])// ÿ��Ԫ�����Լ����ڵ����ݽ���ֱ�Ӳ�������
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
