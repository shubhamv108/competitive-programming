package code.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestSubsequenceIncreasingThenDecreasing {

    class Solution {

        public int longestSubsequenceLength(final List<Integer> A) {

            if (A.size() == 0) return 0;

            int[] lis = new int[A.size()];
            int[] lid = new int[A.size()];

            for (int i = 0; i < A.size(); i++) {
                lis[i] = 1;
                lid[i] = 1;
            }

            for (int i = 1; i < A.size(); i++)
                for (int j = 0; j < i; j++)
                    if (A.get(i) > A.get(j) && lis[i] < lis[j] + 1)
                        lis[i] = lis[j] + 1;

            for (int i = A.size() - 2; i > -1 ; i--)
                for (int j = A.size() - 1; j > i; j--)
                    if (A.get(i) > A.get(j) && lid[i] < lid[j] + 1)
                        lid[i] = lid[j] + 1;

            int longestSubsequence = 1;
            for (int i = 0; i < A.size(); i++)
                longestSubsequence = Math.max (longestSubsequence, lis[i] + lid[i] - 1);

            return longestSubsequence;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubsequenceIncreasingThenDecreasing().new Solution().longestSubsequenceLength(new ArrayList<>(Arrays.asList(  ))));
    }


}
