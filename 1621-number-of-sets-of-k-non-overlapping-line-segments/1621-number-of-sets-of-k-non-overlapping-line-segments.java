class Solution {
    public int numberOfSets(int n, int k) {

        int MOD = 1000000007;

        int[][] f = new int[n + 1][k + 1];
        int[][] g = new int[n + 1][k + 1];

        // 0 segments using 1 point
        f[1][0] = 1;

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j <= k; j++) {

                // Don't have an open segment
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % MOD;

                // Keep the current segment open
                g[i][j] = g[i - 1][j];

                if (j > 0) {

                    // Start a new segment
                    g[i][j] += f[i - 1][j - 1];
                    g[i][j] %= MOD;

                    // Continue an existing segment
                    g[i][j] += g[i - 1][j - 1];
                    g[i][j] %= MOD;
                }
            }
        }

        return (f[n][k] + g[n][k]) % MOD;
    }
}