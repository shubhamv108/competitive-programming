package code.arrays.subsequence;

import java.util.ArrayList;
import java.util.Collections;

public class MaxEvenSumSubsequenceOfLengthK {

    class Solution {
        int solve(int[] A, int K) {
            ArrayList<Integer> even = new ArrayList<>();
            ArrayList<Integer> odd = new ArrayList<>();

            for (int n : A)
                if ((n & 1) == 0)
                    even.add(n);
                else
                    odd.add(n);

            Collections.sort(even);
            Collections.sort(odd);

            int i = even.size() - 1, j = odd.size() - 1, result = 0;
            while (K > 0) {
                if ((K & 1) == 1) {
                    if (i > -1) {
                        result += even.get(i);
                        i--;
                    } else return -1;
                    K--;
                } else if (i >= 1 && j >= 1) {
                    if (even.get(i) + even.get(i - 1) <= odd.get(j) + odd.get(j-1)) {
                        result += odd.get(j) + odd.get(j-1);
                        j -= 2;
                    } else {
                        result += even.get(i) + even.get(i-1);
                        i -= 2;
                    }
                    K -= 2;
                } else if (i >= 1) {
                    result += even.get(i) + even.get(i-1);
                    i -= 2;
                    K -= 2;
                } else if (j >= 1) {
                    result += odd.get(j) + odd.get(j-1);
                    j -= 2;
                    K -= 2;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        MaxEvenSumSubsequenceOfLengthK maxEvenSumSubsequenceOfLengthK = new MaxEvenSumSubsequenceOfLengthK();
        Solution solution = maxEvenSumSubsequenceOfLengthK.new Solution();
        System.out.println(solution.solve(
                new int[] {2, 4, 10, 3, 5}
                , 3
        ));
    }

}
