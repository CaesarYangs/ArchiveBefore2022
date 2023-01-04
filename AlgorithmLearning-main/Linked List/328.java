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
class Solution {
    public ListNode oddEvenList(ListNode head) {
        int mark = 1;
        ListNode p = head;
        ListNode pre = new ListNode(0);
        ListNode q = new ListNode(0);
        ListNode t = q;

        while(p!=null){
            if(mark%2==0){
                pre.next = p.next;
                p.next = null;
                
                q.next = p;
                q = p;

                p = pre.next;
                ++mark;
            }else{
                pre = p;
                p = p.next;
                ++mark;
            }
            
        }
        pre.next = t.next;

        return head;
    }
}