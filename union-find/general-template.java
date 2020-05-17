class DSU{
    int[] p, r;
    int l, n; // l is the size, n is number of subgraphs, usually l is not needed;
    DSU(int l){
        this.l = l;
        n = l;
        p = new int[l];
        r = new int[l];
        for(int i = 0; i < l; i++) p[i] = i;
    }
    void union(int x, int y){
        if(x < 0 || x >= l || y < 0 || y >= l) return;
        int px = find(x), py = find(y);
        if(px == py) return;
        if(r[px] > r[py]){
            p[py] = px;
        }
        else if(r[px] < r[py]){
            p[px] = py;
        }
        else{
            r[px]++;
            p[py] = px;
        }
        n--; // count the number of subgraphs
    }
    int find(int x){
        if(x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
