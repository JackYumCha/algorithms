// topological sorting + union find
// 238ms 52.41%
class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        int cr = matrix.length, cc = matrix[0].length, l = cr * cc;
        DSU dsu = new DSU(l);
        for(int r = 0; r < cr; r++){
            HashMap<Integer, ArrayList<Integer>> map =new HashMap();
            for(int c = 0; c < cc; c++){
                int n = matrix[r][c];
                ArrayList<Integer> list = map.getOrDefault(n, new ArrayList());
                list.add(r * cc + c);
                map.put(n, list);
            }
            for(int n : map.keySet()){
                Integer first = null;
                for(int i : map.get(n)){
                    if(first == null){
                        first = i;
                    }
                    else{
                        dsu.union(first, i);
                    }
                }
            }
        }
        for(int c = 0; c < cc; c++){
            HashMap<Integer, ArrayList<Integer>> map =new HashMap();
            for(int r = 0; r < cr; r++){
                int n = matrix[r][c];
                ArrayList<Integer> list = map.getOrDefault(n, new ArrayList());
                list.add(r * cc + c);
                map.put(n, list);
            }
            for(int n : map.keySet()){
                Integer first = null;
                for(int i : map.get(n)){
                    if(first == null){
                        first = i;
                    }
                    else{
                        dsu.union(first, i);
                    }
                }
            }
        }
        
        Integer[] in = new Integer[l];
        ArrayList<Integer>[] out = new ArrayList[l];
        
        for(int r = 0; r < cr; r++){
            PriorityQueue<int[]> q = new PriorityQueue((a, b) -> {
                return Integer.compare(((int[])a)[0], ((int[])b)[0]);
            });
            for(int c = 0; c < cc; c++){
                q.add(new int[] {matrix[r][c], dsu.find(r * cc + c)});
            }
            int[] first = q.remove();
            int prev = first[0], pindex = first[1], current, cindex;
            if(in[pindex] == null) in[pindex] = 0;
            if(out[pindex] == null) out[pindex] = new ArrayList();
            while(!q.isEmpty()){
                int[] pair = q.remove();
                current = pair[0];
                cindex = pair[1];
                if(in[cindex] == null) in[cindex] = 0;
                if(out[cindex] == null) out[cindex] = new ArrayList();
                if(prev != current){
                    in[cindex]++;
                    out[pindex].add(cindex);
                    prev = current;
                    pindex = cindex;
                }
            }
        }
        for(int c = 0; c < cc; c++){
            PriorityQueue<int[]> q = new PriorityQueue((a, b) -> {
                return Integer.compare(((int[])a)[0], ((int[])b)[0]);
            });
            for(int r = 0; r < cr; r++){
                q.add(new int[] {matrix[r][c], dsu.find(r * cc + c)});
            }
            int[] first = q.remove();
            int prev = first[0], pindex = first[1], current, cindex;
            if(in[pindex] == null) in[pindex] = 0;
            if(out[pindex] == null) out[pindex] = new ArrayList();
            while(!q.isEmpty()){
                int[] pair = q.remove();
                current = pair[0];
                cindex = pair[1];
                if(in[cindex] == null) in[cindex] = 0;
                if(out[cindex] == null) out[cindex] = new ArrayList();
                if(prev != current){
                    in[cindex]++;
                    out[pindex].add(cindex);
                    prev = current;
                    pindex = cindex;
                }
            }
        }
        
        HashSet<Integer> layer = new HashSet(), next;
        for(int i = 0; i < l; i++){
            if(in[i] == null || in[i] > 0) continue;
            layer.add(i);
        }
        int order = 0;
        HashMap<Integer, Integer> orders = new HashMap();
        while(!layer.isEmpty()){
            next = new HashSet();
            order++;
            for(int i : layer){
                orders.put(i, order);
                for(int j : out[i]){
                    in[j]--;
                    if(in[j] == 0){
                        next.add(j);
                    }
                }
            }
            layer = next;
        }
        for(int r = 0; r < cr; r++){
            for(int c= 0 ; c < cc; c++){
                matrix[r][c] = orders.get(dsu.find(r * cc + c));
            }
        }
        return matrix;
    }
}

class DSU{
    int[] r, p;
    DSU(int l){
        r = new int[l];
        p = new int[l];
        for(int i = 0 ;i < l; i++) p[i] = i;
    }
    int find(int x){
        if(x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
    void union(int a, int b){
        if(a == b) return;
        int pa = find(a), pb = find(b);
        if(r[pa] > r[pb]) p[pb] = pa;
        else if(r[pb] > r[pa]) p[pa] = pb;
        else{
            r[pa]++;
            p[pb] = pa;
        }
    }
}

/*
-21 < 14 < 20

-19 <  4 < 19

-47 < 22 < 24

-19 <  4 < 19


*/
