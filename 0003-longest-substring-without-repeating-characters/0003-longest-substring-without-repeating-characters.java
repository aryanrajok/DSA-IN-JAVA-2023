import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> lastSeen = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If we've seen this char before, AND it's inside our current window
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1; // jump tail past the duplicate
            }

            lastSeen.put(c, right); // update last seen position
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}