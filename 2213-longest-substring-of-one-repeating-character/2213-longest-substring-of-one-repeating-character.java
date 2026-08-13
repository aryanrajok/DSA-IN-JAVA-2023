class Solution {
    int n;
    char[] leftChar, rightChar, arr;
    int[] segLen, prefix, suffix, maxLen;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        arr = s.toCharArray();
        leftChar = new char[4 * n];
        rightChar = new char[4 * n];
        segLen = new int[4 * n];
        prefix = new int[4 * n];
        suffix = new int[4 * n];
        maxLen = new int[4 * n];

        build(1, 0, n - 1);

        int m = queryCharacters.length();
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int idx = queryIndices[i];
            arr[idx] = queryCharacters.charAt(i);   // <-- charAt instead of array index
            update(1, 0, n - 1, idx);
            ans[i] = maxLen[1];
        }
        return ans;
    }

    void build(int node, int l, int r) {
        if (l == r) {
            leftChar[node] = rightChar[node] = arr[l];
            segLen[node] = prefix[node] = suffix[node] = maxLen[node] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        merge(node);
    }

    void update(int node, int l, int r, int idx) {
        if (l == r) {
            leftChar[node] = rightChar[node] = arr[idx];
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node, l, mid, idx);
        else update(2 * node + 1, mid + 1, r, idx);
        merge(node);
    }

    void merge(int node) {
        int lc = 2 * node, rc = 2 * node + 1;
        segLen[node] = segLen[lc] + segLen[rc];
        leftChar[node] = leftChar[lc];
        rightChar[node] = rightChar[rc];

        prefix[node] = prefix[lc];
        if (prefix[lc] == segLen[lc] && rightChar[lc] == leftChar[rc]) {
            prefix[node] += prefix[rc];
        }

        suffix[node] = suffix[rc];
        if (suffix[rc] == segLen[rc] && rightChar[lc] == leftChar[rc]) {
            suffix[node] += suffix[lc];
        }

        maxLen[node] = Math.max(maxLen[lc], maxLen[rc]);
        if (rightChar[lc] == leftChar[rc]) {
            maxLen[node] = Math.max(maxLen[node], suffix[lc] + prefix[rc]);
        }
    }
}