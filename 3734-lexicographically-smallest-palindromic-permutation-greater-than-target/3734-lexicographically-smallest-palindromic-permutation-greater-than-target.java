class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        int oddCount = 0, oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) { oddCount++; oddChar = i; }
        }
        if (n % 2 == 0 && oddCount != 0) return "";
        if (n % 2 == 1 && oddCount != 1) return "";

        char midChar = (n % 2 == 1) ? (char) ('a' + oddChar) : 0;
        int[] pairs = new int[26];
        for (int i = 0; i < 26; i++) pairs[i] = freq[i] / 2;

        int h = n / 2;
        String halfTarget = target.substring(0, h);

        List<String> candidates = new ArrayList<>();

        // Case 1: exact match of target's first half
        int[] halfTargetCount = new int[26];
        for (char c : halfTarget.toCharArray()) halfTargetCount[c - 'a']++;
        if (Arrays.equals(halfTargetCount, pairs)) {
            StringBuilder sb = new StringBuilder();
            sb.append(halfTarget);
            if (n % 2 == 1) sb.append(midChar);
            sb.append(new StringBuilder(halfTarget).reverse());
            String full = sb.toString();
            if (full.compareTo(target) > 0) candidates.add(full);
        }

        // Case 2: smallest half strictly greater than target[:h]
        int[][] states = new int[h + 1][26];
        states[0] = pairs.clone();
        char[] prefixChars = new char[h];
        int maxMatched = 0;
        for (int i = 0; i < h; i++) {
            char c = halfTarget.charAt(i);
            if (states[i][c - 'a'] > 0) {
                states[i + 1] = states[i].clone();
                states[i + 1][c - 'a']--;
                prefixChars[i] = c;
                maxMatched++;
            } else {
                break;
            }
        }

        String halfB = null;
        for (int i = maxMatched; i >= 0 && halfB == null; i--) {
            if (i == h) continue;
            int[] cur = states[i];
            char tchar = halfTarget.charAt(i);
            int chosen = -1;
            for (int c = tchar - 'a' + 1; c < 26; c++) {
                if (cur[c] > 0) { chosen = c; break; }
            }
            if (chosen != -1) {
                int[] nxt = cur.clone();
                nxt[chosen]--;
                StringBuilder sb = new StringBuilder();
                sb.append(prefixChars, 0, i);
                sb.append((char) ('a' + chosen));
                for (int c = 0; c < 26; c++) {
                    for (int k = 0; k < nxt[c]; k++) sb.append((char) ('a' + c));
                }
                halfB = sb.toString();
            }
        }

        if (halfB != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(halfB);
            if (n % 2 == 1) sb.append(midChar);
            sb.append(new StringBuilder(halfB).reverse());
            candidates.add(sb.toString());
        }

        if (candidates.isEmpty()) return "";
        Collections.sort(candidates);
        return candidates.get(0);
    }
}