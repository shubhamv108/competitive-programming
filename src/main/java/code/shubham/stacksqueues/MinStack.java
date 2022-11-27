package code.shubham.stacksqueues;

import java.util.Stack;

public class MinStack {
    class Solution1 {
        Stack<Integer> min = new Stack<>();
        Stack<Integer> stack = new Stack<>();

        public void push(int val) {
            stack.push(val);
            if (min.isEmpty() || min.peek() >= val)
                min.push(val);
            System.out.println(min);
        }

        public void pop() {
            if (min.peek() == stack.peek().intValue())
                min.pop();
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    class Solution2 {

        Node head;

        class Node {
            int val;
            int min;
            Node next;
            Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }

        public void push(int val) {
            if (head == null)
                head = new Node(val, val, head);
            else
                head = new Node(val, Math.min(val, head.min), head);
        }

        public void pop() {
            if (head != null)
                head = head.next;
        }

        public int top() {
            if (head != null)
                return head.val;
            return 0;
        }

        public int getMin() {
            if (head != null)
                return head.min;
            return 0;
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        Solution2 solution = minStack.new Solution2();
        solution.push(512);
        solution.push(-1024);
        solution.push(-1024);
        solution.push(512);
        solution.pop();
        System.out.println(solution.getMin());
        solution.pop();
        System.out.println(solution.getMin());
        solution.pop();
        System.out.println(solution.getMin());
    }
}