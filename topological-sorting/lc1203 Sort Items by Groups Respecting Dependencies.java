// 38ms 82.37%
class Solution {
    static Comparator cpt = new Comparator(){
        @Override
        public int compare(Object o1, Object o2){
            int[] a1 = (int[])o1, a2 = (int[])o2;
            if(a1[0] == a2[0]) return Integer.compare(a1[1], a2[1]); // compare the order of entering queue
            else return Integer.compare(a1[0], a2[0]); // compare the order of group
        }
    };
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // topological sorting + backtracking
        // topological sorting of groups + topological sorting of items
        for(int i = 0; i < n; i++){
            if(group[i] == -1){
                group[i] = m;
                m++;
            }
        }
        int[] itemIncoming = new int[n], groupIncoming = new int[m];
        ArrayList<Integer>[] nextItems = new ArrayList[n], nextGroups = new ArrayList[m];
        for(int i = 0; i < n; i++) nextItems[i] = new ArrayList();
        for(int i = 0; i < m; i++) nextGroups[i] = new ArrayList();
        for(int i = 0; i < n; i++){
            int g = group[i];
            for(int before : beforeItems.get(i)){
                itemIncoming[i]++;
                nextItems[before].add(i);
                if(g == group[before]) continue;
                int gfrom = group[before];
                groupIncoming[g]++;
                nextGroups[gfrom].add(g);
            }
        }
        int[] groupOrders = new int[m]; // group index -> order of the group
        int groupOrder = 0;
        LinkedList<Integer> gq = new LinkedList();
        for(int i = 0; i < m; i++){
            if(groupIncoming[i] == 0){
                gq.add(i); // add group index to queue
            }
        }
        while(!gq.isEmpty()){
            int g = gq.pollFirst();
            groupOrders[g] = groupOrder;
            groupOrder++;
            for(int next : nextGroups[g]){
                groupIncoming[next]--;
                if(groupIncoming[next] == 0){
                    gq.add(next);
                }
            }
        }
        if(groupOrder < m) return new int[0];
        PriorityQueue<int[]> q = new PriorityQueue(cpt); // int[] {order of group, order of entering queue, item}
        int orderOfEnter = 0, count = 0;
        int[] result = new int[n];
        for(int i = 0; i < n; i++){
            if(itemIncoming[i] == 0){
                q.add(new int[]{groupOrders[group[i]], orderOfEnter, i}); // add group index to queue
                orderOfEnter++;
            }
        }
        while(!q.isEmpty()){
            int[] arr = q.remove();
            int i = arr[2];
            result[count] = i;
            count++;
            for(int next : nextItems[i]){
                itemIncoming[next]--;
                if(itemIncoming[next] == 0){
                    q.add(new int[]{groupOrders[group[next]], orderOfEnter, next});
                    orderOfEnter++;
                }
            }
        }
        return count == n ? result : new int[0]; 
    }
}

/*

Item    BeforeItems Group   BeforeGroups
0                     2          
1            6        3          0
2            5        1          1(X)
3            6        0          0(X)
4          3,6        0        0,0(X)
5                     1
6                     0
7                     4
*/
