package code.shubham.stacksqueues;

public class MinimumRemoveToMakeValidParenthesis {

    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();

        int open = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                open++;
            } else if (chars[i] == ')') {
                if (open > 0) {
                    open--;
                } else {
                    chars[i] = '\n';
                }
            }
        }

        for (int i = chars.length - 1; i >= 0; i--) {
            if (open > 0 && chars[i] == '(') {
                chars[i] = '\n';
                open--;
            }
        }

        int p = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++)
            if (chars[i] != '\n')
                builder.append(chars[i]);

        return builder.toString();
    }

}
