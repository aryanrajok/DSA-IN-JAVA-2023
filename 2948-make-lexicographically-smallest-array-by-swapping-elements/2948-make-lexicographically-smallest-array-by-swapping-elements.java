class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);

        int[] result = new int[n];
        int i = 0;
        while (i < n) {
            int j = i;
            // extend the group while consecutive sorted values differ by <= limit
            while (j + 1 < n && nums[idx[j + 1]] - nums[idx[j]] <= limit) j++;

            // collect original indices in this group, sort them
            int[] groupIndices = new int[j - i + 1];
            for (int k = i; k <= j; k++) groupIndices[k - i] = idx[k];
            Arrays.sort(groupIndices);

            // assign sorted values to sorted original indices
            for (int k = 0; k < groupIndices.length; k++) {
                result[groupIndices[k]] = nums[idx[i + k]];
            }
            i = j + 1;
        }
        return result;
    }
}