class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int totalPushes = 0;

        for (int i = 0; i < n; i++) {
            // Har 8 letters ke baad presses ek se badh jaate hain
            // i=0..7 -> 1 press, i=8..15 -> 2 presses, i=16..23 -> 3, i=24..25 -> 4
            int presses = (i / 8) + 1;
            totalPushes += presses;
        }

        return totalPushes;
    }
}