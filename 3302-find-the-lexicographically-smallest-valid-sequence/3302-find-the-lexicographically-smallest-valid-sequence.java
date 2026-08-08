class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        
        // suf[i] = smallest j such that word2[j:] exactly matches as subsequence in word1[i:]
        int[] suf = new int[n + 1];
        suf[n] = m;
        int j = m;
        for (int i = n - 1; i >= 0; i--) {
            if (j > 0 && word1.charAt(i) == word2.charAt(j - 1)) {
                j--;
            }
            suf[i] = j;
        }
        
        int[] result = new int[m];
        int idx = 0;
        int i = 0, jj = 0;
        boolean mismatchUsed = false;
        
        while (i < n && jj < m) {
            if (word1.charAt(i) == word2.charAt(jj)) {
                result[idx++] = i;
                i++; jj++;
            } else if (!mismatchUsed && suf[i + 1] <= jj + 1) {
                result[idx++] = i;
                i++; jj++;
                mismatchUsed = true;
            } else {
                i++;
            }
        }
        
        if (jj < m) return new int[0]; // no valid sequence
        return result;
    }
}