package code.shubham._01Knapsack;

public class JumpGame2 {
    class Solution {
        public int jump(int[] A) {
            int curEnd = 0, maxReach = 0, l = A.length - 1, result = 0;
            for (int i = 0; i < l && i <= maxReach; ++i) {
                maxReach = Math.max(maxReach, i + A[i]);
                if (i == curEnd) {
                    ++result;
                    curEnd = maxReach;
                }
            }
            return result;
        }
    }
}
