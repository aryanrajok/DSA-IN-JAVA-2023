import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1
        if (k == 1) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int x : nums) {
                freq.put(x, freq.getOrDefault(x, 0) + 1);
            }

            int ans = -1;
            for (int x : nums) {
                if (freq.get(x) == 1) {
                    ans = Math.max(ans, x);
                }
            }
            return ans;
        }

        // Case 2
        if (k == n) {
            int mx = nums[0];
            for (int x : nums) {
                mx = Math.max(mx, x);
            }
            return mx;
        }

        // Case 3
        int ans = -1;

        if (appearsOnce(nums, 0)) {
            ans = nums[0];
        }

        if (appearsOnce(nums, n - 1)) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }

    private boolean appearsOnce(int[] nums, int idx) {
        for (int i = 0; i < nums.length; i++) {
            if (i != idx && nums[i] == nums[idx]) {
                return false;
            }
        }
        return true;
    }
}