class Solution {

    class Node {
        int product;
        int[] count;

        Node(int k) {
            product = 1 % k;
            count = new int[k];
        }
    }

    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Update
            update(1, 0, n - 1, index, value);

            // Query [start ... n-1]
            Node result = query(1, 0, n - 1, start, n - 1);

            answer[q] = result.count[x];
        }

        return answer;
    }

    // Build segment tree
    void build(int node, int left, int right, int[] nums) {

        if (left == right) {

            tree[node] = new Node(k);

            int value = nums[left] % k;

            tree[node].product = value;
            tree[node].count[value] = 1;

            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two nodes
    Node merge(Node a, Node b) {

        Node result = new Node(k);

        // Product of whole segment
        result.product = (a.product * b.product) % k;

        // Prefixes completely inside left part
        for (int r = 0; r < k; r++) {
            result.count[r] = a.count[r];
        }

        // Prefixes that go through left + part of right
        for (int r = 0; r < k; r++) {

            int newRemainder = (a.product * r) % k;

            result.count[newRemainder] += b.count[r];
        }

        return result;
    }

    // Update one index
    void update(int node, int left, int right,
                int index, int value) {

        if (left == right) {

            int v = value % k;

            tree[node] = new Node(k);

            tree[node].product = v;
            tree[node].count[v] = 1;

            return;
        }

        int mid = (left + right) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Range query
    Node query(int node, int left, int right,
               int ql, int qr) {

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = (left + right) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node a = query(node * 2, left, mid, ql, qr);
        Node b = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(a, b);
    }
}