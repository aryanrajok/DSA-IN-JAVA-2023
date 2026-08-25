class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        for (int i = 1; ; i++) {
            int x = k * i;
            if (!set.contains(x)) {
                return x;
            }
        }
    }
}