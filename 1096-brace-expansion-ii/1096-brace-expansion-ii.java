class Solution {

    String s;
    int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    // Handles: a,b,c
    Set<String> parseExpression() {

        Set<String> result = parseTerm();

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // skip ','

            Set<String> next = parseTerm();

            result.addAll(next);
        }

        return result;
    }

    // Handles concatenation: abc, {a,b}c, a{b,c}
    Set<String> parseTerm() {

        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> next = parseFactor();

            Set<String> temp = new HashSet<>();

            // Combine every string from result
            // with every string from next
            for (String a : result) {
                for (String b : next) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }

    // Handles:
    // 1. normal character
    // 2. {...}
    Set<String> parseFactor() {

        Set<String> result = new HashSet<>();

        if (s.charAt(index) == '{') {

            index++; // skip '{'

            result = parseExpression();

            index++; // skip '}'

        } else {

            // Normal character
            result.add(String.valueOf(s.charAt(index)));

            index++;
        }

        return result;
    }
}