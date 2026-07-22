class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        int maxSum = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];   // slide the window
            maxSum = Math.max(maxSum, sum); // track best sum
        }
        
        return (double) maxSum / k;
    }
}