package code.shubham.strings.parser;

public class ReverseSubstringsBetweenEachPairOfParentheses {
    class Solution {
        String A;
        int ai = 0, n;
        public String reverseParentheses(String A) {
            this.A = A;
            this.n = A.length();
            return parseString().toString();
        }

        StringBuilder parseString() {
            StringBuilder result = new StringBuilder();
            for (; ai < n; ++ai) {
                char c = A.charAt(ai);
                if (c == ')')
                    return result;
                if (c == '(') {
                    result.append(parseBracket());
                } else {
                    result.append(c);
                }
            }

            return result;
        }

        StringBuilder parseBracket() {
            StringBuilder result = new StringBuilder();
            for (++ai; ai < n; ++ai) {
                char c = A.charAt(ai);
                if (c == ')')
                    return result;
                return parseString().reverse();
            }
            return result;
        }
    }

    void main() {
//        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().new Solution().reverseParentheses("a(ab(cd(ef)))"));
        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().new Solution().reverseParentheses("a(bc)de"));
        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().new Solution().reverseParentheses("a(ab(cd(ef))gh)"));
//        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().new Solution().reverseParentheses("(ed(et(oc))el)"));
    }
}
