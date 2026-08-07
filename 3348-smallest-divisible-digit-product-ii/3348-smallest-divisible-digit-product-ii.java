class Solution {
    public String smallestNumber(String num, long t) {
        int[] req = factorT(t);
        if (req == null) return "-1";

        int[] freq0 = toDigitFreq(req.clone());
        int L0 = totalLen(freq0);
        int n = num.length();

        // Case: even minimal digits exceed num's length -> must use more digits
        if (L0 > n) {
            return sortedDigits(freq0);
        }

        // prefix[i] = exponents contributed by num[0..i-1]
        int[][] prefix = new int[n + 1][4];
        for (int i = 0; i < n; i++) {
            int[] de = digitExp(num.charAt(i) - '0');
            for (int k = 0; k < 4; k++) prefix[i + 1][k] = prefix[i][k] + de[k];
        }

        int firstZero = num.indexOf('0');
        if (firstZero == -1) {
            firstZero = n;
            if (isSubset(req, prefix[n])) return num; // num itself works
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i > firstZero) continue; // can't keep a '0' unchanged
            int d = num.charAt(i) - '0';
            for (int big = d + 1; big <= 9; big++) {
                int[] de = digitExp(big);
                int[] remaining = new int[4];
                for (int k = 0; k < 4; k++)
                    remaining[k] = Math.max(0, req[k] - (prefix[i][k] + de[k]));

                int[] freq = toDigitFreq(remaining);
                int L1 = totalLen(freq);
                int space = n - 1 - i;

                if (L1 <= space) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i).append((char) ('0' + big));
                    for (int o = 0; o < space - L1; o++) sb.append('1');
                    sb.append(sortedDigits(freq));
                    return sb.toString();
                }
            }
        }

        // No fix possible within same length -> go one digit longer
        int targetLen = n + 1;
        StringBuilder sb = new StringBuilder();
        for (int o = 0; o < targetLen - L0; o++) sb.append('1');
        sb.append(sortedDigits(freq0));
        return sb.toString();
    }

    private int[] factorT(long t) {
        int[] res = new int[4];
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) { t /= primes[i]; res[i]++; }
        }
        return t == 1 ? res : null;
    }

    private int[] digitExp(int d) {
        switch (d) {
            case 2: return new int[]{1,0,0,0};
            case 3: return new int[]{0,1,0,0};
            case 4: return new int[]{2,0,0,0};
            case 5: return new int[]{0,0,1,0};
            case 6: return new int[]{1,1,0,0};
            case 7: return new int[]{0,0,0,1};
            case 8: return new int[]{3,0,0,0};
            case 9: return new int[]{0,2,0,0};
            default: return new int[]{0,0,0,0}; // digit 1 (or 0, unused here)
        }
    }

    private boolean isSubset(int[] req, int[] achieved) {
        for (int k = 0; k < 4; k++) if (achieved[k] < req[k]) return false;
        return true;
    }

    private int[] toDigitFreq(int[] req) {
        int e2 = req[0], e3 = req[1], e5 = req[2], e7 = req[3];
        int[] freq = new int[10];
        freq[9] = e3 / 2; e3 %= 2;
        freq[8] = e2 / 3; e2 %= 3;
        int min23 = Math.min(e2, e3);
        freq[6] = min23; e2 -= min23; e3 -= min23;
        freq[4] = e2 / 2; e2 %= 2;
        freq[2] = e2;
        freq[3] = e3;
        freq[5] = e5;
        freq[7] = e7;
        return freq;
    }

    private int totalLen(int[] freq) {
        int s = 0;
        for (int d = 2; d <= 9; d++) s += freq[d];
        return s;
    }

    private String sortedDigits(int[] freq) {
        StringBuilder sb = new StringBuilder();
        for (int d = 2; d <= 9; d++)
            for (int c = 0; c < freq[d]; c++) sb.append((char) ('0' + d));
        return sb.toString();
    }
}