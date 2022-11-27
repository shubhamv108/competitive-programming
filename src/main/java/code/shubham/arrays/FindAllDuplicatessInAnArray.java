package code.shubham.arrays;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatessInAnArray {
    class Solution {
        public List<Integer> findDuplicates(int[] A) {
            ArrayList<Integer> result = new ArrayList<>();
            int i, index, len = A.length;
            for (i = 0; i < len; i++) {
                index = Math.abs(A[i]) - 1;
                if (A[index] < 0) {
                    result.add(index + 1);
                    continue;
                }
                A[index] *= -1;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        new FindAllDuplicatessInAnArray().
                new Solution().
                findDuplicates(new int[] {4,3,2,7,8,2,3,1}).
                forEach(System.out::println);
    }
}
