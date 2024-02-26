package code.shubham.strings;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StrobogrammaticNumberII {
    public class Solution {
        /**
         * @param n: the length of strobogrammatic number
         * @return: All strobogrammatic numbers
         *          we will sort your return value in output
         */
        public List<String> findStrobogrammatic(int n) {
            return recurse(n, n);
        }

        LinkedList<String> recurse(int n, int m) {
            if (n == 0)
                return new LinkedList(Arrays.asList(""));
            if (n == 1)
                return new LinkedList(Arrays.asList("0", "1", "8"));

            LinkedList<String> result = recurse(n - 2, m);

            int size = result.size();
            while (size-- > 0) {
                String s = result.poll();
                if (n != m)
                    result.add("0" + s + "0");

                result.add("1" + s + "1");
                result.add("6" + s + "9");
                result.add("8" + s + "8");
                result.add("9" + s + "6");
            }
            return result;
        }
    }
}
