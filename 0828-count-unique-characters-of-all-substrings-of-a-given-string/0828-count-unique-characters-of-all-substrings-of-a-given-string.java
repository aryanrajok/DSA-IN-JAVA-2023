class Solution {
    public int uniqueLetterString(String s) {
        final int MOD = 1_000_000_007; // not needed here actually, LC828 doesn't need mod, but safe to keep logic clean
        int n = s.length();
        int[] lastSeen = new int[26];
        int[] secondLastSeen = new int[26];
        java.util.Arrays.fill(lastSeen, -1);
        java.util.Arrays.fill(secondLastSeen, -1);

        long result = 0;

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';
            // distance to previous occurrence and the one before that
            int left = i - lastSeen[c];
            int right;
            // we don't know 'right' yet directly; better to use the standard two-pass approach below
            secondLastSeen[c] = lastSeen[c];
            lastSeen[c] = i;
        }

        // Cleaner standard approach: precompute prev and next occurrence arrays
        int[] prev = new int[n];
        int[] next = new int[n];
        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';
            prev[i] = last[c];
            last[c] = i;
        }

        java.util.Arrays.fill(last, n);
        for (int i = n - 1; i >= 0; i--) {
            int c = s.charAt(i) - 'A';
            next[i] = last[c];
            last[c] = i;
        }

        result = 0;
        for (int i = 0; i < n; i++) {
            long leftCount = i - prev[i];
            long rightCount = next[i] - i;
            result += leftCount * rightCount;
        }

        return (int) result;
    }
}