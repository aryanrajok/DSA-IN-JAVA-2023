class Solution {
    public int reverseDegree(String s) {

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {

            // Normal position of character
            int value = s.charAt(i) - 'a' + 1;

            // Reverse value
            int reverseValue = 26 - value + 1;

            // Position starts from 1
            ans += reverseValue * (i + 1);
        }

        return ans;
    }
}