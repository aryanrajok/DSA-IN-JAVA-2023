class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        int MOD = 1_000_000_007;
        // dp[face][count] = number of sequences ending with 'face' repeated 'count' times
        int[][] dp = new int[6][16]; // 16 is safely more than max rollMax value (max 15)

        // Base case: after 1 roll, each face has appeared exactly once
        for (int face = 0; face < 6; face++) {
            dp[face][1] = 1;
        }

        // Build up roll by roll, from roll 2 to roll n
        for (int roll = 2; roll <= n; roll++) {
            int[][] newDp = new int[6][16];

            for (int face = 0; face < 6; face++) {
                for (int count = 1; count <= rollMax[face]; count++) {
                    // Case 1: extend the same face's streak (if allowed)
                    if (count > 1) {
                        newDp[face][count] = dp[face][count - 1];
                    }
                    // Case 2: switch to this face from a different previous face
                    if (count == 1) {
                        for (int prevFace = 0; prevFace < 6; prevFace++) {
                            if (prevFace == face) continue;
                            for (int prevCount = 1; prevCount <= rollMax[prevFace]; prevCount++) {
                                newDp[face][1] = (newDp[face][1] + dp[prevFace][prevCount]) % MOD;
                            }
                        }
                    }
                }
            }

            dp = newDp;
        }

        // Sum up all possible ending states
        int total = 0;
        for (int face = 0; face < 6; face++) {
            for (int count = 1; count <= rollMax[face]; count++) {
                total = (total + dp[face][count]) % MOD;
            }
        }

        return total;
    }
}
