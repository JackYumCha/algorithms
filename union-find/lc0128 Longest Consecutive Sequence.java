class Solution {
    // O(n)
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap();
        int index = 0;
        for(int n: nums){
            if(map.containsKey(n)) continue;
            map.put(n, index);
            index++;
        }
        DSU dsu = new DSU(index);
        for(int n: map.keySet()){
            if(map.containsKey(n + 1)) dsu.union(map.get(n), map.get(n + 1));
            if(map.containsKey(n - 1)) dsu.union(map.get(n), map.get(n - 1));
        }
        int[] counts = new int[index];
        for(int i = 0; i < index; i++){
            counts[dsu.find(i)]++;
        }
        int max = 0;
        for(int count: counts){
            max = Math.max(max, count);
        }
        return max;
    }
}


class DSU{
    int[] p, r;
    int l, n;
    DSU(int l){
        this.l = l;
        n = l;
        p = new int[l];
        r = new int[l];
        for(int i = 0 ; i <l ; i++) p[i] = i;
    }
    void union(int x, int y){
        if(x == y) return;
        int px = find(x), py = find(y);
        if(px == py) return;
        if(r[px] > r[py]) p[py] = px;
        else if(r[px] < r[py]) p[px] = py;
        else{
            r[px]++;
            p[py] = px;
        }
        n--;
    }
    int find(int x){
        if(x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}

/*
// sort method

class Solution {
    // O(n*log(n))
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums); // O(n*log(n))
        Integer last = null;
        int max = 0, length = 0;
        for(int n: nums){
            if(last != null && last == n) continue;
            if(last == null || last < n - 1){
                max = Math.max(max, length);
                length = 1;
            }
            else{
                length++;
            }
            last = n;
        }
        max = Math.max(max, length);
        return max;
    }
}

*/
