class Solution {
    int[] ns;
    int n;
    public int findDuplicate(int[] nums) {
        ns = nums;
        n = ns.length - 1;
        for(int i = 0; i < n; i++){
            find(i, true);
        }
        return ???
    }
    int find(int index, boolean start){
        int j = ns[index];
        // logic depends on the problem
        ns[index] = start ? -1 : index; // or set to something as a place holder
        if(j == index){
            ns[index] = j;
            return ???
        }
        else{
            return find(j, false);
        }
    }
}
