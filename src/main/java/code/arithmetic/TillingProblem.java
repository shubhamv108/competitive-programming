package code.arithmetic;

/**
 *
 * If there is a floor of size n * m
 * and tiles of size 1 * m
 * In how many ways can the floor be filled with tiles
 *
 * O(n)
 */
public class TillingProblem {

    public class Solution {

        int countWays(int n, int m) {

            int[] count = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                if (i > m) {
                    count[i] = count[i - 1] + count [i - m];
                } else if (i == m) {
                    count[i] = 2;
                } else {
                    count[i] = 1;
                }
            }
            return count[n];
        }

    }

    public static void main(String[] args) {
        System.out.println(new TillingProblem().new Solution().countWays(2, 3));
    }
}
