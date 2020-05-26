// 0ms 100%
class Solution {
    int[] ns;
    int n;
    public int firstMissingPositive(int[] nums) {
        ns = nums;
        n = ns.length;
        for(int i = 1; i <= n; i++){
            find(i, true);
        }
        for(int i = 1; i <= n; i++){
            if(ns[i-1] != i) return i;
        }
        return n + 1;
    }
    int find(int index, boolean start){
        if(index > n || index <= 0) return -1;
        int j = ns[index - 1];
        ns[index - 1] = start ? -1 : index;
        if(j == index){
            ns[index - 1] = j;
            return -1;
        }
        else{
            return find(j, false);
        }
    }
}
