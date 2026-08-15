class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xorAll = 0;
        boolean allZero = true;

        for (int x : nums) {
            xorAll ^= x;
            if (x != 0) allZero = false;
        }

        if (allZero) return 0;      // sab zero hain
        if (xorAll != 0) return n;  // pura array kaam kar gaya
        return n - 1;               // ek non-zero element hata do
    }
}