class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];
        k = k % (m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = (i * n + j + k) % (m * n);
                int newRow = idx / n, newCol = idx % n;
                res[newRow][newCol] = grid[i][j];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] row : res) {
            List<Integer> r = new ArrayList<>();
            for (int val : row) r.add(val);
            ans.add(r);
        }
        return ans;
    }
}