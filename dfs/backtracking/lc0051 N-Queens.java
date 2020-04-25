// 1ms 99.76% both methods work within 1ms

// using int instead of boolean[]
class Solution {
    int col, up, down; // row, 
    int n;
    ArrayList<List<String>> result;
    int[] ci; // column indices
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        // row = new boolean[n];
        // col = 0;
        // up = 0;
        // down = 0;
        result = new ArrayList();
        ci = new int[n];
        dfs(0);
        return result;
    }
    
    void dfs(int r){ // r is the row index
        if(r == n){
            ArrayList<String> list = new ArrayList();
            StringBuilder stb;
            for(int c: ci){
                stb = new StringBuilder();
                for(int j = 0; j < n; j++){
                    stb.append(j == c ? 'Q' : '.');
                }
                list.add(stb.toString());
            }
            result.add(list);
        }
        else{
            for(int c = 0; c < n; c++){
                // test if (r,c) is available
                if(( (col>>c) & 1) == 1 || ( (up>>(r+c)) & 1) == 1 || ( (down>>(n-1-r+c)) & 1) == 1) continue;
                col |= (1 << c);
                up |= (1 << (r+c));
                down |= (1 << (n-1-r+c));
                ci[r] = c;
                dfs(r+1);
                col &= -1 ^ (1 << c);
                up &=  -1 ^ (1 << (r+c));
                down &= -1 ^ (1 << (n-1-r+c));
            }
        }
    }
}

// using boolean[]
class Solution {
    boolean[] col, up, down; // row, 
    int n;
    ArrayList<List<String>> result;
    int[] ci; // column indices
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        // row = new boolean[n];
        col = new boolean[n];
        up = new boolean[2*n-1];
        down = new boolean[2*n-1];
        result = new ArrayList();
        ci = new int[n];
        dfs(0);
        return result;
    }
    
    void dfs(int r){ // r is the row index
        if(r == n){
            ArrayList<String> list = new ArrayList();
            StringBuilder stb;
            for(int c: ci){
                stb = new StringBuilder();
                for(int j = 0; j < n; j++){
                    stb.append(j == c ? 'Q' : '.');
                }
                list.add(stb.toString());
            }
            result.add(list);
        }
        else{
            for(int c = 0; c < n; c++){
                // test if (r,c) is available
                if(col[c] || up[r+c] || down[n-1-r+c]) continue;
                col[c] = true;
                up[r+c] = true;
                down[n-1-r+c] = true;
                ci[r] = c;
                dfs(r+1);
                col[c] = false;
                up[r+c] = false;
                down[n-1-r+c] = false;
            }
        }
    }
}
