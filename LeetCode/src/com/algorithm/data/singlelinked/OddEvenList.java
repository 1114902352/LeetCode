package com.algorithm.data.singlelinked;

import com.algorithm.data.singlelinked.SingleLinked;

/**
 *	������������ǰ��ż������� 
 */
public class OddEvenList {

	/**
	 * ԭ��:
	 * ���¹���������������ԭ��������Ű�����ż�ֱ�ŵ����������У�ע��Ҫ���������ͷβ�������ܹ���������
	 * @param head
	 * @return
	 */
	public static SingleLinked oddEvenList(SingleLinked head) {
		if (head == null) {
			return null;
		} else if (head.next == null) {
			return head;
		}
		SingleLinked oddHead = null; // ����ͷ
		SingleLinked oddCur = null;//��ǰ������ڵ㣬���һ��ѭ��ʱ��Ϊ�����β
		SingleLinked evenHead = null;// ż��ͷ
		SingleLinked evenCur = null;//��ǰż����ڵ㣬���һ��ѭ��ʱ��Ϊ�����β
		int nums = 0;
		while (head != null) {
			nums++;
			if (nums % 2 == 0) {// ż�����
				if (evenCur != null) {
					evenCur.next = new SingleLinked(head.val);
					evenCur = evenCur.next;
				} else {
					evenHead = new SingleLinked(head.val);
					evenCur = evenHead;
				}
			} else {// �������
				if (oddCur != null) {
					oddCur.next = new SingleLinked(head.val);
					oddCur = oddCur.next;
				} else {
					oddHead = new SingleLinked(head.val);
					oddCur = oddHead;
				}
			}
			head = head.next;
		}
		oddCur.next = evenHead;
		return oddHead;
	}

	public static void main(String[] args) {
		SingleLinked s1 = new SingleLinked(1);
		SingleLinked s2 = new SingleLinked(2);
		SingleLinked s3 = new SingleLinked(3);
		SingleLinked s4 = new SingleLinked(4);
		s1.next = s2;
		s2.next = s3;
		s3.next = s4;
		SingleLinked.scanNode(oddEvenList(s1));
	}
}
