package code.paranthesis;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {
    class Solution {
        public List<String> removeInvalidParentheses(String s) {
            List<String> result = new ArrayList<>();
            remove(s, result, 0, 0, '(', ')');
            return result;
        }

        public void remove(String s, List<String> result, int last_i, int last_j,  char openParan, char closeParan) {
            for (int stack = 0, i = last_i; i < s.length(); i++) {
                if (s.charAt(i) == openParan)
                    stack++;
                if (s.charAt(i) == closeParan)
                    stack--;
                if (stack >= 0)
                    continue;

                for (int j = last_j; j <= i; ++j)
                    if (s.charAt(j) == closeParan && (j == last_j || s.charAt(j - 1) != closeParan))
                        remove(s.substring(0, j) + s.substring(j + 1, s.length()), result, i, j, openParan, closeParan);

                return;
            }

            String reversed = new StringBuilder(s).reverse().toString();
            if (openParan == '(')
                remove(reversed, result, 0, 0, closeParan, openParan);
            else
                result.add(reversed);
        }

    }
}
