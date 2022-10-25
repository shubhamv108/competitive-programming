package code.greedy;

import java.util.Stack;

public class MinimumTimeToMakeRopeColorful {
    class Solution {
        public int minCost(String COLORS, int[] neededTime) {
            char[] colors = COLORS.toCharArray();
            int result = 0, len = colors.length;
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            for (int i = 1; i < len; i++) {
                if (colors[i] == colors[stack.peek()]) {
                    if (neededTime[stack.peek()] < neededTime[i]) {
                        result += neededTime[stack.peek()];
                        stack.pop();
                        stack.push(i);
                    } else {
                        result += neededTime[i];
                    }
                } else {
                    stack.push(i);
                }
            }
            return result;
        }
    }

    class Solution2 {
        public int minCost(String COLORS, int[] neededTime) {
            char[] colors = COLORS.toCharArray();
            int result = 0, len = colors.length, minTime;
            for (int i = 1; i < len; i++) {
                if (colors[i] == colors[i-1]) {
                    minTime = Math.min(neededTime[i], neededTime[i-1]);
                    result += minTime;
                    neededTime[i] = Math.max(neededTime[i], neededTime[i-1]);
                }
            }
            return result;
        }
    }

    class Solution3 {
        public int minCost(String COLORS, int[] neededTime) {
            char[] colors = COLORS.toCharArray();
            int result = 0, len = colors.length, prevIdx = 0;
            for (int i = 1; i < len; i++) {
                if (colors[i] == colors[prevIdx]) {
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
