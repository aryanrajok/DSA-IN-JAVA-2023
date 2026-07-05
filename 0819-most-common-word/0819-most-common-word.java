import java.util.*;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>();
        for (String b : banned) {
            bannedSet.add(b.toLowerCase());
        }

        // Replace non-alphabetic characters with spaces, then lowercase
        String cleaned = paragraph.toLowerCase().replaceAll("[^a-z]", " ");
        String[] words = cleaned.split("\\s+");

        Map<String, Integer> count = new HashMap<>();
        String result = "";
        int maxCount = 0;

        for (String word : words) {
            if (word.isEmpty() || bannedSet.contains(word)) {
                continue;
            }
            int freq = count.merge(word, 1, Integer::sum);
            if (freq > maxCount) {
                maxCount = freq;
                result = word;
            }
        }

        return result;
    }
}