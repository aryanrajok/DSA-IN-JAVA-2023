
class Solution {
    static final long MOD = 1_000_000_007L;

    long[] sum;
    long[] sumSq;
    long[] lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];

            // Increase distinct counts for subarrays
            // starting from prev + 1 through i.
            update(1, 0, n - 1, prev + 1, i, 1);

            // Sum of squared distinct counts
            ans = (ans + sumSq[1]) % MOD;

            last[nums[i]] = i;
        }

        return (int) ans;
    }

    void update(int node, int left, int right,
                int ql, int qr, long val) {

        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, val);
            return;
        }

        pushDown(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, right, ql, qr, val);

        sum[node] = sum[node * 2] + sum[node * 2 + 1];
        sumSq[node] = sumSq[node * 2] + sumSq[node * 2 + 1];
    }

    void apply(int node, int left, int right, long val) {
        long length = right - left + 1;

        // (x + val)^2 = x^2 + 2*x*val + val^2
        sumSq[node] += 2 * val * sum[node] + val * val * length;
        sum[node] += val * length;
        lazy[node] += val;
    }

    void pushDown(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        long val = lazy[node];

        apply(node * 2, left, mid, val);
        apply(node * 2 + 1, mid + 1, right, val);

        lazy[node] = 0;
    }
}
