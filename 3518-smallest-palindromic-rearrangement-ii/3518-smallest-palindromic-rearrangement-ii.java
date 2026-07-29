import java.math.BigInteger;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int half = n / 2;
        char mid = (n % 2 == 1) ? s.charAt(half) : '\0';

        int[] cnt = new int[26];
        for (int i = 0; i < half; i++) cnt[s.charAt(i) - 'a']++;

        BigInteger K = BigInteger.valueOf(k);

        // Compute total arrangements of the half ONCE, incrementally (cheap: multiply/divide by small ints)
        BigInteger totalWays = computeTotal(cnt);
        if (K.compareTo(totalWays) > 0) return "";

        StringBuilder halfBuilder = new StringBuilder();
        int remaining = half;

        for (int pos = 0; pos < half; pos++) {
            for (int c = 0; c < 26; c++) {
                if (cnt[c] == 0) continue;

                // O(1)-ish: candidate = totalWays * cnt[c] / remaining  (no full recomputation)
                BigInteger candidate = totalWays
                        .multiply(BigInteger.valueOf(cnt[c]))
                        .divide(BigInteger.valueOf(remaining));

                if (K.compareTo(candidate) <= 0) {
                    halfBuilder.append((char) ('a' + c));
                    totalWays = candidate;
                    cnt[c]--;
                    remaining--;
                    break;
                } else {
                    K = K.subtract(candidate);
                }
            }
        }

        String halfStr = halfBuilder.toString();
        StringBuilder res = new StringBuilder(halfStr);
        if (mid != '\0') res.append(mid);
        res.append(new StringBuilder(halfStr).reverse());
        return res.toString();
    }

    // Build the multinomial count incrementally: add one element at a time,
    // multiplying by its new "slot count" and dividing by its rank within its own group.
    // Every intermediate value stays a valid integer — no huge factorials computed directly.
    private BigInteger computeTotal(int[] cnt) {
        BigInteger result = BigInteger.ONE;
        long total = 0;
        for (int c = 0; c < 26; c++) {
            for (int j = 1; j <= cnt[c]; j++) {
                total++;
                result = result.multiply(BigInteger.valueOf(total)).divide(BigInteger.valueOf(j));
            }
        }
        return result;
    }
}