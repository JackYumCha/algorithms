class Solution {
    static Comparator edgeComparator = new Comparator(){
        @Override
        public int compare(Object o1, Object o2){
            Edge e1 = (Edge)o1, e2 = (Edge)o2;
            return Integer.compare(e1.cost, e2.cost);
        }
    };
    public int MST() {
        PriorityQueue<Edge> q = new PriorityQueue(edgeComparator);
        // load Edges to q
        
        DSU dsu = new DSU(n); // total number of vertices
        int cost = 0;
        while(dsu.n > 1){ // while all vertices were not connected
            Edge e = q.remove();
            if(dsu.find(e.from) == dsu.find(e.to)) continue; // if from and to are already connected, ignore this edge.
            cost += e.cost;
            dsu.union(e.from, e.to);
        }
        return cost; // return the minimum cost
    }
}

// Edge class that holds the information
class Edge{ 
    int from, to, cost;
    Edge(int from, int to, int cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
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
