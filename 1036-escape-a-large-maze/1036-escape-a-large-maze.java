class Solution {
    private static final int BOUND = 1_000_000;
    private static final int LIMIT = 20000; // safely above 200*199/2 = 19900

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<Long> blockedSet = new HashSet<>();
        for (int[] b : blocked) {
            blockedSet.add(encode(b[0], b[1]));
        }

        // Both must be able to "escape" their local area AND reach each other
        return bfs(source, target, blockedSet) && bfs(target, source, blockedSet);
    }

    private boolean bfs(int[] start, int[] end, Set<Long> blocked) {
        Set<Long> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(encode(start[0], start[1]));

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            if (curr[0] == end[0] && curr[1] == end[1]) return true;

            if (visited.size() > LIMIT) return true; // escaped — enough open space

            for (int[] d : dirs) {
                int nx = curr[0] + d[0];
                int ny = curr[1] + d[1];

                if (nx < 0 || ny < 0 || nx >= BOUND || ny >= BOUND) continue;
                
                long code = encode(nx, ny);
                if (blocked.contains(code) || visited.contains(code)) continue;

                visited.add(code);
                queue.offer(new int[]{nx, ny});
            }
        }

        return false; // ran out of moves without reaching end or escaping
    }

    private long encode(int x, int y) {
        return (long) x * BOUND + y;
    }
}