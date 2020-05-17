class Solution{

    public int binarySearch(){
        int left = 0, right = L; // 0 <= index < L (left inclusive, right exclusive)
        while(left < right){
            int middle = left + (right - left) / 2; // calculate the middle
            int g = isGreaterThan(middle); // test the middle
            if(g == 0){ // if found
                return middle;
            }
            else if(g > 0){ // if middle is greater than the target
                right = middle; // right is the upper bound (exclusive)
            }
            else { // g < 0
                left = middle + 1; // left is the lower bound (inclusive)
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
