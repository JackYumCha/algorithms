class Solution {
    public boolean /* List<Integer> if return order of sorting */ topologicalSorting(int n, int[][] edges) {
        int[] dependencies = new int[n]; // dependencies, record how many prerequisite dependencies a node has
        ArrayList<Integer>[] toNodes = new ArrayList[n]; // record the edges from i to other nodes
        for(int i = 0; i < n; i++){
            toNodes[i] = new ArrayList();
        }
        for(int[] edge : edges){
            dependencies[edge[1]]++;
            toNodes[edge[0]].add(edge[1]);
        }
        LinkedList<Integer> q = new LinkedList();
        int count = 0;
        ArrayList<Integer> orders = new ArrayList();
        for(int i = 0; i < n; i++){
            if (dependencies[i] == 0){
                q.add(i);
                count++;
            }
        }
        while(!q.isEmpty()){
            int k = q.pollFirst();
            orders.add(k);
            for(int d : toNodes[k]){
                dependencies[d]--;
                if(dependencies[d] == 0){
                    q.add(d);
                    count++;
                }
            }
        }
        return count == n; // true when there is a solution
        // return orders; // return the orders of a topological sorting solution
    }
}
