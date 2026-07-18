class Solution {
    public int minCost(String source, String target, List<List<String>> rules, int[] costs) {

        int n = source.length();
        int R = rules.size();
        long INF = Long.MAX_VALUE / 2;
        long[] dp = new long[n + 1];
        Arrays.fill(dp,INF);
        dp[0] = 0;

        for(int i = 0; i < n; i++) {
            if(dp[i] >= INF) continue;

            if(source.charAt(i) == target.charAt(i)) {
             dp[i + 1] = Math.min(dp[i + 1], dp [ i]);
            }
            for( int r = 0; r < R; r++) {

                String pat = rules.get(r).get(0);
                String rep = rules.get(r).get(1);
                int len = pat.length();
                if ( i + len > n) continue;

                boolean valid = true;
                int wildcards = 0;
                for ( int j = 0; j < len; j++) {

                    if ( rep.charAt(j)  != target.charAt(i + j))  {

                        valid = false;
                        break;
                    }
                    char pc = pat.charAt(j);
                    if(pc == '*') {
                        wildcards++;
                    }else if (pc != source.charAt(i + j)) {

                        valid = false;
                        break;
                    }

                        
                    
                }

                if(valid) {
                    dp[i +  len] = Math.min(dp[i + len], dp[i] + costs[r] + wildcards);
                }
            }
        }
        return dp[ n] >= INF ? -1 : (int) dp[n];
    }
}