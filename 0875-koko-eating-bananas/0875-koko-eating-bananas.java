class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int start = 1, end = 0, mid, ans = 0;

        for (int i = 0; i < n; i++) {
            end = Math.max(end, piles[i]);
        }

        while (start <= end) {
            mid = start + (end - start) / 2;
            long total_time = 0;

            for (int i = 0; i < n; i++) {
                total_time += piles[i] / mid;
                if (piles[i] % mid != 0) total_time++;   // ceil division
            }

            if (total_time > h) {
                start = mid + 1;   // speed slow hai, badhao
            } else {
                ans = mid;         // valid hai, aur chhoti try karo
                end = mid - 1;
            }
        }
        return ans;
    }
}