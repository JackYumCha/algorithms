// https://leetcode.com/problems/binary-tree-postorder-traversal/
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
    public List<Integer> postorderTraversal(TreeNode root) {
        arr = new ArrayList();
        dfs(root);
        return arr;
    }
    void dfs(TreeNode n){
        if(n == null) return;
        dfs(n.left);
        dfs(n.right);
        arr.add(n.val);
    }
}
