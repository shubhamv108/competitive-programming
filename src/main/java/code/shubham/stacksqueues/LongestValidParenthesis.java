package code.shubham.stacksqueues;

import java.util.Stack;

public class LongestValidParenthesis {
    public static int longestValidParentheses(String s) {
        if (s.length() <= 1) return 0;
        int max = 0;
        Stack<Integer> z = new Stack<>();
        z.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                z.push(i);
            else {
                z.pop();
                if (!z.isEmpty())
                    max = Math.max(max, i - z.peek());
                else
                    z.push(i);
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

    class Solution {
        public int longestValidParentheses(String s) {
            int result = 0, left = -1;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(')
                    stack.push(i);
                else {
                    if (stack.isEmpty())
                        left = i;
                    else {
                        stack.pop();
                        if (stack.isEmpty())
                            result = Math.max(result, i - left);
                        else
                            result = Math.max(result, i - stack.peek());
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            longestValidParentheses("))()())((()))")
        );
    }

}
