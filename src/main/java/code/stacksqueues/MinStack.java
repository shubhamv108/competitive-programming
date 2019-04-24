package code.stacksqueues;

import java.util.Stack;

public class MinStack {
    class Solution {
        private Stack<Integer> s    = new Stack<>();
        private Stack<Integer> minS = new Stack<>();
        public void push(int x) {
            s.push(x);
            if (minS.isEmpty() || x <= minS.peek()) minS.push(x);
        }

        public void pop() {
            if (s.isEmpty()) return;
            int n = s.pop();
            if (n == minS.peek()) minS.pop();
        }

        public int top() {
            if (s.isEmpty()) return -1;
            return s.peek();
        }

        public int getMin() {
            if (minS.isEmpty()) return -1;
            return minS.peek();
        }
    }
}
