class Solution {
    ArrayList<Integer>[] cn; // node -> to ArrayList<Integer>
    int[] index, group;
    int i;
    // n is number of nodes, connections are arrays of int[]{from, to}
    public void stronglyConnectedComponents(int n, int[][] connections) {
        // tarjan SCC strongly connected components
        cn = new ArrayList[n];
        for(int i = 0; i < n; i++) cn[i] = new ArrayList();
        for(int[] conn: connections){
            int a = conn[0], b = conn[1];
            cn[a].add(b);
            cn[b].add(a);
        }
        index = new int[n];
        group = new int[n];
        Arrays.fill(index, -1);
        i = 0;
        dfs(0, -1);
    }
    void dfs(int from, int source){
        index[from] = i;
        group[from] = i;
        i++;
        boolean blockSource = true;
        for(int to : cn[from]){
            // only for undirected graph, because you can't go back with directed graph
            if(to == source && blockSource ) { // block only once, if there are more than one way back
                blockSource = false;
                continue;
            }
            if(index[to] == -1) dfs(to, from);
            group[from] = Math.min(group[from], group[to]);
        }
    }
}
