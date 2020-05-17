class Solution {
    public int minSwapsCouples(int[] row) {
        // N
        // 4
        // 1 2 (2-1), 3 4 (2-1)
        // Sum(GroupSize[i]) = N
        // Sum(GroupSize[i] - 1) = N - Count(Group)
        int n = row.length / 2;
        DSU dsu = new DSU(n);
        for(int i = 0; i < n; i++){
            dsu.union(row[i * 2] / 2, row[i * 2 + 1] / 2);
        }
        return n - dsu.n;
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
