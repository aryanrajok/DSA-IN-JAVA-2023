class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;   // edge case: empty grid hai toh 0 islands

        int rows = grid.length;                            // total rows
        int cols = grid[0].length;                          // total columns
        int islandCount = 0;                                 // answer counter

        for (int i = 0; i < rows; i++) {                     // pura grid row-wise traverse karo
            for (int j = 0; j < cols; j++) {                 // har row ke andar column-wise traverse karo
                if (grid[i][j] == '1') {                     // agar unvisited land mila
                    islandCount++;                            // naya island mila, count badhao
                    dfs(grid, i, j, rows, cols);              // poore connected land ko sink (mark visited) karo
                }
            }
        }

        return islandCount;                                  // total islands return karo
    }

    private void dfs(char[][] grid, int i, int j, int rows, int cols) {
        // base case: agar out of bounds hai YA water hai YA already visited (sunk) hai
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == '0') {
            return;                                          // recursion yahan ruk jayegi
        }

        grid[i][j] = '0';                                     // current land ko "sink" karo (visited mark)

        dfs(grid, i + 1, j, rows, cols);                       // neeche wale cell ko explore karo
        dfs(grid, i - 1, j, rows, cols);                       // upar wale cell ko explore karo
        dfs(grid, i, j + 1, rows, cols);                       // right wale cell ko explore karo
        dfs(grid, i, j - 1, rows, cols);                       // left wale cell ko explore karo
    }
}