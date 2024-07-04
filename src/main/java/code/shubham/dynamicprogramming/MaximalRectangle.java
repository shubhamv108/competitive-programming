package code.shubham.dynamicprogramming;

import java.util.Stack;

public class MaximalRectangle {

    class Solution {
        public int maximalRectangle(char[][] A) {
            if (A.length==0)
                return 0;
            int[] heights = new int[A[0].length];
            int maxArea = -1;
            for(int i = 0; i < A.length; ++i) {
                for(int j = 0; j < A[0].length; ++j) {
                    if (A[i][j] == '0')
                        heights[j] = 0;
                    else
                        ++heights[j];
                }
                int area = largestRectangleArea(heights);
                maxArea = Math.max(maxArea, area);
            }
            return maxArea;
        }

        int largestRectangleArea(int[] heights) {
            int maxArea = 0;
            Stack<Integer> s = new Stack<>();

            for (int i = 0; i < heights.length; ++i) {
                while (!s.empty() && heights[i] < heights[s.peek()]) {
                    int top = s.pop();
                    if (s.isEmpty())
                        maxArea = Math.max(maxArea, heights[top] * i);
                    else
                        maxArea = Math.max(maxArea, heights[top] * (i - s.peek() - 1));
                }
                s.push(i);
            }

            if (!s.isEmpty()) {
                int i = heights.length;
                while (!s.empty()) {
                    int top = s.pop();
                    if (s.isEmpty())
                        maxArea = Math.max(maxArea, heights[top] * i);
                    else
                        maxArea = Math.max(maxArea, heights[top] * (i - s.peek() - 1));
                }
            }

            return maxArea;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximalRectangle().new Solution().maximalRectangle(
                new char[][] {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
    }
}
