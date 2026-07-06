class Solution {
    public boolean xorGame(int[] nums) {
        // Rule 1: if starting XOR is already 0, Alice wins instantly
        int xorAll = 0;
        for (int num : nums) {
            xorAll ^= num;
        }
        if (xorAll == 0) {
            return true;
        }
        
        // Rule 2: if array length is even, Alice always wins
        return nums.length % 2 == 0;
    }
}