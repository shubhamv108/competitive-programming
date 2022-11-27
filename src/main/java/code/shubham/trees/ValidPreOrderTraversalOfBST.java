package code.shubham.trees;

import java.util.Stack;

public class ValidPreOrderTraversalOfBST {

    boolean canRepresentBST(int A[], int n) {
        Stack<Integer> s = new Stack<Integer>();
        int root = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (A[i] < root)
                return false;
            while (!s.empty() && s.peek() < A[i])
                root = s.pop();
            s.push(A[i]);
        }
        return true;
    }

}
