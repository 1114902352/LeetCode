package com.algorithm.data.singlelinked;

import com.algorithm.data.singlelinked.SingleLinked;

/**
 *	链表奇数拍最前，偶数排最后 
 */
public class OddEvenList {

	/**
	 * 原理:
	 * 重新构造两条链表，遍历原链表，将序号按照奇偶分别放到两条链表中，注意要保存链表的头尾，最后才能够连接起来
	 * @param head
	 * @return
	 */
	public static SingleLinked oddEvenList(SingleLinked head) {
		if (head == null) {
			return null;
		} else if (head.next == null) {
			return head;
		}
		SingleLinked oddHead = null; // 奇数头
		SingleLinked oddCur = null;//当前奇链表节点，最后一个循环时即为链表的尾
		SingleLinked evenHead = null;// 偶数头
		SingleLinked evenCur = null;//当前偶链表节点，最后一个循环时即为链表的尾
		int nums = 0;
		while (head != null) {
			nums++;
			if (nums % 2 == 0) {// 偶数序号
				if (evenCur != null) {
					evenCur.next = new SingleLinked(head.val);
					evenCur = evenCur.next;
				} else {
					evenHead = new SingleLinked(head.val);
					evenCur = evenHead;
				}
			} else {// 奇数序号
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
