// 104ms 50.37%
class Solution {
    ArrayList<Integer>[] connections;
    int[] index, group;
    int i;
    ArrayList<List<Integer>> results = new ArrayList();
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> edges) {
        connections = new ArrayList[n];
        for(int j = 0; j < n; j++) connections[j] = new ArrayList();
        for(List<Integer> edge: edges){
            int a = edge.get(0), b = edge.get(1);
            connections[a].add(b);
            connections[b].add(a);
        }
        index = new int[n];
        group = new int[n];
        Arrays.fill(index, -1);
        i = 0;
        dfs(0, -1);
        return results;
    }
    
    void dfs(int current, int from){
        index[current] = i;
        group[current] = i;
        i++;
        boolean blockFrom = true;
        for(int to : connections[current]){
            if(to == from && blockFrom){
                blockFrom = false;
                continue;
            }
            if(index[to] == -1){
                dfs(to, current);
                if(index[to] == group[to]){
                    ArrayList<Integer> edge = new ArrayList(2);
                    edge.add(current);
                    edge.add(to);
                    results.add(edge);
                }
            }
            group[current] = Math.min(group[current], group[to]);
        }
    }
}
