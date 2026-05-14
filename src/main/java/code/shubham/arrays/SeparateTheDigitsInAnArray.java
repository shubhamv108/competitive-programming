package code.shubham.arrays;

import java.util.LinkedList;

public class SeparateTheDigitsInAnArray {
    class Solution {
        public int[] separateDigits(int[] A) {
            int n = A.length;
            LinkedList<Integer> result = new LinkedList<>();
            for (int i = n - 1; i >= 0; --i) {
                while (A[i] > 0) {
                    result.offerFirst(A[i] % 10);
                    A[i] /= 10;
                }
            }
            return result.stream().mapToInt(e -> e).toArray();
        }
    }


    static void main() {

    }
}
