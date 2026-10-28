
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Maximum grid value is 1023, so there are 1024 XOR states
        boolean[][] dp = new boolean[1024][n];

        // Start before processing the first cell with XOR = 0
        dp[0][0] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[] current = new boolean[1024];

                if (i == 0 && j == 0) {
                    current[grid[i][j]] = true;
                } else {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[x][j] || (j > 0 && dp[x][j - 1])) {
                            current[x ^ grid[i][j]] = true;
                        }
                    }
                }

                for (int x = 0; x < 1024; x++) {
                    dp[x][j] = current[x];
                }
            }
        }

        for (int x = 0; x < 1024; x++) {
            if (dp[x][n - 1]) {
                return x;
            }
        }

        return 0;
    }
}
