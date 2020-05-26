// 0ms 100%
class Solution {
    int[] ns;
    int n;
    public int findDuplicate(int[] nums) {
        ns = nums;
        n = ns.length - 1; 
        for(int i = 1; i <= n + 1; i++){
            int k = find(i, true);    
            if(k > 0) return k;
        }
        return -1;
    }
    // if not found return 0 or -1, if found, return the number
    int find(int index, boolean start){
        int j = ns[index - 1];
        if(j == -1){
            return start ? -1 : index;
        }
        if(j == 0){
            ns[index - 1] = -1;
            return -1;
        }
        ns[index - 1] = start ? 0 : -1; // use -1 to indicated index has appeared
        if(j == index){
            ns[index - 1] = -1;
            return start ? -1 : index;
        }
        else{
            return find(j, false);
        }
    }
}
