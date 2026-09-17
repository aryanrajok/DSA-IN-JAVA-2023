class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = 1000000;

        int[] best = new int[n];

        int left = 0;
        int sum = 0;
        int ans = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {

                int len = right - left + 1;

                // If there was another valid subarray before this one
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, len + best[left - 1]);
                }

                // Store the shortest valid subarray ending before/right here
                best[right] = len;
            } else {
                best[right] = INF;
            }

            // Carry forward the minimum length seen so far
            if (right > 0) {
                best[right] = Math.min(best[right], best[right - 1]);
            }
        }

        return ans == INF ? -1 : ans;
    }
}