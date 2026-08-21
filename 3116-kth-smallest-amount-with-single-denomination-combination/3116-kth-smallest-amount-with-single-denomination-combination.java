class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long lo = 1, hi = (long) 1e15;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (countLE(coins, mid) >= k) {
                hi = mid;       // mid amounts made honge >= k, so answer <= mid
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    // amount <= X tak kitne valid amounts ban sakte hain (inclusion-exclusion)
    private long countLE(int[] coins, long X) {
        int n = coins.length;
        long total = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcmVal = 1;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lcmVal = lcm(lcmVal, coins[i]);
                    if (lcmVal > X) { overflow = true; break; }
                }
            }
            if (overflow) continue;

            int bits = Integer.bitCount(mask);
            if (bits % 2 == 1) total += X / lcmVal;   // odd subset -> add
            else total -= X / lcmVal;                  // even subset -> subtract
        }
        return total;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}