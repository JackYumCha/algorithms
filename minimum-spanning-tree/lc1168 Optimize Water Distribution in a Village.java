class Solution {
    static Comparator edgeComparator = new Comparator(){
        @Override
        public int compare(Object o1, Object o2){
            Edge e1 = (Edge)o1, e2 = (Edge)o2;
            return Integer.compare(e1.cost, e2.cost);
        }
    };

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        PriorityQueue<Edge> q = new PriorityQueue(edgeComparator);
        // assume water source is the vertex 0
        for(int i = 0; i < n; i++){
            q.add(new Edge(0, i + 1, wells[i]));
        }
        for(int[] p : pipes){
            q.add(new Edge(p[0], p[1], p[2]));
        }
        DSU dsu = new DSU(n + 1); // n houses + water source (0)
        int cost = 0;
        while(dsu.n > 1){
            Edge e = q.remove();
            if(dsu.find(e.from) == dsu.find(e.to)) continue;
            cost += e.cost;
            dsu.union(e.from, e.to);
        }
        return cost;
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


/*


cost(water -> house(i)) => wells[i]



*/
