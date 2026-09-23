class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int target = total - x;

        // We need to remove everything
        if (target < 0) {
            return -1;
        }

        // If target is 0, keep nothing
        if (target == 0) {
            return n;
        }

        int left = 0;
        int sum = 0;
        int maxLength = -1;

        for (int right = 0; right < n; right++) {

            sum += nums[right];

            // Make sum <= target
            while (sum > target) {
                sum -= nums[left];
                left++;
            }

            // Found a subarray with required sum
            if (sum == target) {
                maxLength = Math.max(
                    maxLength,
                    right - left + 1
                );
            }
        }

        // No valid subarray
        if (maxLength == -1) {
            return -1;
        }

        return n - maxLength;
    }
}