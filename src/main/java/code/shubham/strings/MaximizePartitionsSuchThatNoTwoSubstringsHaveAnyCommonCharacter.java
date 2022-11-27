package code.shubham.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Partition Labels
public class MaximizePartitionsSuchThatNoTwoSubstringsHaveAnyCommonCharacter {

    // Time Complexity: O(n) , (26 + length of string + 26 + 26 + 26) = (length of string + 104) = O(n)
    class Solution {
        public List<String> solve(final String s) {
            char[] c = s.toCharArray();
            int l = c.length;

            List<int[]> characterIndexes = new ArrayList<>();

            // initialize interval for each character as [-1, -1]
            IntStream.range(0, 26).forEach(i -> characterIndexes.add(new int[] {-1, -1}));

            // Add first and last position for each character in the characterIndexes list
            for (int i = 0; i < l; i++) {
                int idx = c[i] - 'a';
                int[] interval = characterIndexes.get(idx);
                if (interval[0] == -1) {
                    interval[0] = i;
                    interval[1] = i;
                }
                else interval[1] = i;
            }

            // Removing all intervals for character which are not in String s
            List<int[]> intervals = characterIndexes.stream().filter(interval -> interval[0] != -1).collect(Collectors.toList());
            Collections.sort(intervals, (a, b) -> a[0] - b[0]);
            // Merge Intervals
            int prevNonNullIdx = 0;
            for (int i = 1; i < intervals.size(); i++) {
                int[] prevInterval = intervals.get(prevNonNullIdx);
                int[] interval = intervals.get(i);
                if (prevInterval[1] >= interval[0]) {
                    /** if current character interval overlaps */
                    interval[0] = prevInterval[0];
                    // make current interval end as max of previous end or current end
                    interval[1] = Math.max(prevInterval[1], interval[1]);
                    // mark previous interval as null
                    intervals.set(i - 1, null);
                    prevNonNullIdx = i;
                } else {
                    // mark previous non null index in interval as current interval
                    prevNonNullIdx = i;
                }
            }

            // filter out null intervals and calculate count
            return  intervals.stream()
                    .filter(Objects::nonNull)
                    .map(interval -> s.substring(interval[0], interval[1] + 1))
                    .map(e -> e.toString())
                    .collect(Collectors.toList());
//                    .count();
//            return intervals.stream()
//                    .filter(Objects::nonNull)
//                    .mapToInt(interval -> (interval[1] - interval[0]) + 1)
//                    .collect()
        }
    }

    class Solution2 {
        public List<Integer> partitionLabels(String s) {
            int[] lastIndex = new int[26];
            for (int i = 0; i < s.length(); ++i)
                lastIndex[s.charAt(i) - 'a'] = i;
            int curStart = 0, curEnd = 0;
            List<Integer> result = new ArrayList();
            for (int i = 0; i < s.length(); i++) {
                curEnd = Math.max(curEnd, lastIndex[s.charAt(i) - 'a']);
                if (i == curEnd) {
                    result.add(i - curStart + 1);
                    curStart = i + 1;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        MaximizePartitionsSuchThatNoTwoSubstringsHaveAnyCommonCharacter obj =
                new MaximizePartitionsSuchThatNoTwoSubstringsHaveAnyCommonCharacter();
//        System.out.println(obj.new Solution().solve("abcabcdefgdehijkhl")); // [abcabc, defgde, hijkhl]
//        System.out.println(obj.new Solution().solve("abcabcdefgehijkl")); // [abcabcd, efgehijkl]
//        System.out.println(obj.new Solution().solve("abbcaa")); // [abbcaa]
//        System.out.println(obj.new Solution().solve("ababcbacadefegdehijhklij")); // [ababcbaca, defegde, hijhklij]
//        System.out.println(obj.new Solution().solve("aaa")); // [aaa]
//        System.out.println(obj.new Solution().solve("eaaaabaaec")); // [aaa]
        System.out.println(obj.new Solution2().partitionLabels("eaaaabaaec")); // [aaa]
    }
}
