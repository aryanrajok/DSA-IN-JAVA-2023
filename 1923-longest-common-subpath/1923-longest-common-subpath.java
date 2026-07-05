import java.util.*;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 998_244_353L;
    private static final long BASE1 = 131;
    private static final long BASE2 = 137;

    public int longestCommonSubpath(int n, int[][] paths) {
        int hi = Integer.MAX_VALUE;
        for (int[] path : paths) {
            hi = Math.min(hi, path.length);
        }

        int lo = 0, result = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // length 0 is trivially a common subpath — must NOT be
            // short-circuited to "false", or the binary search skips
            // testing mid == 1 entirely.
            if (mid == 0 || hasCommonSubpath(paths, mid)) {
                result = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return result;
    }

    private boolean hasCommonSubpath(int[][] paths, int len) {
        Set<Long> common = null;
        for (int[] path : paths) {
            Set<Long> hashesInPath = getHashes(path, len);
            if (hashesInPath.isEmpty()) return false;
            if (common == null) {
                common = hashesInPath;
            } else {
                common.retainAll(hashesInPath);
                if (common.isEmpty()) return false;
            }
        }
        return common != null && !common.isEmpty();
    }

    private Set<Long> getHashes(int[] path, int len) {
        Set<Long> hashes = new HashSet<>();
        int n = path.length;
        if (len > n) return hashes;

        long hash1 = 0, hash2 = 0;
        long pow1 = 1, pow2 = 1;

        for (int i = 0; i < len; i++) {
            hash1 = (hash1 * BASE1 + path[i]) % MOD1;
            hash2 = (hash2 * BASE2 + path[i]) % MOD2;
            if (i > 0) {
                pow1 = (pow1 * BASE1) % MOD1;
                pow2 = (pow2 * BASE2) % MOD2;
            }
        }
        hashes.add(combine(hash1, hash2));

        for (int i = len; i < n; i++) {
            hash1 = (hash1 - path[i - len] * pow1 % MOD1 + MOD1 * BASE1) % MOD1;
            hash1 = (hash1 * BASE1 + path[i]) % MOD1;

            hash2 = (hash2 - path[i - len] * pow2 % MOD2 + MOD2 * BASE2) % MOD2;
            hash2 = (hash2 * BASE2 + path[i]) % MOD2;

            hashes.add(combine(hash1, hash2));
        }
        return hashes;
    }

    private long combine(long h1, long h2) {
        return h1 * 1_000_000_009L + h2;
    }
}