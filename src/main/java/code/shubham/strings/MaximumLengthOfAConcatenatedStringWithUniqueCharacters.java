package code.shubham.strings;

import java.util.ArrayList;
import java.util.List;

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    public int maxLength(List<String> arr) {
        int result = 0;
        ArrayList<Integer> dp = new ArrayList<>();
        dp.add(0);
        for (String s : arr) {
            int b = 0, dup = 0;
            for (char c : s.toCharArray()) {
                dup |= b & (1 << (c - 'a'));
                b |= 1 << (c - 'a');
            }
            if (dup > 0) continue;
            for (int i = dp.size()-1; i > -1; i--) {
                if ((dp.get(i) & b) > 0) continue;
                dp.add(dp.get(i) | b);
                result = Math.max(result, Integer.bitCount(dp.get(i) | b));
            }
        }
        return result;
    }

}
