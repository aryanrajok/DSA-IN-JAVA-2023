class Solution {
    public int sumSubseqWidths(int[] nums) {
        int MOD = 1_000_000_007;
        int n = nums.length;

        Arrays.sort(nums);

        long[] pow2 = new long[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        long result = 0;

        for (int i = 0; i < n; i++) {
            long contribution = ((long) nums[i] * pow2[i]) % MOD;
            contribution = (contribution - (long) nums[i] * pow2[n - 1 - i]) % MOD;
            result = (result + contribution) % MOD;
        }

        result = ((result % MOD) + MOD) % MOD;

        return (int) result;
    }
}