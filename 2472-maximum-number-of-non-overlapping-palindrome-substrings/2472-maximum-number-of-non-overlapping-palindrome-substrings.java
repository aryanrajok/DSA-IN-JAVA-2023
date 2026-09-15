class Solution {
    public int maxPalindromes(String s, int k) {

        int n = s.length();

        // palindrome[i][j] = true if s[i...j] is palindrome
        boolean[][] palindrome = new boolean[n][n];

        // Build palindrome table
        for (int i = n - 1; i >= 0; i--) {

            for (int j = i; j < n; j++) {

                if (s.charAt(i) == s.charAt(j)) {

                    // Length 1 or 2
                    // OR inside part is already palindrome
                    if (j - i <= 2 || palindrome[i + 1][j - 1]) {
                        palindrome[i][j] = true;
                    }
                }
            }
        }

        // dp[i] = maximum palindromes using first i characters
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            // Don't take a palindrome ending at i - 1
            dp[i] = dp[i - 1];

            // Try every starting position
            for (int j = 0; j <= i - k; j++) {

                // s[j...i-1] is a palindrome
                if (palindrome[j][i - 1]) {

                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}