class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int i = 1;
        
        // Step 1: find end of longest sequential prefix
        while (i < n && nums[i] == nums[i - 1] + 1) {
            i++;
        }
        
        // Step 2: sum of the sequential prefix (0 to i-1)
        int sum = 0;
        for (int k = 0; k < i; k++) {
            sum += nums[k];
        }
        int x = sum;
        
        // Step 3: put all elements in a set
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        
        // Step 4: find smallest missing x
        while (set.contains(x)) {
            x++;
        }
        
        return x;
    }
}