// https://leetcode.com/problems/max-area-of-island/
// 2ms 99.34%
class Solution {
    
    int cr, cc;
    int[][] gd;
    public int maxAreaOfIsland(int[][] grid) {
        gd = grid;
        cr = gd.length;
        if(cr == 0) return 0;
        cc = gd[0].length;
        if(cc == 0) return 0;
        int max = 0;
        
        // need to scan every location once
        for(int r = 0; r < cr; r++){
            for(int c = 0; c < cc; c++){
                max = Math.max(max, dfs(r, c));
            }
        }
        return max;
    }
    
    int dfs(int r, int c){
        if(r < 0 || r >= cr || c < 0 || c >= cc || gd[r][c] == 0) return 0;
        // trick: mutate the grid itself to avoid process more than once
        gd[r][c] = 0;
        // aggregate the result, which is the sum of island value
        return 1 + dfs(r, c+1) + dfs(r,c-1) + dfs(r+1,c) + dfs(r-1,c);
    }
}
