// https://leetcode.com/problems/validate-binary-search-tree/
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
    
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }
    boolean dfs(TreeNode n, Integer from, Integer to){
        // compute result for current level and early termination
        if(n == null) return true;
        
        // compute result for current level and early termination
        if((from != null && n.val <= from) || (to != null && n.val >= to)) return false;
        
        // aggregate result for child level and early termination
        if(!dfs(n.left, from, n.val)) return false;
        if(!dfs(n.right, n.val, to)) return false;
        
        // return result
        return true;
    }
}
