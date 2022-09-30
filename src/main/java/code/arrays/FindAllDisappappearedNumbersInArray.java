package code.arrays;

import java.util.ArrayList;
import java.util.List;

public class FindAllDisappappearedNumbersInArray {
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] A) {
            ArrayList<Integer> result = new ArrayList<>();
            int len = A.length, i, val, temp;

            for (i = 0; i < len; i++) {
                val = A[i];
                while (val != A[val - 1]) {
                    temp = A[val - 1];
                    A[val - 1] = val;
                    val = temp;
                    if (val < 1 || val > len)
                        break;
                }
            }

            for (i = 0; i < len; i++)
                if (A[i] != i + 1)
                    result.add(i + 1);

            return result;
        }
    }
}
