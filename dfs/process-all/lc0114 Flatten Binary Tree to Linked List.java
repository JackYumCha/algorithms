// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// 0ms 100%
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    TreeNode head;
    public void flatten(TreeNode root) {
        dfs(root);
    }
    
    void dfs(TreeNode n){
        // do nothing if null
        if(n == null) return;
        
        // need to hold reference to left and right child as it would be modified in append operation
        TreeNode left = n.left, right = n.right;
        n.left = null;
        n.right = null;
        
        // preorder root -> left -> right
        append(n);
        dfs(left);
        dfs(right);
    }
    
    // append as right child
    void append(TreeNode n){
        if(head == null){
            head = n;
        }
        else{
            head.right = n;
            head = head.right;
        }
    }
}
