public class PageQuery {
    /*
     0 1 2 3
   0 1   1 0
   1     0 1
   2
   3       1

    [0,0] + [1,2] -> [0,2]


     */
    Integer[][] dp;
    HashMap<Integer, HashSet<Integer>> startWith  = new HashMap(), endWith = new HashMap();
    public void load(int[][] ranges, int n){
        dp = new Integer[n][n];
        LinkedList<int[]> q = new LinkedList();
        for(int[] r: ranges){
            q.offerLast(r);
        }

        while(!q.isEmpty()){
            int[] r = q.pollFirst();
            int from = r[0]-1, to = r[1]-1, odd = r[2];
            dp[from][to] = odd;
            if(startWith.containsKey(from)){
                for(int to2: startWith.get(from)){
                    int from3 = Math.min(to, to2) + 1, to3 = Math.max(to, to2);
                    if(dp[from3][to3] == null){
                        q.offerLast(new int[]{from3, to3 , dp[from][to2] ^ odd});
                    }
                }
            }
            if(endWith.containsKey(to)){
                for(int from2: endWith.get(to)){
                    int from3 = Math.min(from, from2), to3 = Math.max(from, from2) - 1;
                    if(dp[from3][to3] == null){
                        q.offerLast(new int[]{from3, to3 , dp[from2][to] ^ odd});
                    }
                }
            }
            addStart(from, to);
            addEnd(from, to);
            if(startWith.containsKey(to + 1)){
                for(int to2: startWith.get(to + 1)){
                    int from3 = from, to3 = to2;
                    if(dp[from3][to3] == null){
                        q.offerLast(new int[]{from3, to3 , dp[to + 1][to2] ^ odd});
                    }
                }
            }
            if(endWith.containsKey(from - 1)){
                for(int from2: endWith.get(from - 1)){
                    int from3 = from2, to3 = to;
                    if(dp[from3][to3] == null){
                        q.offerLast(new int[]{from3, to3 , dp[from2][from - 1] ^ odd});
                    }
                }
            }
        }

    }

    public Boolean query(int from, int to){
        return dp[from][to] == null ? null : dp[from][to] == 1;
    }

    void addStart(int from, int to){
        if(startWith.containsKey(from)){
            startWith.get(from).add(to);
        }
        else{
            HashSet<Integer> hs = new HashSet();
            hs.add(to);
            startWith.put(from, hs);
        }
    }
    void addEnd(int from, int to){
        if(endWith.containsKey(to)){
            endWith.get(to).add(from);
        }
        else{
            HashSet<Integer> hs = new HashSet();
            hs.add(from);
            endWith.put(to, hs);
        }
    }

}
