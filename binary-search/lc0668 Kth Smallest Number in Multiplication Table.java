// 15-30ms
class Solution {
    int m, n, k;
    // O(n * log(m))
    public int findKthNumber(int m, int n, int k) {
        this.m = Math.max(m, n);
        this.n = Math.min(m, n);
        this.k = k;
        int left = 1, right = m * n + 1;
        while(left < right){ //O(log(m) * n)
            int middle = left + (right - left) / 2;
            int g = isGreaterThan(middle); // O(n)
            if(g == 0) return middle;
            if(g > 0) right = middle;
            else left = middle + 1;
        }
        return left;
    }
    
    // O(n)
    int isGreaterThan(int middle){
        int lt = 0, eq = 0;
        for(int r = 1; r <= n; r++){
            int c = middle / r; // 1 <= c <= m;
            if(c > m){
                lt += m;
            }
            else{
                lt += c;
                if(middle % r == 0){
                    lt--;
                    eq++;
                }
            }
        }
        if(lt < k && lt + eq >= k) return 0;
        return lt < k ? -1 : 1;
    }
}
