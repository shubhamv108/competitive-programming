package code.stacksqueues;

import java.util.HashMap;
import java.util.Stack;

public class ValidParanthesis {

    class Solution {
        public boolean isValid(String s) {
            HashMap<Character, Character> m = new HashMap<>();
            m.put(')', '(');
            m.put('}', '{');
            m.put(']', '[');
            Stack<Character> stack = new Stack<>();
            char[] chrs = s.toCharArray();
            for (char c : chrs)
                if (c == '(' || c == '{' || c == '[')
                    stack.push(c);
                else if (stack.isEmpty() || (stack.peek() != m.get(c)))
                    return false;
                else
                    stack.pop();

            return stack.isEmpty();
        }
    }

}
