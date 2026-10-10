class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {

        long[] diff = new long[nums1.length];
        long total = 0;

        for (int i = 0; i < nums1.length; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
        }

        long k = (long) k1 + k2;

        // If all differences can become zero
        if (total <= k) {
            return 0;
        }

        // Count differences from largest to smallest
        long[] count = new long[100001];

        for (long d : diff) {
            count[(int) d]++;
        }

        for (int d = 100000; d > 0 && k > 0; d--) {

            if (count[d] == 0) {
                continue;
            }

            long move = Math.min(count[d], k);

            count[d] -= move;
            count[d - 1] += move;

            k -= move;
        }

        long answer = 0;

        for (int d = 0; d <= 100000; d++) {
            answer += (long) d * d * count[d];
        }

        return answer;
    }
}