/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        helperFunction(root);
        return ans;

    }

    public int helperFunction(TreeNode node){
        if(node==null){
            return 0;
        }
        int l = helperFunction(node.left);
        int r = helperFunction(node.right);
        ans = Math.max(ans,l+r);
        return Math.max(l,r)+1; // 返回该节点为根的子树的深度
    }

}