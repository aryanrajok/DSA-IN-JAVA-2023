class Solution {
    public String reverseParentheses(String s) {

        Stack<StringBuilder> stack = new Stack<>();

        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {

                // Save current string
                stack.push(current);

                // Start a new string
                current = new StringBuilder();

            } else if (ch == ')') {

                // Reverse what's inside ()
                current.reverse();

                // Get the string before '('
                StringBuilder previous = stack.pop();

                // Join them
                previous.append(current);

                current = previous;

            } else {

                current.append(ch);
            }
        }

        return current.toString();
    }
}