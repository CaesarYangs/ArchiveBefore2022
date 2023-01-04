/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 //双指针 链表遍历
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}

//哈希表方法
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> listset = new HashSet<ListNode>();
        ListNode temp = headA;
        while(temp!=null){
            listset.add(temp);
            temp = temp.next;
        }
        temp = headB;

        while(temp!=null){
            if(listset.contains(temp)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}