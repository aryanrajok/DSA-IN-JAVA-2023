class Solution {
    public boolean isAlienSorted(String[] words, String order) {

        // Store the position of each character in the alien alphabet
        int[] rank = new int[26];

        for (int i = 0; i < order.length(); i++) {
            rank[order.charAt(i) - 'a'] = i;
        }

        // Compare every adjacent pair of words
        for (int i = 0; i < words.length - 1; i++) {

            String word1 = words[i];
            String word2 = words[i + 1];

            int j = 0;

            // Compare characters until one word ends
            while (j < word1.length() && j < word2.length()) {

                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if (c1 != c2) {
                    // If characters are different, check their alien order
                    if (rank[c1 - 'a'] > rank[c2 - 'a']) {
                        return false;
                    }
                    break;
                }

                j++;
            }

            // Prefix case: "apple" comes before "app" -> Invalid
            if (j == word2.length() && word1.length() > word2.length()) {
                return false;
            }
        }

        return true;
    }
}