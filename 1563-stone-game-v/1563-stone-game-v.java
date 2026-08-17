class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        
        int[][] dp = new int[n][n];
        int[][] memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);
        
        return solve(0, n - 1, prefix, memo);
    }
    
    private int solve(int i, int j, int[] prefix, int[][] memo) {
        if (i == j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        
        int best = 0;
        for (int k = i; k < j; k++) {
            int leftSum = prefix[k + 1] - prefix[i];
            int rightSum = prefix[j + 1] - prefix[k + 1];
            
            int score;
            if (leftSum < rightSum) {
                score = leftSum + solve(i, k, prefix, memo);
            } else if (leftSum > rightSum) {
                score = rightSum + solve(k + 1, j, prefix, memo);
            } else {
                score = leftSum + Math.max(solve(i, k, prefix, memo), solve(k + 1, j, prefix, memo));
            }
            best = Math.max(best, score);
        }
        
        memo[i][j] = best;
        return best;
    }
}