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
                idx[i] = j; // idx[j] = i; both will work
                dfs(i+1);
                used[j] = false;
            }
        }
    }
}

// swap - permute
class Solution {
    ArrayList<List<Integer>> arr;
    int[] nums;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        arr = new ArrayList();
        dfs(0, nums.length - 1);
        return arr;
    }
    void dfs(int from, int to){
        if(from == to){
            ArrayList<Integer> a = new ArrayList();
            for(int n: nums) a.add(n);
            arr.add(a);
        }
        else{
            for(int i = from; i <= to; i++){
                swap(from, i);
                dfs(from + 1, to);
                swap(from, i);
            }
        }
    }
    void swap(int a, int b){
        int c = nums[b];
        nums[b] = nums[a];
        nums[a] = c;
    }
}
