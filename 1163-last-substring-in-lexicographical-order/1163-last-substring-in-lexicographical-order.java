class Solution {
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0, j = 1, k = 0;

        while (j + k < n) {
            char a = s.charAt(i + k);
            char b = s.charAt(j + k);

            if (a == b) {
                k++;
            } else if (a > b) {
                j = j + k + 1;
                k = 0;
            } else {
                i = Math.max(i + k + 1, j);
                j = i + 1;
                k = 0;
            }
        }

        return s.substring(i);
    }
}