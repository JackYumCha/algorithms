class Solution {
    
    public int findCircleNum(int[][] M) {
        int n = M.length;
        DSU dsu = new DSU(n);
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(M[i][j] == 1){
                    dsu.union(i, j);
                }
            }
        }
        return dsu.n;
    }
}

class DSU{
    int[] p, r;
    int l, n;
    DSU(int l){
        this.l = l;
        n = l;
        p = new int[l];
        r = new int[l];
        for(int i = 0 ; i <l ; i++) p[i] = i;
    }
    void union(int x, int y){
        if(x == y) return;
        int px = find(x), py = find(y);
        if(px == py) return;
        if(r[px] > r[py]) p[py] = px;
        else if(r[px] < r[py]) p[px] = py;
        else{
            r[px]++;
            p[py] = px;
        }
        n--;
    }
    int find(int x){
        if(x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
