package code.stacksqueues;

import java.util.Stack;

public class LargestAreaInHistogram {

    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;

        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!s.empty() && heights[i] < heights[s.peek()]) {
                int top = s.pop();
                if (s.isEmpty()) {
                    maxArea = Math.max(maxArea, heights[top] * i);
                } else {
                    maxArea = Math.max(maxArea, heights[top] * (i - s.peek() - 1));
                }
            }
            s.push(i);
        }

        if (!s.isEmpty()) {
            int i = heights.length;
            while (!s.empty()) {
                int top = s.pop();
                if (s.isEmpty()) {
                    maxArea = Math.max(maxArea, heights[top] * i);
                } else {
                    maxArea = Math.max(maxArea, heights[top] * (i - s.peek() - 1));
                }
            }
        }

        return maxArea;
    }

}
