class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        dp[0] = false; // 0 stones -> current player can't move -> loses
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                // Agar koi ek move exist karta hai jisse opponent losing state me jaye
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break; // ek winning move mil gaya, aur check karne ki zarurat nahi
                }
            }
        }
        
        return dp[n];
    }
}