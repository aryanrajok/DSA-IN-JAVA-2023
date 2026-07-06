class Solution {
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees; // all points are on the fence
        }
        
        // Step 1: sort by x, then by y
        Arrays.sort(trees, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        
        int[][] hull = new int[2 * n + 1][2];
        int size = 0;
        
        // Step 2: build lower hull
        for (int i = 0; i < n; i++) {
            while (size >= 2 && cross(hull[size-2], hull[size-1], trees[i]) < 0) {
                size--;
            }
            hull[size++] = trees[i];
        }
        
        // Step 3: build upper hull
        int lowerSize = size + 1; // save boundary point
        for (int i = n - 2; i >= 0; i--) {
            while (size >= lowerSize && cross(hull[size-2], hull[size-1], trees[i]) < 0) {
                size--;
            }
            hull[size++] = trees[i];
        }
        
        // Step 4: dedupe (last point == first point, since it's a closed loop)
        Set<String> seen = new HashSet<>();
        List<int[]> resultList = new ArrayList<>();
        for (int i = 0; i < size - 1; i++) {
            String key = hull[i][0] + "," + hull[i][1];
            if (seen.add(key)) {
                resultList.add(hull[i]);
            }
        }
        
        return resultList.toArray(new int[0][]);
    }
    
    // cross product of vectors OA and OB
    private int cross(int[] O, int[] A, int[] B) {
        return (A[0]-O[0]) * (B[1]-O[1]) - (A[1]-O[1]) * (B[0]-O[0]);
    }
}