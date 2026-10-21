
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Find the minimum and maximum characters
        char min = 'z';
        char max = 'a';

        boolean sorted = true;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            min = (char) Math.min(min, c);
            max = (char) Math.max(max, c);

            // Check whether the string is sorted
            if (i > 0 && s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
            }
        }

        // Already sorted
        if (sorted) {
            return 0;
        }

        // Cannot sort a reversed string of length 2
        if (n == 2) {
            return -1;
        }

        // One operation is enough
        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        // Check whether min or max appears in the middle
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == min || s.charAt(i) == max) {
                return 2;
            }
        }

        // Otherwise, three operations are needed
        return 3;
    }
}
