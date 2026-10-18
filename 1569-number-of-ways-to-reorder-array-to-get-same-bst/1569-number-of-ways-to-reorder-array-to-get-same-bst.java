import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute combinations
        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] =
                    (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 to exclude the original ordering
        return (int) ((ways(list) - 1 + MOD) % MOD);
    }

    private long ways(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            int num = nums.get(i);

            if (num < root) {
                left.add(num);
            } else {
                right.add(num);
            }
        }

        long leftWays = ways(left);
        long rightWays = ways(right);

        int leftSize = left.size();
        int rightSize = right.size();

        // Choose positions for left-subtree elements
        long combinations = comb[leftSize + rightSize][leftSize];

        return combinations * leftWays % MOD * rightWays % MOD;
    }
}