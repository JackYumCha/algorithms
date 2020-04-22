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
