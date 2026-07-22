class Group {
    int start, length;
    Group(int start, int length) { this.start = start; this.length = length; }
}

class SparseTable {
    private final int n;
    private final int[][] st;

    SparseTable(int[] nums) {
        n = nums.length;
        int k = 32 - Integer.numberOfLeadingZeros(Math.max(n, 1));
        st = new int[k + 1][n];
        if (n > 0) System.arraycopy(nums, 0, st[0], 0, n);
        for (int i = 1; i <= k; i++)
            for (int j = 0; j + (1 << i) <= n; j++)
                st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
    }

    int query(int l, int r) { // max(nums[l..r])
        int i = 31 - Integer.numberOfLeadingZeros(r - l + 1);
        return Math.max(st[i][l], st[i][r - (1 << i) + 1]);
    }
}

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones = 0;
        for (char c : s.toCharArray()) if (c == '1') ones++;

        List<Group> zg = new ArrayList<>();
        int[] zIdx = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') zg.get(zg.size() - 1).length++;
                else zg.add(new Group(i, 1));
            }
            zIdx[i] = zg.size() - 1;
        }

        List<Integer> ans = new ArrayList<>();
        if (zg.isEmpty()) {
            for (int[] q : queries) ans.add(ones);
            return ans;
        }

        int[] mergeSums = new int[Math.max(zg.size() - 1, 0)];
        for (int i = 0; i < zg.size() - 1; i++)
            mergeSums[i] = zg.get(i).length + zg.get(i + 1).length;
        SparseTable st = mergeSums.length > 0 ? new SparseTable(mergeSums) : null;

        for (int[] q : queries) {
            int l = q[0], r = q[1];
            int left = zIdx[l] == -1 ? -1 : zg.get(zIdx[l]).length - (l - zg.get(zIdx[l]).start);
            int right = zIdx[r] == -1 ? -1 : (r - zg.get(zIdx[r]).start + 1);

            int startAdj = zIdx[l] + 1;
            int endAdj = (s.charAt(r) == '1' ? zIdx[r] : zIdx[r] - 1) - 1;

            int best = ones;

            if (s.charAt(l) == '0' && s.charAt(r) == '0' && zIdx[l] + 1 == zIdx[r]) {
                best = Math.max(best, ones + left + right);
            } else if (startAdj <= endAdj && st != null) {
                best = Math.max(best, ones + st.query(startAdj, endAdj));
            }

            int rhsFirst = s.charAt(r) == '1' ? zIdx[r] : zIdx[r] - 1;
            if (s.charAt(l) == '0' && zIdx[l] + 1 <= rhsFirst) {
                best = Math.max(best, ones + left + zg.get(zIdx[l] + 1).length);
            }
            if (s.charAt(r) == '0' && zIdx[l] < zIdx[r] - 1) {
                best = Math.max(best, ones + right + zg.get(zIdx[r] - 1).length);
            }

            ans.add(best);
        }
        return ans;
    }
}