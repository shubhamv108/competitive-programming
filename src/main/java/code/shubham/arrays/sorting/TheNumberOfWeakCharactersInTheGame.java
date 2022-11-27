package code.shubham.arrays.sorting;

import java.util.Arrays;

public class TheNumberOfWeakCharactersInTheGame {
    class Solution {
        public int numberOfWeakCharacters(int[][] properties) {
            int result = 0;
            Arrays.sort(properties, (x, y) -> x[0] == y[0] ? x[1] - y[1] : y[0] - x[0]);

            int max = properties[0][1];
            for (int i = 1; i < properties.length; i++) {
                if (properties[i][1] < max)
                    result++;
                max = Math.max(max, properties[i][1]);
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new TheNumberOfWeakCharactersInTheGame().new Solution().
                        numberOfWeakCharacters(new int[][] {{5,5},{6,3},{3,6}})
        );
    }
}
