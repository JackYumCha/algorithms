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

// 5ms HashSet method 
class Solution{
    public List<List<Integer>> permute(int[] nums) {
        HashSet<Integer> hs = new HashSet();
        for(int n : nums) hs.add(n);
        result = new ArrayList();
        dfs(hs, new LinkedList());
        return result;
    }
    
    void dfs(HashSet<Integer> hs, LinkedList<Integer> ll){
        if(hs.isEmpty()){
            ArrayList<Integer> a =new ArrayList();
            for(int i : ll) a.add(i);
            result.add(a);
        }
        else{
            int[] arr = new int[hs.size()];
            int i = 0;
            for(int n: hs){
                arr[i] = n;
                i++;
            }
            for(int n: arr){
                hs.remove(n); // do
                ll.offerLast(n); // do
                dfs(hs, ll);
                ll.pollLast(); // undo
                hs.add(n); // undo
            }
        }
    }
}
