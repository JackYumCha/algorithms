// https://leetcode.com/problems/binary-tree-inorder-traversal/
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
    ArrayList<Integer> arr;
    public List<Integer> inorderTraversal(TreeNode root) {
        arr = new ArrayList();
        dfs(root);
        return arr;
    }
    void dfs(TreeNode n){
        if(n == null) return;
        dfs(n.left);
        arr.add(n.val);
        dfs(n.right);
    }
}
