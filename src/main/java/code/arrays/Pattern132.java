package code.arrays;

import java.util.Stack;

public class Pattern132 {

    public boolean find132pattern(int[] A) {
        if(A == null || A.length < 3) return false;
        Stack<Integer> highest = new Stack<>();
        int secondHighest = Integer.MIN_VALUE;
        for (int i = A.length - 1; i > -1; i--) {
            if (A[i] < secondHighest)
                return true;
            while (!highest.isEmpty() && A[i] > highest.peek())
                secondHighest = highest.pop();
            highest.push(A[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        new Pattern132().find132pattern(new int[] { 3, 6, 4, 1, 2 });
    }

}
