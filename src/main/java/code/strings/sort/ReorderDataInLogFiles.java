package code.strings.sort;

import java.util.Arrays;

public class ReorderDataInLogFiles {
    class Solution {
        public String[] reorderLogFiles(String[] logs) {
            Arrays.sort(logs, (x, y) -> {
                int a = x.indexOf(' ') + 1;
                int b = y.indexOf(' ') + 1;
                boolean isXDigit = x.charAt(a) <= '9';
                boolean isYDigit = y.charAt(b) <= '9';

                if (isXDigit)
                    return isYDigit ? 0 : 1;

                if (isYDigit)
                    return -1;

                int result = x.substring(a).compareTo(y.substring(b));
                if (result == 0)
                    return x.substring(0, a-1).compareTo(y.substring(0, b-1));

                return result;
            });

            return logs;
        }
    }
}
