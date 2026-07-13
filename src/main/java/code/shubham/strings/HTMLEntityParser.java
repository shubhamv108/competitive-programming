package code.shubham.strings;

import java.util.HashMap;
import java.util.Map;

public class HTMLEntityParser {
    class Solution {

        public String entityParser(String A) {
            Map<String, Character> m = new HashMap<>() {{
                put("quot", '\"');
                put("apos", '\'');
                put("amp", '&');
                put("gt", '>');
                put("lt", '<');
                put("frasl", '/');
            }};

            int n = A.length();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < n; ++i) {
                char ch = A.charAt(i);
                if (ch == '&')
                    ch = m.getOrDefault(extractSign(A, n, i), ch);
                result.append(ch);
            }
            return result.toString();
        }

        String extractSign(String A, int al, int ai) {
            StringBuilder sb = new StringBuilder();
            while (ai++ < al) {
                char ch = A.charAt(ai);
                if (ch == ';')
                    break;
                sb.append(ch);
            }
             System.out.println(sb.toString());
            return sb.toString();
        }
    }

    void main() {
        System.out.println(new Solution().entityParser("&amp; is an HTML entity but &ambassador; is not."));
    }
}
