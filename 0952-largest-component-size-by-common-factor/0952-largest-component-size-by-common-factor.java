class Solution {
    private int[] parent;

    public int largestComponentSize(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // We need slots for numbers 0...maxVal (numbers act as "nodes" too, alongside their prime factors)
        parent = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            parent[i] = i; // everyone starts as their own parent
        }

        // Step 1: union each number with all its prime factors
        for (int num : nums) {
            int n = num;
            for (int factor = 2; factor * factor <= n; factor++) {
                if (n % factor == 0) {
                    union(num, factor);
                    while (n % factor == 0) {
                        n /= factor;
                    }
                }
            }
            if (n > 1) {
                union(num, n); // n itself is a prime factor left over
            }
        }

        // Step 2: count group sizes based on root parent
        Map<Integer, Integer> groupCount = new HashMap<>();
        int maxGroupSize = 0;

        for (int num : nums) {
            int root = find(num);
            int newCount = groupCount.getOrDefault(root, 0) + 1;
            groupCount.put(root, newCount);
            maxGroupSize = Math.max(maxGroupSize, newCount);
        }

        return maxGroupSize;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}