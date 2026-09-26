class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        // Store key-value pairs in HashMap
        HashMap<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            if (s.charAt(i) == '(') {

                i++; // skip '('

                StringBuilder key = new StringBuilder();

                // Read until ')'
                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

                i++; // skip ')'

                // If key exists, use its value
                // Otherwise use '?'
                if (map.containsKey(key.toString())) {
                    ans.append(map.get(key.toString()));
                } else {
                    ans.append("?");
                }

            } else {

                ans.append(s.charAt(i));
                i++;
            }
        }

        return ans.toString();
    }
}