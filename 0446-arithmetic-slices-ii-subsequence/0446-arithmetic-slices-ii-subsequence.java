class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        long result = 0;
        
        // dp[i] = map of {difference -> count of weak arithmetic subsequences ending at i with that difference}
        HashMap<Long, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - (long) nums[j];
                
                // How many chains end at j with this same difference?
                int countAtJ = dp[j].getOrDefault(diff, 0);
                
                // Those chains can be extended to i -> real arithmetic subsequences (length >= 3)
                result += countAtJ;
                
                // Update dp[i]: inherit j's count, plus 1 for the new pair (j, i)
                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + countAtJ + 1);
            }
        }
        
        return (int) result;
    }
}