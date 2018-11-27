package com.algorithm.data.singlelinked;

import com.alibaba.fastjson.JSONObject;

/**
 * 带有环的链表
 */
//    E--D
//    |  |
// A--B--C
public class LinkedListCycle {

    /**
     * 判断一个单链表是否带有环
     * 使用快慢指针，慢指针一次走一步，快指针一次走两步
     * 如果快指针探测到节点有空值，直接返回false
     * 如果慢指针追上了快指针，说明有环，返回true
     * @param head 头结点
     * @return 是否带有环
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }


    /**
     * https://blog.csdn.net/l294265421/article/details/50478818
     * 寻找环的起始节点
     * 从头结点开始，慢指针一次走一步，快指针一次走两步，直到相遇
     * 两指针相遇节点为C，环入口为B，链表头结点为A
     * 设|AB|=z,|BC|=x,|CB|=y，那么
     * 慢指针走到C的距离s=z+x
     * 快指针走到C的距离2s=z+x+nR，其中R表示环的长度R=x+y
     * 综上z=nr-x=(n-1)R+y
     *
     * 此时，让慢指针回到到A,快指针停在C上，都以相同步进1开始走，由z=y+(n-1)R可以得出，
     * 慢指针走过z到达环入口B时过程中，快指针先走y的距离到达入口B，又走了(n-1)圈回到B，此时两节点在环入口相遇。
     *
     * 因此可以在找到相遇节点C后，让慢指针回到到A,快指针停在C上，以相同步进行走，一旦相遇即是环的入口
     * @param head 头结点
     * @return 环的起始节点
     */
    public static ListNode detectCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        // 寻找相遇节点
        while(true){
            if(fast == null || fast.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        // slow回到head
        slow = head;
        while(true){
            if(slow == fast){
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
        }
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = root;
        System.out.println(JSONObject.toJSONString(detectCycle(root)));
    }
}
