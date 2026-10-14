class Solution {
    public String maxSumOfSquares(int num, int sum) {

        if ((long) num * 9 < sum) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // Add as many 9s as possible
        int nines = sum / 9;
        int remainder = sum % 9;

        for (int i = 0; i < nines; i++) {
            ans.append('9');
        }

        // Add the remaining digit, if any
        if (remainder > 0) {
            ans.append(remainder);
        }

        // Fill remaining positions with zeros
        while (ans.length() < num) {
            ans.append('0');
        }

        return ans.toString();
    }
}