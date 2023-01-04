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
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> listNum= new ArrayList<>();
        ListNode p = head;
        while(p!=null){
            listNum.add(p.val);
            p = p.next;
        }
        int size = listNum.size();

        for(int i=0;i<size;i++){
            if(listNum.get(i)!=listNum.get(size-1-i)){
                return false;
            }
        }
        return true;
    }
}