package com.algorithm.data.singlelinked;

public class DelDuplicates {

	/**
	 * 删除链表重复元素， 原理：维持两个指针，p1指向第一个节点，p2指向第二个节点，比对发现两个值相同，删除第一个元素，p1自动指向第二个元素，
	 * p2必须递进一步保持不指向统一元素，
	 * 
	 * @param head
	 * @return
	 */
	public static SingleLinked deleteDuplicates(SingleLinked head) {
		if (head == null || head.next == null) {
			return head;
		}
		SingleLinked pre = head;
		SingleLinked suf = head.next;
		while (suf != null) {
			if (pre.val == suf.val) {
				pre.val = pre.next.val;
				pre.next = pre.next.next;
				suf = suf.next;
			} else {
				suf = suf.next;
				pre = pre.next;
			}
		}
		return head;
	}

	public static void main(String[] args) {
		SingleLinked s1 = new SingleLinked(1);
		SingleLinked s2 = new SingleLinked(1);
		SingleLinked s3 = new SingleLinked(2);
		SingleLinked s4 = new SingleLinked(3);
		SingleLinked s5 = new SingleLinked(3);
		SingleLinked s6 = new SingleLinked(3);
		SingleLinked s7 = new SingleLinked(7);
		SingleLinked s8 = new SingleLinked(7);
		SingleLinked s9 = new SingleLinked(7);
		s1.next = s2;
		s2.next = s3;
		s3.next = s4;
		s4.next = s5;
		s5.next = s6;
		s6.next = s7;
		s7.next = s8;
		s8.next = s9;
		deleteDuplicates(s1);
		SingleLinked.scanNode(s1);
	}
}
