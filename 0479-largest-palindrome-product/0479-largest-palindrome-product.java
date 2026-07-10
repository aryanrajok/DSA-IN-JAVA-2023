class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) return 9;

        int upperBound = (int) Math.pow(10, n) - 1;
        int lowerBound = (int) Math.pow(10, n - 1);

        for (int left = upperBound; left >= lowerBound; left--) {
            long palindrome = createPalindrome(left);

            for (long factor = upperBound; factor >= lowerBound; factor--) {
                if (factor * factor < palindrome) break;

                if (palindrome % factor == 0) {
                    long other = palindrome / factor;
                    if (other >= lowerBound && other <= upperBound) {
                        return (int) (palindrome % 1337);
                    }
                }
            }
        }

        return -1;
    }

    private long createPalindrome(int left) {
        String leftStr = String.valueOf(left);
        String reversed = new StringBuilder(leftStr).reverse().toString();
        return Long.parseLong(leftStr + reversed);
    }
}