package code.shubham.topk;

import java.util.ArrayList;

public class SortCharactersByFrequency {

    class Solution {
        public String frequencySort(String s) {
            StringBuilder result = new StringBuilder();

            int[] f = new int[81];
            for (int i = 0; i < s.length(); ++i)
                ++f[s.charAt(i) - 48];

            ArrayList<Integer>[] buckets = new ArrayList[s.length() + 1];
            for (int i = 0; i < 81; ++i) {
                if (buckets[f[i]] == null)
                    buckets[f[i]] = new ArrayList<>();
                buckets[f[i]].add(i);
            }

            for (int i = buckets.length - 1; i > -1; --i)
                if (buckets[i] != null)
                    for (int a : buckets[i])
                        for (int j = 0; j < i; ++j)
                            result.append((char) (a + 48));

            return result.toString();
        }
    }

}
