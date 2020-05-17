class Solution {
    // O(log(m)*log(n))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l = nums1.length + nums2.length;
        if((l&1) == 1){
            Integer median = findKthInFirstArray(nums1, nums2, l/2);
            if(median == null) median = findKthInFirstArray(nums2, nums1, l/2);
            return (double) median;
        }
        else{
            Integer median1 = findKthInFirstArray(nums1, nums2, l/2 - 1), median2 = findKthInFirstArray(nums1, nums2, l/2);
            if(median1 == null) median1 = findKthInFirstArray(nums2, nums1, l/2 - 1);
            if(median2 == null) median2 = findKthInFirstArray(nums2, nums1, l/2);
            return ((double)median1 + (double)median2) * 0.5d;
        }
    }
    
    Integer findKthInFirstArray(int[] a1, int[] a2, int k){
        int left = 0, right = a1.length;
        while(left < right){
            int middle = left + (right - left) / 2;
            int g = isGreaterThan(middle, a1, a2, k);
            if(g == 0){
                return a1[middle];
            }
            else if(g > 0){
                right = middle;
            }
            else{
                left = middle + 1;
            }
        }
        return null;
    }
    
    int isGreaterThan(int middle, int[] a1, int[] a2, int k){
        int v = a1[middle];
        int countLessThanV1 = lessThan(a1, v), countNoGreaterThanV1 = noGreaterThan(a1, v),
            countLessThanV2 = lessThan(a2, v), countNoGreaterThanV2 = noGreaterThan(a2, v);
        if(countLessThanV1 + countLessThanV2 <= k && countNoGreaterThanV1 + countNoGreaterThanV2 > k) return 0;
        return countLessThanV1 + countLessThanV2 < k ? -1 : 1;
    }
    
    int lessThan(int[] arr, int v){ // count number of elements < v
        int left = 0, right = arr.length;
        if(right == 0 || arr[0] > v) return 0;
        while(left < right){
            int middle = left + (right - left) / 2;
            int g = isLessThan(arr, middle, v);
            if(g == 0){
                return middle + 1;
            }
            else if(g > 0){
                right = middle;
            }
            else{
                left = middle + 1;
            }
        }
        return left;
    }
    int isLessThan(int[] arr, int middle, int v){
        if(arr[middle] < v && (middle + 1 == arr.length || arr[middle + 1] >= v)) return 0;
        return arr[middle] < v ? -1: 1;
    }
    int noGreaterThan(int[] arr, int v){ // count number of elements <= v
        int left = 0, right = arr.length;
        if(right == 0 || arr[right - 1] <= v) return right;
        while(left < right){
            int middle = left + (right - left) / 2;
            int g = isNoGreaterThan(arr, middle, v);
            if(g == 0){
                return middle;
            }
            else if(g > 0){
                right = middle;
            }
            else{
                left = middle + 1;
            }
        }
        return left;
    }
    int isNoGreaterThan(int[] arr, int middle, int v){
        if(arr[middle] > v && (middle == 0 || arr[middle - 1] <= v)) return 0;
        return arr[middle] > v ? 1: -1;
    }
}

/*

[1,3]
[2]
l = 2 + 1 = 3
index = 1 (2) = l/2

[1,3]
[2,4]
l = 2 + 2 = 4
median = (2+3)/2
index = 1,2 = l/2-1, l/2

*/
