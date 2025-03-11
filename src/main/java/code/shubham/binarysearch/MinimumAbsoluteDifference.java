package code.shubham.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumAbsoluteDifference {
    class Solution {
        public List<List<Integer>> minimumAbsDifference(int[] A) {
            ArrayList<List<Integer>> result = new ArrayList<>();

            countingSort(A);

            int minDiff = Integer.MAX_VALUE;
            for (int i = 1; i < A.length; ++i)
                minDiff = Math.min(minDiff, A[i] - A[i-1]);

            for (int i = 1; i < A.length; ++i)
                if (minDiff == A[i] - A[i-1])
                    result.add(Arrays.asList(A[i-1], A[i]));

            return result;
        }

        public void countingSort(int[] A){
            int min = A[0], max = A[0];
            for (int i = 0; i < A.length; ++i) {
                min = Math.min(A[i], min);
                max = Math.max(A[i], max);
            }

            boolean[] counts = new boolean[max - min + 1];

            for (int a : A)
                counts[a - min] = true;

            int ai = 0;
            for (int i = 0; i < counts.length; ++i)
                if (counts[i])
                    A[ai++] = i + min;

        }
    }
}
