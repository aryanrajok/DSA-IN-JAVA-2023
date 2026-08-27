class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        char[] prefix = new char[n];
        int i = 0;

        while (i < n) {
            int idx = target.charAt(i) - 'a';
            if (cnt[idx] > 0) {
                prefix[i] = target.charAt(i);
                cnt[idx]--;
                i++;
            } else {
                break;
            }
        }

        int j;
        if (i == n) {
            j = n - 1;
            cnt[target.charAt(j) - 'a']++;
        } else {
            j = i;
        }

        while (true) {
            int found = -1;
            for (int c = target.charAt(j) - 'a' + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    found = c;
                    break;
                }
            }

            if (found != -1) {
                prefix[j] = (char) ('a' + found);
                cnt[found]--;

                StringBuilder sb = new StringBuilder();
                sb.append(prefix, 0, j + 1);
                for (int c = 0; c < 26; c++) {
                    for (int k = 0; k < cnt[c]; k++) {
                        sb.append((char) ('a' + c));
                    }
                }
                return sb.toString();
            }

            if (j == 0) {
                return "";
            }

            j--;
            cnt[target.charAt(j) - 'a']++;
        }
    }
}