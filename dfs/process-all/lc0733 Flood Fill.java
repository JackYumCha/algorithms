// https://leetcode.com/problems/flood-fill/
// 1ms 49.97%
class Solution {
    // reference to the matrix
    int[][] gd;
    
    // shared parameters for dfs
    int cr, cc, color, replace;
    
    // avoid traverse more than once
    boolean[][] visited;
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        gd = image;
        cr = gd.length;
        if(cr == 0) return gd;
        cc = gd[0].length;
        if(cc == 0) return gd;
        color = gd[sr][sc];
        replace = newColor;
        visited = new boolean[cr][cc];
        dfs(sr, sc);
        return gd;
    }
    
    void dfs(int r, int c){
        if(r < 0 || r >= cr || c < 0 || c >= cc || visited[r][c] || gd[r][c] != color) return;
        gd[r][c] = replace;
        visited[r][c] = true;
        
        // traverse neighbours
        dfs(r,c+1);
        dfs(r,c-1);
        dfs(r+1,c);
        dfs(r-1,c);
    }
    
}
