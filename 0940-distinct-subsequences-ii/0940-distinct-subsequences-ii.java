class Solution {
    public int distinctSubseqII(String s) {

        long[] end = new long[26];

        long total = 0;

        for (char ch : s.toCharArray()) {

            int index = ch - 'a';

            long newCount = (total + 1 - end[index] + 1000000007) 
                            % 1000000007;

            end[index] = (total + 1) % 1000000007;

            total = 0;

            for (long x : end) {
                total = (total + x) % 1000000007;
            }
        }

        return (int) total;
    }
}