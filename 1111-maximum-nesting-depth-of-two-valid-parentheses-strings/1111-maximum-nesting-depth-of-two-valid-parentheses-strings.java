class Solution {
    public int[] maxDepthAfterSplit(String seq) {

        int[] ans = new int[seq.length()];

        int depth = 0;

        for (int i = 0; i < seq.length(); i++) {

            if (seq.charAt(i) == '(') {
                depth++;

                // Odd depth → group 1
                // Even depth → group 0
                ans[i] = depth % 2;
            } 
            else {
                // For ')', use the current depth
                ans[i] = depth % 2;

                depth--;
            }
        }

        return ans;
    }
}