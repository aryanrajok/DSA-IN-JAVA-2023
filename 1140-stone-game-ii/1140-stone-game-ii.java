class Solution {
    int[][] memo;
    int[] suffix;
    
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        
        memo = new int[n][n + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        
        return dp(0, 1, piles);
    }
    
    private int dp(int i, int M, int[] piles) {
        int n = piles.length;
        
        // base case: saare stones bache hue le lo
        if (i + 2 * M >= n) {
            return suffix[i];
        }
        
        if (memo[i][M] != -1) return memo[i][M];
        
        int best = 0;
        for (int X = 1; X <= 2 * M; X++) {
            // current player suffix[i] - (jo agla player uthayega starting i+X se) leta hai
            int result = suffix[i] - dp(i + X, Math.max(M, X), piles);
            best = Math.max(best, result);
        }
        
        memo[i][M] = best;
        return best;
    }
}