package code.shubham.contestpractice.oa.microsoft;

public class MinDeletionToAvoidRepeatingLetters {

    class Solution {
        public int minCost(String S, int[] neededTime) {
            char[] chrs = S.toCharArray();
            int result = 0, len = chrs.length, prevIdx = 0;
            for (int i = 1; i < len; i++) {
                if (chrs[i] == chrs[prevIdx]) {
                    if (neededTime[i] < neededTime[prevIdx]) {
                        result += neededTime[i];
                    } else {
                        result += neededTime[prevIdx];
                        prevIdx = i;
                    }
                } else {
                    prevIdx = i;
                }
            }
            return result;
        }
    }
}
