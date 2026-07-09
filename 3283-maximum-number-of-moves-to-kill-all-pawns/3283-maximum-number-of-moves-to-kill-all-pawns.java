import java.util.*;

class Solution {
    private int n;
    private int[][] dist;   // dist[i][j] = shortest knight-move distance between point i and point j
    private int[][] memo;   // memo[mask][pos] = best result from this state

    public int maxMoves(int kx, int ky, int[][] positions) {
        n = positions.length;
        int[][] points = new int[n + 1][2];
        points[0] = new int[]{kx, ky};
        for (int i = 0; i < n; i++) points[i + 1] = positions[i];

        // Step 1: BFS from every point to get full distance matrix
        dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            int[][] d = bfsFrom(points[i][0], points[i][1]);
            for (int j = 0; j <= n; j++) {
                dist[i][j] = d[points[j][0]][points[j][1]];
            }
        }

        // Step 2: bitmask minimax DP
        memo = new int[1 << n][n + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        return dfs((1 << n) - 1, 0);
    }

    // pos: 0 = knight's start, i+1 = pawn i's position
    // mask: bitmask of pawns still on the board
    private int dfs(int mask, int pos) {
        if (mask == 0) return 0;
        if (memo[mask][pos] != -1) return memo[mask][pos];

        int captured = n - Integer.bitCount(mask);
        boolean aliceTurn = captured % 2 == 0;
        int best = aliceTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                int val = dist[pos][i + 1] + dfs(mask ^ (1 << i), i + 1);
                best = aliceTurn ? Math.max(best, val) : Math.min(best, val);
            }
        }
        memo[mask][pos] = best;
        return best;
    }

    private int[][] bfsFrom(int sx, int sy) {
        int[][] d = new int[50][50];
        for (int[] row : d) Arrays.fill(row, -1);
        d[sx][sy] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 8; k++) {
                int nx = cur[0] + dx[k], ny = cur[1] + dy[k];
                if (nx >= 0 && nx < 50 && ny >= 0 && ny < 50 && d[nx][ny] == -1) {
                    d[nx][ny] = d[cur[0]][cur[1]] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return d;
    }
}