package code.shubham.contestpractice.oa.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    class Solution {
        public int maxLength(List<String> A) {
            int result = 0;
            ArrayList<Integer> dp = new ArrayList<>();
            dp.add(0);
            for (String a: A) {
                char[] chrs = a.toCharArray();
                int d = 0;
                boolean hasDuplicate = false;
                for (char chr: chrs) {
                    int p = 1 << (chr - 'a');
                    if ((d & p) == 0)
                        d |= p;
                    else
                        hasDuplicate = true;
                }

                if (!hasDuplicate) {
                    int size = dp.size();
                    for (int i = 0; i < size; i++) {
                        if ((dp.get(i) & d) == 0) {
                            int t = dp.get(i) | d;
                            dp.add(t);
                            result = Math.max(result, Integer.bitCount(t));
                        }
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        MaximumLengthOfAConcatenatedStringWithUniqueCharacters object = new MaximumLengthOfAConcatenatedStringWithUniqueCharacters();
        Solution solution = object.new Solution();
        System.out.println(solution.maxLength(Arrays.asList("un", "iq", "ue")));
    }
}

