package code.shubham.bitmanipulation;

import java.util.Arrays;

public class SingleNumber {
    class Solution {
        public int singleNumber(int[] A) {
            return Arrays.stream(A).reduce(0, (acc, a) -> acc ^ a);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new SingleNumber().new Solution().singleNumber(new int[] {1, 2, 3, 4, 5, 1, 2, 3, 4})
        );
    }
}
