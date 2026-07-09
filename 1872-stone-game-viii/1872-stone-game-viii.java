class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        long[] prefix = new long[n];
        prefix[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // dp[n-1] = taking everything (last possible move)
        long dp = prefix[n - 1];
        long maxSuffix = dp;

        for (int i = n - 2; i >= 1; i--) {
            dp = prefix[i] - maxSuffix;          // opponent will pick the best response from i+1 onward
            maxSuffix = Math.max(maxSuffix, dp);  // roll the running max forward
        }

        return (int) maxSuffix;
    }
}