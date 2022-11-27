package code.shubham.contestpractice.oa.amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MostCommonWordWithExclusionList {

    class Solution {
        String solve(String paragraph, Set<String> exclusions) {
            String[] words = paragraph.split("\\s");
            HashMap<String, Integer> count = new HashMap<>();
            int maxFreq = 1;
            String result = words[0];
            for (String word : words) {
                if (!exclusions.contains(word)) {
                    int freq = count.getOrDefault(word, 0) + 1;
                    count.put(word, freq);
                    if (maxFreq <= freq) {
                        result = word;
                        maxFreq = freq;
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MostCommonWordWithExclusionList()
                    .new Solution()
                        .solve("If this book was written today in the midst of the slew of dystopian novels that come out, it may not have stood out. But, this book was way ahead of its time.",
                                new HashSet<>(Arrays.asList("of", "was", "the")))
        );
    }

}
