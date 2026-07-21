import java.util.*;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1"; // augment both ends
        int n = t.length();

        // Build run-length blocks: [char(0/1), length]
        List<int[]> blocks = new ArrayList<>();
        int i = 0;
        while (i < n) {
            char c = t.charAt(i);
            int j = i;
            while (j < n && t.charAt(j) == c) j++;
            blocks.add(new int[]{c - '0', j - i});
            i = j;
        }

        int ones = 0;
        for (char c : s.toCharArray()) if (c == '1') ones++;

        int best = 0;
        // only interior blocks can be "surrounded" on both sides
        for (int k = 1; k < blocks.size() - 1; k++) {
            if (blocks.get(k)[0] == 1) { // an interior 1-block
                int left = blocks.get(k - 1)[1];  // adjacent 0-block
                int right = blocks.get(k + 1)[1]; // adjacent 0-block
                best = Math.max(best, left + right);
            }
        }

        return ones + best;
    }
}