class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();

        int sr = -1, sc = -1;
        List<int[]> litterPos = new ArrayList<>();
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) Arrays.fill(row, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') { sr = i; sc = j; }
                else if (grid[i][j] == 'L') {
                    litterIndex[i][j] = litterPos.size();
                    litterPos.add(new int[]{i, j});
                }
            }
        }

        int total = litterPos.size();
        if (total == 0) return 0;
        int fullMask = (1 << total) - 1;

        int[][] maxEnergy = new int[m * n][1 << total];
        for (int[] row : maxEnergy) Arrays.fill(row, -1);

        Deque<int[]> queue = new ArrayDeque<>(); // {row, col, mask, energy, moves}
        maxEnergy[sr * n + sc][0] = energy;
        queue.add(new int[]{sr, sc, 0, energy, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], mask = cur[2], e = cur[3], moves = cur[4];
            if (mask == fullMask) return moves;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                char ch = grid[nr][nc];
                if (ch == 'X') continue;
                if (e - 1 < 0) continue; // no energy left to move

                int ne = (ch == 'R') ? energy : e - 1;

                int nmask = mask;
                if (ch == 'L') {
                    int idx = litterIndex[nr][nc];
                    if (idx != -1) nmask = mask | (1 << idx);
                }

                int nidx = nr * n + nc;
                if (ne > maxEnergy[nidx][nmask]) {
                    maxEnergy[nidx][nmask] = ne;
                    queue.add(new int[]{nr, nc, nmask, ne, moves + 1});
                }
            }
        }
        return -1;
    }
}