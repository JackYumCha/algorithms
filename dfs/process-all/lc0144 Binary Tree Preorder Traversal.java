// https://leetcode.com/problems/binary-tree-preorder-traversal/
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
    public List<Integer> preorderTraversal(TreeNode root) {
        arr = new ArrayList();
        dfs(root);
        return arr;
    }
    void dfs(TreeNode n){
        if(n == null) return;
        arr.add(n.val);
        dfs(n.left);
        dfs(n.right);
    }
}
