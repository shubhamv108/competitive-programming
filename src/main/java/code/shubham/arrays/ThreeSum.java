package code.shubham.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {

    class Solution {
        public List<List<Integer>> threeSum(int[] A) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(A);
            int n = A.length;

            for (int i = 0; i < n-2; ++i) {
                int j = i + 1, k = n - 1;
                while (j < k) {
                    if (A[i] + A[j] + A[k] < 0)
                        j++;
                    else if(A[i] + A[j] + A[k] > 0)
                        k--;
                    else {
                        result.add(Arrays.asList(A[i], A[j], A[k]));
                        while(j+1 <= k && A[j] == A[j+1])
                            j++;
                        while(k-1 >= j && A[k-1] == A[k])
                            k--;
                        j++;
                        k--;
                    }

                }
                while(i + 1 < n && A[i] == A[i+1])
                    ++i;
            }
            return result;
        }
    }

}
