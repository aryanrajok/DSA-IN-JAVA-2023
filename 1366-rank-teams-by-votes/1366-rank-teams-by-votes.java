import java.util.*;

class Solution {
    public String rankTeams(String[] votes) {
        if (votes.length == 0) return "";

        int numTeams = votes[0].length();
        // count[c - 'A'][position] = number of votes team c got at that position
        int[][] count = new int[26][numTeams];

        for (String vote : votes) {
            for (int pos = 0; pos < vote.length(); pos++) {
                count[vote.charAt(pos) - 'A'][pos]++;
            }
        }

        Character[] teams = new Character[numTeams];
        for (int i = 0; i < numTeams; i++) {
            teams[i] = votes[0].charAt(i);
        }

        Arrays.sort(teams, (a, b) -> {
            int idxA = a - 'A';
            int idxB = b - 'A';
            for (int pos = 0; pos < numTeams; pos++) {
                if (count[idxA][pos] != count[idxB][pos]) {
                    return count[idxB][pos] - count[idxA][pos]; // higher count first
                }
            }
            return a - b; // alphabetical tiebreak
        });

        StringBuilder sb = new StringBuilder();
        for (char c : teams) {
            sb.append(c);
        }
        return sb.toString();
    }
}