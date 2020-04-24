// 1ms 99.30%
class Solution {
    ArrayList<List<Integer>> result;
    int l;
    int[] ns, arr;
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        ns = nums; // [1,2,3,2,1] -> [1,1,2,2,3]
        l = ns.length; 
        Arrays.sort(ns); // [1,1,2,2,3]
        arr = new int[l];
        used = new boolean[l];
        result = new ArrayList();
        dfs(0, 0, null);
        return result;
    }
    
    void dfs(int i, int from, Integer previous){
        if(i == l){
            ArrayList<Integer> a =new ArrayList();
            for(int k: arr) a.add(k);
            result.add(a);
        }
        else{
            for(int j = (previous == null || previous != ns[i]) ? 0 : from; j < l; j++){
                if(used[j]) continue;
                used[j] = true;
                arr[j] = ns[i];
                dfs(i + 1, j + 1, ns[i]);
                // arr[j] = ;
                used[j] = false;
            }
        }
    }
}
