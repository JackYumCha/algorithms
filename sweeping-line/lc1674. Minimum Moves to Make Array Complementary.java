//5ms 100%
class Solution {
    public int minMoves(int[] nums, int limit) {
        int[] delta = new int[2 * limit + 2];
        int n = nums.length, h = n / 2, min = n;
        for(int i = 0; i < h; i++){
            int a = nums[i], b = nums[n - i - 1];
            delta[a + b]--;
            delta[a + b + 1]++;
            delta[Math.min(a + 1, b + 1)]--;
            delta[Math.max(a + limit, b + limit) + 1]++;
        }
        for(int i = 2; i <= limit * 2 + 1; i++){
            n += delta[i];
            min = Math.min(n, min);
        }
        return min;
    }
}
