package code.shubham.stacksqueues;

import java.util.Deque;
import java.util.LinkedList;

public class LargestAreaInHistogram {

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

    void main() {
        System.out.println(new LargestAreaInHistogram().largestRectangleArea(new int[] { 2, 1, 5, 6, 2, 3 }));
    }

}
