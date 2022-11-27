package code.shubham.strings;

import java.util.Stack;

public class MakeTheStringGreat {
    class Solution {
        public String makeGood(String s) {
            char[] chrs = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (char chr : chrs) {
                if (!stack.isEmpty() && (stack.peek() + 32 == chr  || stack.peek() - 32 == chr)) {
                    stack.pop();
                    continue;
                }
                stack.push(chr);
            }

            StringBuilder builder = new StringBuilder();
            while (!stack.isEmpty())
                builder.insert(0, stack.pop());
            return builder.toString();
        }
    }
}
