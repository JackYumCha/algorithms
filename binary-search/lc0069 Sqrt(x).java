class Solution {
    long x;
    public int mySqrt(int x) {
        this.x = (long)x;
        long left = 0L, right = (1L<<16);
        while(left < right){
            long middle = left + (right - left) / 2L;
            long g = isGreaterThan(middle);
            if(g == 0L){
                return (int)middle;
            }
            else if(g > 0L){
                right = middle;
            }
            else{
                left = middle + 1;
            }
        }
        return (int)left;
    }
    
    long isGreaterThan(long middle){
        if(middle * middle <= x && (middle + 1L) * (middle+1L) > x) return 0L;
        return middle * middle - x;
    }
}
