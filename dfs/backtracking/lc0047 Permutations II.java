// 1ms 99.30%
class Solution {
    ArrayList<List<Integer>> arr;
    int[] nums;
    boolean[] used;
    int[] r;
    int l;
    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums = nums;
        l = nums.length;
        arr = new ArrayList();
        Arrays.sort(nums);
        r = new int[l];
        used = new boolean[l];
        dfs(0, 0, -1);
        return arr;
    }
    void dfs(int i, int from, int last){
        if(i == l){
            ArrayList<Integer> a = new ArrayList(l);
            for(int n : r) a.add(n);
            arr.add(a);
        }
        else{
            if(nums[i] == last){
                for(int j = from; j < l; j++){
                    if(used[j]) continue;
                    used[j] = true;
                    r[j] = nums[i];
                    dfs(i+1, j+1, nums[i]);
                    used[j] = false;
                }
            }
            else{
                for(int j = 0; j < l; j++){
                    if(used[j]) continue;
                    used[j] = true;
                    r[j] = nums[i];
                    dfs(i+1, j+1, nums[i]);
                    used[j] = false;
                }
            }
        }
    }
}
