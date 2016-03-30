package com.algorithm.data.singlelinked;

public class DelDuplicates {

	/**
	 * ɾ�������ظ�Ԫ�أ� ԭ��ά������ָ�룬p1ָ���һ���ڵ㣬p2ָ��ڶ����ڵ㣬�ȶԷ�������ֵ��ͬ��ɾ����һ��Ԫ�أ�p1�Զ�ָ��ڶ���Ԫ�أ�
	 * p2����ݽ�һ�����ֲ�ָ��ͳһԪ�أ�
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
