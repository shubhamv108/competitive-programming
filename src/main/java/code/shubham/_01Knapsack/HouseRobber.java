package code.shubham._01Knapsack;

public class HouseRobber {
    class Solution {
        public int rob(int[] A) {
            int a = 0, b = 0;
            for (int c : A) {
                int t = b;
                b = Math.max(b, a + c);
                a = t;
            }
            return b;
        }
    }
}
