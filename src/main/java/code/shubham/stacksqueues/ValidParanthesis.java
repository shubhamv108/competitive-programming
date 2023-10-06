package code.shubham.stacksqueues;

import java.util.HashMap;
import java.util.Stack;

public class ValidParanthesis {

    class Solution {
        public boolean isValid(String s) {
            HashMap<Character, Character> m = new HashMap<>();
            m.put(')', '(');
            m.put('}', '{');
            m.put(']', '[');
            Stack<Character> stack = new Stack<>();;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '{' || c == '[')
                    stack.push(c);
                else if (stack.isEmpty() || (stack.peek() != m.get(c)))
                    return false;
                else
                    stack.pop();
            }

            return stack.isEmpty();
        }
    }

}
