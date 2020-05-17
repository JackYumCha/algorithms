class Solution {
    int[][] matrix;
    int k, n;
    public int kthSmallest(int[][] matrix, int k) {
        this.matrix = matrix;
        this.k = k;
        n = matrix.length;
        long left = matrix[0][0], right = matrix[n-1][n-1] + 1;
        while(left < right){
            long middle = left + (right - left) / 2;
            long g = isGreaterThan(middle);
            if(g == 0L){
                return (int)middle;
            }
            else if(g > 0L){
                right = middle;
            }
            else{
                left = middle + 1L;
            }
        }
        return (int)left;
    }
    long isGreaterThan(long middle){
        int lt = 0, eq = 0, t = (int)middle;
        for(int i = 0; i < n; i++){
            int j = Arrays.binarySearch(matrix[i], t);
            if(j >= 0){
                int m = j, s = j;
                eq++;
                while(m - 1 > 0 && matrix[i][m - 1] ==  t){
                    eq++;
                    s--;
                    m--;
                }
                lt += s;
                m = j;
                while(m + 1 < n && matrix[i][m + 1] == t){
                    eq++;
                    m++;
                }
            }
            else{
                j = ~j;
                lt += j;
            }
        }
        if(eq == 0){
            return lt < k? - 1 : 1;
        }
        else{
            if(lt < k && lt + eq >= k) return 0;
            return lt < k? - 1 : 1;
        }
    }
}
