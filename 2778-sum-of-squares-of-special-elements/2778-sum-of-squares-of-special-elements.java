class Solution {
    public int sumOfSquares(int[] nums) {

        int n = nums.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {

            // Special element: its 1-based index divides n
            if (n % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }

        return sum;
    }
}