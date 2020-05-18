class Solution {
    static Comparator cellComparator = new Comparator(){
        @Override
        public int compare(Object o1, Object o2){
            Cell c1 = (Cell)o1, c2 = (Cell)o2;
            return Integer.compare(c2.v, c1.v);
        }
    };
    public int maximumMinimumPath(int[][] A) {
        PriorityQueue<Cell> q = new PriorityQueue(cellComparator);
        int cr = A.length, cc = A[0].length;
        for(int r = 0; r < cr; r++){
            for(int c = 0; c < cc; c++){
                q.add(new Cell(r, c, A[r][c]));
            }
        }
        DSU dsu = new DSU(cr * cc);
        int first = 0, last = cr * cc - 1;
        boolean[][] used = new boolean[cr][cc];
        int score = A[0][0];
        while(dsu.find(first) != dsu.find(last)){
            Cell c = q.remove();
            if(c.r > 0 && used[c.r-1][c.c]) dsu.union(c.r * cc + c.c, (c.r - 1) * cc + c.c);
            if(c.c > 0 && used[c.r][c.c-1]) dsu.union(c.r * cc + c.c, c.r * cc + c.c - 1);
            if(c.r + 1 < cr && used[c.r+1][c.c]) dsu.union(c.r * cc + c.c, (c.r + 1) * cc + c.c);
            if(c.c + 1 < cc && used[c.r][c.c+1]) dsu.union(c.r * cc + c.c, c.r * cc + c.c + 1);
            used[c.r][c.c] = true;
            score = c.v;
        }
        return score;
    }
}

class Cell{ 
    int r, c, v;
    Cell(int r, int c, int v){
        this.r = r;
        this.c = c;
        this.v = v;
    }
}

// Disjoint Set/Union Find
class DSU{
    int[] p, r;
    int l, n;
    DSU(int l){
        this.l = l;
        n = l;
        p = new int[l];
        r = new int[l];
        for(int i = 0; i < l; i++) p[i] = i;
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
