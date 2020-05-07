// 38ms 88.21%
class Solution {
    public int minJumps(int[] arr) {
        int l = arr.length;
        if(l == 1) return 0;
        Integer[] steps = new Integer[l];
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap();
        for(int i = 0; i < l; i++){
            int j = arr[i];
            if(hm.containsKey(j)){
                hm.get(j).add(i);
            }
            else{
                ArrayList<Integer> al = new ArrayList();
                al.add(i);
                hm.put(j, al);
            }
        }
        
        LinkedList<int[]> q = new LinkedList();// int[]{index, step}
        q.offerLast(new int[]{0, 0});
        steps[0] = 0;
        while(!q.isEmpty()){
            int[] p = q.pollFirst();
            int j = arr[p[0]], step = p[1] + 1, k;
            k = p[0] + 1;
            if(k == l - 1) return step;
            if(k < l){
                if(steps[k] == null){
                    steps[k] = step;
                    q.offerLast(new int[]{k, step});
                }
            }
            k = p[0] - 1;
            if(k == l - 1) return step;
            if(k >= 0){
                if(steps[k] == null){
                    steps[k] = step;
                    q.offerLast(new int[]{k, step});
                }
            }
            if(hm.containsKey(j)){
                for(int s : hm.get(j)){
                    if(s == l - 1) return step;
                    if(steps[s] == null){
                        steps[s] = step;
                        q.offerLast(new int[]{s, step});
                    }
                }
                hm.remove(j);
            }
        }
        return -1;
    }
    
    
}
