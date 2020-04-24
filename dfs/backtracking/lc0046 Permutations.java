// 0ms 100%
class Solution {
    ArrayList<List<Integer>> arr;
    int[] nums, idx;
    int l;
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        l = nums.length;
        idx = new int[l];
        used = new boolean[l];
        arr = new ArrayList();
        dfs(0);
        return arr;
    }
    void dfs(int i){
        if(i == l){
            ArrayList<Integer> r = new ArrayList();
            for(int j = 0; j < l; j++){
                r.add(nums[idx[j]]);
            }
            arr.add(r);
        }
        else{
            for(int j = 0; j < l; j++){
                if(used[j]) continue;
                used[j] = true;
                idx[i] = j;
                dfs(i+1);
                used[j] = false;
            }
        }
    }
}
