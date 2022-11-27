package code.shubham.stacksqueues;

import java.util.Stack;

public class RedundantBraces {
    class Solution {
        public int braces(String A) {
            char c;
            int count;
            Stack<Character> s = new Stack();
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) == ')') {
                    c = s.pop();
                    if (c == '(') return 1;
                    else {
                        count = 0;
                        while (c != '(') {
                            c = s.pop();
                            count++;
                        }
                        if (count == 1) return 1;
                    }
                } else {
                    s.push(A.charAt(i));
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new RedundantBraces().new Solution().braces("((a+b))"));
    }
}
