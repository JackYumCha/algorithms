class Solution{

    public int binarySearch(){
    
        int left = 0, right = L; // 0 <= index < L
        while(left < right){

            int middle = left + (right - left) / 2;
            int g = isGreaterThan(middle);
            if(g == 0){
                return middle;
            }
            else if(g > 0){
                right = middle;
            }
            else { // g < 0
                left = middle + 1;
            }
        }
        return left;
    }
    
    int isGreaterThan(int middle){
        // should return 0 if found target
        // return v where v > 0 if middle is greater than the target
        // return v where v < 0 if middle is less than the target
    }
}
