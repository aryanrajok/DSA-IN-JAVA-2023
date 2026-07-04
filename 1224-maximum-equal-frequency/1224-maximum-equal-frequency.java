class Solution {
    public int maxEqualFreq(int[] nums) {
        int n = nums.length;
        int[] count = new int[100001];   // count[num] = frequency of that number
        int[] freq = new int[n + 2];     // freq[c] = how many numbers have frequency c
        int maxFreq = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            // Step A: remove old frequency count before updating
            if (count[num] > 0) {
                freq[count[num]]--;
            }

            // Step B: increase this number's frequency
            count[num]++;
            freq[count[num]]++;

            // Step C: update the running maximum frequency
            maxFreq = Math.max(maxFreq, count[num]);

            int length = i + 1;

            // Check the 3 valid situations
            if (maxFreq == 1) {
                ans = length;
            } else if (freq[maxFreq] == 1 &&
                       freq[maxFreq - 1] * (maxFreq - 1) == length - maxFreq) {
                ans = length;
            } else if (freq[1] == 1 &&
                       maxFreq * freq[maxFreq] == length - 1) {
                ans = length;
            }
        }

        return ans;
    }
}