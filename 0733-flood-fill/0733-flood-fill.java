class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image; // avoid infinite recursion
        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }
    
    private void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        // bounds check + color check
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length 
            || image[r][c] != oldColor) {
            return;
        }
        
        image[r][c] = newColor; // paint this cell
        
        // spread to 4 neighbors
        dfs(image, r + 1, c, oldColor, newColor);
        dfs(image, r - 1, c, oldColor, newColor);
        dfs(image, r, c + 1, oldColor, newColor);
        dfs(image, r, c - 1, oldColor, newColor);
    }
}