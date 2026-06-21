package code.shubham.stacksqueues;

import java.util.Deque;
import java.util.LinkedList;

public class LargestAreaInHistogram {

    class Solution1 {
        public int largestRectangleArea(int[] A) {
            int n = A.length, result = 0;

            Deque<Integer> s = new LinkedList<>();
            for (int i = 0; i < n; ++i) {
                while (!s.isEmpty() && A[i] < A[s.peek()]) {
                    int top = s.pop();
                    if (s.isEmpty())
                        result = Math.max(result, A[top] * i);
                    else
                        result = Math.max(result, A[top] * (i - s.peek() - 1));
                }
                s.push(i);
            }

            if (s.isEmpty())
                return result;

            int i = n;
            while (!s.isEmpty()) {
                int top = s.pop();
                if (s.isEmpty())
                    result = Math.max(result, A[top] * i);
                else
                    result = Math.max(result, A[top] * (i - s.peek() - 1));
            }


            return result;
        }
    }

    class Solution {
        public int largestRectangleArea(int[] A) {
            int n = A.length;
            int result = 0;

            int l[] = new int[n]; // left reach jump
            int r[] = new int[n]; // right reach jump

            l[0] = -1;
            r[n - 1] = n;

            // left reach
            for(int i = 1; i < n; ++i) {
                int reach = i - 1;
                while (reach >= 0 && A[reach] >= A[i])
                    reach = l[reach];
                l[i] = reach;
            }

            // right reach
            for(int i = n - 2; i >= 0; --i) {
                int reach = i + 1;
                while(reach < n && A[reach] >= A[i])
                    reach = r[reach];
                r[i] = reach;
            }


            for(int i = 0; i < n; ++i) {
                int width = r[i] - l[i] - 1;
                result = Math.max(result, A[i] * width);
            }
            return result;

        }
    }

    void main() {
        System.out.println(new Solution().largestRectangleArea(new int[] { 2, 1, 5, 6, 2, 3 })); // 10
        System.out.println(new Solution().largestRectangleArea(new int[] { 4, 2, 0, 3, 2, 5 })); // 6
    }

}
