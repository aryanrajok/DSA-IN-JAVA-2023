class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int pos = n - 1; // fill from the back

        while (left <= right) {
            int leftVal = Math.abs(nums[left]);
            int rightVal = Math.abs(nums[right]);

            if (leftVal > rightVal) {
                result[pos] = leftVal * leftVal;
                left++;
            } else {
                result[pos] = rightVal * rightVal;
                right--;
            }
            pos--;
        }

        return result;
    }
}