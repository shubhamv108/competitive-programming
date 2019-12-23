package code.stacksqueues;

import java.util.Stack;

public class LongestValidParenthesis {
    public static int longestValidParentheses(String s) {
        if (s.length() <= 1) return 0;
        int max = 0;
        Stack<Integer> z = new Stack<>();
        z.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') z.push(i);
            else {
                z.pop();
                if (!z.isEmpty()) max = Math.max(max, i - z.peek());
                else z.push(i);
            }
        }
        return max;
    }

    public static int longestValidParentheses2(String s) {
        if (s.length() <= 1) return 0;
        int max = 0;
        int[] l = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')' && i - l[i - 1] - 1 >= 0
                    && s.charAt(i - l[i - 1] - 1) == '(') {
                l[i] = l[i - 1] + 2 + ((i - l[i - 1] - 2 >= 0)
                                ? l[i - l[i - 1] - 2] : 0);
                max = Math.max(l[i], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(
            longestValidParentheses(")()())")
        );
    }

}
