package code.stack;

import java.util.ArrayList;

public class DesignAStackWithIncrementOperation {
    class CustomStack {

        int[] stack;
        int curIdx = -1;
        public CustomStack(int maxSize) {
            stack = new int[maxSize];
        }

        public void push(int x) {
            if (curIdx == stack.length - 1)
                return;
            stack[++curIdx] = x;
        }

        public int pop() {
            if (curIdx == -1)
                return -1;
            return stack[curIdx--];
        }

        public void increment(int k, int val) {
            for (int i = 0; i <= curIdx && i < k; i++)
                stack[i] += val;
        }
    }

    /**
     * Your CustomStack object will be instantiated and called as such:
     * CustomStack obj = new CustomStack(maxSize);
     * obj.push(x);
     * int param_2 = obj.pop();
     * obj.increment(k,val);
     */

    public static void main(String[] args) {
        var stack = new DesignAStackWithIncrementOperation().new CustomStack(3);
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.increment(5, 200);
        stack.increment(2, 100);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}
