/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 //1. 迭代方法
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }

        ListNode node1 = head;
        ListNode node2 = head.next;

        ListNode newhead = new ListNode();
        newhead.next = head;
        ListNode pre = newhead;
        pre.next = node1;


        while (node2.next!=null && node1.next!=null){
            node1.next = node2.next;
            pre.next = node2;
            node2.next = node1;
            pre = node2;
        }


        return newhead.next;
    }
}