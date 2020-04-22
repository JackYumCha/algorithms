// https://leetcode.com/problems/balanced-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1ms 50.36%
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfs(root)[0] == 1;
    }
    static int[] nullReturn = new int[] {1, 0}, failReturn = new int[]{0, 0};
    
    // return type int[] {balanced, depth}
    int[] dfs(TreeNode n){
        // computer result for current level and terminate when there is no children
        if(n == null) return nullReturn;
        
        // compute result for current level by aggregating results from children
        int[] left = dfs(n.left), right = dfs(n.right);
        if(left[0] == 0 || right[0] == 0 || Math.abs(left[1] - right[1]) > 1) return failReturn;
        
        return new int[]{1, Math.max(left[1], right[1]) + 1};
    }
}

// 0ms 100%
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != null;
    }
    
    // return null for fail, int for valid
    Integer dfs(TreeNode n){
        // computer result for current level and terminate when there is no children
        if(n == null) return 0;
        
        // compute result for current level by aggregating results from children
        Integer left = dfs(n.left), right = dfs(n.right);
        if(left == null || right == null || Math.abs(left - right) > 1) return null;
        
        return Math.max(left, right) + 1;
    }
}
