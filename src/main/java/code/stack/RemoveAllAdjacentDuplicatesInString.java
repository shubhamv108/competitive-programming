package code.stack;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInString {
    class Solution {
        public String removeDuplicates(String s) {
            char[] chrs = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (char chr : chrs) {
                if (!stack.isEmpty() && stack.peek() == chr) {
                    stack.pop();
                    continue;
                }
                stack.push(chr);
            }

            StringBuilder result = new StringBuilder();
            while (!stack.isEmpty())
                result.insert(0, stack.pop());

            return result.toString();
        }
    }
}
