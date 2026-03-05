package code.shubham.bitmanipulation.greedybitmasking.feasibilitycheck;

public class MinimumBitwiseOrFromGrid {
    class Solution {
        public int minimumOR(int[][] AA) {
            int result = 0;
            int forbiddenBits = 0;

            for (int bit = 17; bit >= 0; --bit) {
                int testBits = forbiddenBits | (1 << bit); // number will contain forbidden bits
                boolean possible = true;

                // feasibility check
                for (int[] A : AA) {
                    boolean rowOk = false;
                    for (int a : A) {
                        if ((a & testBits) == 0) { // find at least one
                            rowOk = true;
                            break;
                        }
                    }

                    if (!rowOk) {
                        possible = false;
                        break;
                    }
                }

                if (possible)
                    forbiddenBits = testBits;
                else
                    result |= (1 << bit); // forced to allow this bit
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumBitwiseOrFromGrid().new Solution().minimumOR(new int[][] {
                { 2, 6, 7 },
                { 16, 23, 20 },
                { 10, 9, 28 }
        }));
    }
}
