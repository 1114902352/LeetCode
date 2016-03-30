package com.algorithm.data.singlelinked;

/**
 * 单链表 
 */
public class SingleLinked {
	public int val;
	public SingleLinked next;

	public SingleLinked(int x) {
		val = x;
	}

	/**
	 * 单链表遍历
	 * @param node 头结点
	 */
	public static void scanNode(SingleLinked node){
		System.out.print(node.val+"-->");
		if(node.next==null){
			return;
		}
		scanNode(node.next);
	}
	
	/**
	 * 单链表删除子节点 
	 * @param node 需要删除的结点
	 */
	public static void deleteNode(SingleLinked node) {
		 if(node.next==null){
			 return;
		 }
		 node.val = node.next.val;
		 node.next = node.next.next;
	 }
	
	/**
	 * 扭转单向链表:
	 * 原理：声明三个变量cur,pre,suf,将当前节点，前节点，后节点备份起来，再将当前节点指向前一个节点，进行递进，注意备份的顺序，
	 * @param head
	 * @return 反转后的头节点
	 */
	public static SingleLinked reverseList(SingleLinked head){
		SingleLinked cur = head;
		SingleLinked pre = null;
		SingleLinked suf = null;
		while(cur!=null){
			suf = cur.next;
			cur.next = pre;
			pre = cur;
			cur = suf;
		}
		return pre;
	}
	
	/**
	 * 寻找最后一个节点
	 * @param head
	 * @return
	 */
	public static SingleLinked getLastOne(SingleLinked head){
		SingleLinked cur = head;
		while(cur.next!=null){
			cur = cur.next;
		}
		return cur;
	}
	
	/**
	 * 合并两个有序链表，使新链表
	 * @return
	 */
	public static SingleLinked mergeTwoLists(SingleLinked l1, SingleLinked l2){
		if(l1==null){
			return l2; 
		}
		SingleLinked newLinked = l1;
		while(l1!=null){
			if(l1.next==null){
				l1.next = l2;
				break;
			}
			l1 = l1.next;
		}
		sortLinkList(newLinked);
		return newLinked;
	}
	
	/**
	 * 链表排序
	 * @param list
	 */
	public static void sortLinkList(SingleLinked list){
		SingleLinked i = list;
		while(i!=null){
			SingleLinked j = i.next;
			while(j!=null){
				if(i.val>j.val){
					int temp = j.val;
					j.val = i.val;
					i.val = temp;
				}
				j = j.next;
			}
			i = i.next;
		}
	}
	
	public static void main(String[] args) {
		SingleLinked s1 = new SingleLinked(1); 
		SingleLinked s3 = new SingleLinked(3);
		SingleLinked s4 = new SingleLinked(4);
		SingleLinked s0 = new SingleLinked(0);
		s1.next = s3;
		s3.next = s4;
		s4.next = s0;
//		SingleLinked newHead = mergeTwoLists(s1,s0);
		sortLinkList(s1);
		scanNode(s1);
	}
	
//	public static void main(String[] args) {
//		SingleLinked s1 = new SingleLinked(1);
//		SingleLinked s2 = new SingleLinked(2);
//		SingleLinked s3 = new SingleLinked(3);
//		SingleLinked s4 = new SingleLinked(4);
//		s1.next = s2;
//		s2.next = s3;
//		s3.next = s4;
//		deleteNode(s2);
//		System.out.println("链表最后一个节点的值为："+getLastOne(s1).val);
//		SingleLinked head = reverseList(s1);
//		scanNode(head);
//	}
}
