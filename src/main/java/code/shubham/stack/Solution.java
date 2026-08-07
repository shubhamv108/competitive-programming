package code.shubham.stack;

import java.util.Stack;

class Solution {
    public int[] nextGreaterElements(int[] A) {
        int n = A.length, i = 0, result[] = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int k = (n * 2) - 1; k > -1; --k) {
            i = k % n;
           
            while (!stack.isEmpty() && A[stack.peek()] <= A[i])
                stack.pop();
            
            result[i] = stack.isEmpty() ? -1 : A[stack.peek()];

            stack.push(i);
        }

        return result;
        
    }

    void main() {
        System.out.println(new Solution().nextGreaterElements(new int[] { 1,2,3,4,3 }));
    }
}