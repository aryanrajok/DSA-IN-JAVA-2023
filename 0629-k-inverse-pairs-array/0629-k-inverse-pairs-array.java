class Solution {
    public int kInversePairs(int n, int k) {
        final int MOD = 1_000_000_007;
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1; // base case: 0 elements, 0 inverse pairs = 1 way

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1; // 0 inverse pairs always has exactly 1 way
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
                if (j - i >= 0) {
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + MOD) % MOD;
                }
            }
        }
        return dp[n][k];
    }
}