class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        // First and last position of every character
        int[] first = new int[26];
        int[] last = new int[26];

        // Initialize
        for (int i = 0; i < 26; i++) {
            first[i] = n;
            last[i] = -1;
        }

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try every character
        for (int c = 0; c < 26; c++) {

            if (last[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            // Expand the interval
            for (int i = left; i <= right; i++) {

                int x = s.charAt(i) - 'a';

                // This character appears before our left boundary
                if (first[x] < left) {
                    valid = false;
                    break;
                }

                // Need to include its last occurrence
                right = Math.max(right, last[x]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();

        int prevEnd = -1;

        // Greedily choose non-overlapping intervals
        for (int[] interval : intervals) {

            if (interval[0] > prevEnd) {

                result.add(
                    s.substring(interval[0], interval[1] + 1)
                );

                prevEnd = interval[1];
            }
        }

        return result;
    }
}