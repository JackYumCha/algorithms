// 2ms 98.80%
class Solution {
    int n;
    int[][] gd;
    boolean[][] row, col, sub;
    public void solveSudoku(char[][] board) {
        n = board.length;
        gd = new int[n][n];
        row = new boolean[n][n];
        col = new boolean[n][n];
        sub = new boolean[n][n];
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                gd[r][c] = board[r][c] == '.' ? 0 : (board[r][c] - '0');
                int v = gd[r][c]-1;
                if(v < 0) continue;
                row[r][v] = true;
                col[c][v] = true;
                sub[(r/3)*3 + c/3][v] = true;
            }
        }
        dfs(0, 0);
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                board[r][c] = (char)('0' + gd[r][c]);
            }
        }
    }
    
    boolean dfs(int r, int c){
        if(c == n){
            c = 0;
            r++;
        }
        if(r == n){ // end of dfs
            return true;
        }
        else{
            if(gd[r][c] > 0){
                if(dfs(r, c+1)) return true;
            }
            else{
                for(int v = 0; v < 9; v++){
                    if(row[r][v] || col[c][v] || sub[(r/3)*3 + c/3][v]) continue;
                    row[r][v] = true;
                    col[c][v] = true;
                    sub[(r/3)*3 + c/3][v] = true;
                    gd[r][c] = v + 1;
                    if(dfs(r, c+1)) return true;
                    row[r][v] = false;
                    col[c][v] = false;
                    sub[(r/3)*3 + c/3][v] = false;
                }
                gd[r][c] = 0;
            }
            return false;
        }
    }
}
