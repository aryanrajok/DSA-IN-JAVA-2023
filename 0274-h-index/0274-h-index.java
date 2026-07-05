import java.util.Arrays;

class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        
        for (int i = 0; i < n; i++) {
            // number of papers with at least citations[i] citations = n - i
            int papersWithAtLeastThisManyCitations = n - i;
            if (citations[i] >= papersWithAtLeastThisManyCitations) {
                return papersWithAtLeastThisManyCitations;
            }
        }
        
        return 0;
    }
}