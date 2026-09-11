import java.util.HashSet;
import java.util.Set;

class Solution {
    public int totalNumbers(int[] digits) {

        Set<Integer> set = new HashSet<>();

        int n = digits.length;

        // First digit
        for (int i = 0; i < n; i++) {

            // A 3-digit number cannot start with 0
            if (digits[i] == 0) {
                continue;
            }

            // Second digit
            for (int j = 0; j < n; j++) {

                // Cannot use the same position again
                if (j == i) {
                    continue;
                }

                // Third digit
                for (int k = 0; k < n; k++) {

                    // All three positions must be different
                    if (k == i || k == j) {
                        continue;
                    }

                    // Last digit must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int number =
                        digits[i] * 100 +
                        digits[j] * 10 +
                        digits[k];

                    set.add(number);
                }
            }
        }

        return set.size();
    }
}