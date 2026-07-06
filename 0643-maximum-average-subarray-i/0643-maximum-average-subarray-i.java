class Solution {
    public double findMaxAverage(int[] nums, int k) {

        // Step 1: Find the sum of the first k elements
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // Step 2: Assume this is the maximum sum
        int maxSum = sum;

        // Step 3: Slide the window
        for (int i = k; i < nums.length; i++) {

            // Remove the leftmost element
            sum = sum - nums[i - k];

            // Add the new element
            sum = sum + nums[i];

            // Update maximum sum
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        // Step 4: Return the average
        return (double) maxSum / k;
    }
}