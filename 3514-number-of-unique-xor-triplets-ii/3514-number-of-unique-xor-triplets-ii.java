class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int x : nums) max = Math.max(max, x);
        // XOR of two numbers up to 'max' needs the next power of two minus 1
        int bound = 1;
        while (bound <= max) bound <<= 1;
        // bound is now > max, and bound-1 covers all bits used

        boolean[] pairXor = new boolean[bound]; // possible values of nums[i]^nums[j]
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairXor[nums[i] ^ nums[j]] = true;
            }
        }

        boolean[] result = new boolean[bound];
        for (int x : nums) {
            for (int v = 0; v < bound; v++) {
                if (pairXor[v]) {
                    result[x ^ v] = true;
                }
            }
        }

        int count = 0;
        for (boolean b : result) if (b) count++;
        return count;
    }
}