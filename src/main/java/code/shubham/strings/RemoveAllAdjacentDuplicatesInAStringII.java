package code.shubham.strings;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInAStringII {
    class Solution {
        public String removeDuplicates(String s, int k) {
            Stack<int[]> stack = new Stack();
            int r = 0;
            for (int i = 0; i < s.length(); ++i) {
                if (!stack.isEmpty() && s.charAt(i) == s.charAt(stack.peek()[0])) {
                    if (++stack.peek()[1] == k) {
                        stack.pop();
                        ++r;
                    }
                } else {
                    stack.push(new int[] {i, 1});
                }
            }

            char[] result = new char[s.length() - (r * k)];
            int ri = result.length - 1;
            while (!stack.isEmpty()) {
                int[] p = stack.pop();
                while (p[1]-- > 0)
                    result[ri--] = s.charAt(p[0]);
            }

            return new String(result);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new RemoveAllAdjacentDuplicatesInAStringII()
                        .new Solution()
                        .removeDuplicates("deeedbbcccbdaa", 3));
    }
}
