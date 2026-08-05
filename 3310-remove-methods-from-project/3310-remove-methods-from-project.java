class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // forward graph: a -> b (a calls b)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }

        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int callee : graph.get(cur)) {
                if (!suspicious[callee]) {
                    suspicious[callee] = true;
                    queue.offer(callee);
                }
            }
        }

        // check: non-suspicious calling suspicious => disrupted
        for (int[] inv : invocations) {
            int a = inv[0], b = inv[1];
            if (!suspicious[a] && suspicious[b]) {
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) all.add(i);
                return all;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) result.add(i);
        }
        return result;
    }
}