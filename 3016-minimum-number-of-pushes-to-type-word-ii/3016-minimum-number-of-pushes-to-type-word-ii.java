class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) freq[c - 'a']++;
        
        Arrays.sort(freq); // ascending, so we read from the back for descending
        
        int totalPushes = 0;
        int pos = 0; // 0-indexed position among nonzero letters
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;
            totalPushes += freq[i] * (pos / 8 + 1);
            pos++;
        }
        return totalPushes;
    }
}