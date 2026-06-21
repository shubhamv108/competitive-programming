package code.shubham.range.bitwise;

import java.util.ArrayList;
import java.util.List;

public class ValidBinaryStringsWithCostLimit {
    class Solution {
        public List<String> generateValidStrings(int n, int k) {
            List<String> result = new ArrayList<>();
            recurse(0, 0, false, n, k, new StringBuilder(), result);
            return result;
        }

        void recurse(int idx, int c, boolean prev, int n, int k, StringBuilder cur, List<String> result) {
            if (c > k)
                return;

            if (idx == n) {
                result.add(cur.toString());
                return;
            }

            cur.append('0');
            recurse(idx + 1, c, false, n, k, cur, result);
            cur.deleteCharAt(cur.length() - 1);

            if (!prev) {
                cur.append('1');
                recurse(idx + 1, c + idx, true, n, k, cur, result);
                cur.deleteCharAt(cur.length() - 1);
            }
        }
    }

    void main() {
        System.out.println(new Solution().generateValidStrings(3, 1));
    }
}
