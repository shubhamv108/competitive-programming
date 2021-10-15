package code.contestpractice.oa.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    class Solution {
        public int maxLength(List<String> arr) {
            if (arr == null || arr.size() == 0) return 0;
            int result = 0;
            ArrayList<Integer> dp = new ArrayList<>();
            dp.add(0);
            for (String s : arr) {
                char[] chrs = s.toCharArray();
                int d = 0;
                boolean containsDup = false;
                for (char c : chrs)
                    if ((d & 1 << (c - 'a')) == 0)
                        d |= 1 << (c - 'a');
                    else
                        containsDup = true;

                if (!containsDup)
                    for (int i = 0; i < dp.size(); i++) {
                        if ((dp.get(i) & d) == 0)
                            dp.add(dp.get(i) | d);
                        result =  Math.max(result, Integer.bitCount(dp.get(dp.size() - 1)));
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

