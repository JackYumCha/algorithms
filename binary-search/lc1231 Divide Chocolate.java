// 5-11ms
class Solution {
    
    int[] sw;
    int k;
    // O(n * log(n))
    public int maximizeSweetness(int[] sweetness, int K) {
        sw = sweetness;
        k = K + 1;
        int total = 0;
        for(int s : sw) total += s;
        int left = 0, right = total / k + 1;
        while(left < right){ //(O(log(n) * n))
            int middle = left + (right - left) / 2;
            int g = isGreaterThan(middle);
            if(g == 0){
                return middle;
            }
            else if(g > 0){
                right = middle;
            }
            else {
                left = middle + 1;
            }
        }
        return left;
    }
    
    // O(n) O(2 * n)
    int isGreaterThan(int middle){
        int c0 = countCuts(middle), c1 = countCuts(middle + 1);
        if(c0 >= k && c1 < k) return 0;
        return c1 >= k ? -1: 1;
    }
    
    // O(n)
    int countCuts(int size){
        int c = 0, p = 0;
        for(int s: sw){ // O(n)
            p += s;
            if(p >= size){
                c++;
                p = 0;
            }
        }
        return c;
    }
}
