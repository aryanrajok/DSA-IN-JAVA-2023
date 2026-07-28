class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] secretCount = new int[10];
        int[] guessCount = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if (s == g) {
                bulls++;
            } else {
                secretCount[s - '0']++;
                guessCount[g - '0']++;
            }
        }

        for (int d = 0; d < 10; d++) {
            cows += Math.min(secretCount[d], guessCount[d]);
        }

        return bulls + "A" + cows + "B";
    }
}