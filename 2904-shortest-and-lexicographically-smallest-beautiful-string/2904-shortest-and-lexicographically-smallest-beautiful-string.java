class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0, ones = 0;
        String ans = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') ones++;

            // shrink if we have too many ones
            while (ones > k) {
                if (s.charAt(left) == '1') ones--;
                left++;
            }

            if (ones == k) {
                // trim leading zeros to keep it minimal
                int l = left;
                while (s.charAt(l) == '0') l++;

                int len = right - l + 1;
                String candidate = s.substring(l, right + 1);

                if (ans.isEmpty() || len < ans.length() ||
                    (len == ans.length() && candidate.compareTo(ans) < 0)) {
                    ans = candidate;
                }
            }
        }

        return ans;
    }
}