package code.shubham.java;

import java.util.concurrent.locks.ReadWriteLock;

public class TryCatchFinally {

    class Solution {
        int solve() {
            int returnValue = 10;

            try {
                int[] A = {};
                System.out.println(A[2]);
            } catch (Exception exception) {
                System.out.println("Catch Block: " + returnValue);
                return returnValue + 100;
            } finally {
                returnValue += 10;
                System.out.println("Finally Block: " + returnValue);
                return returnValue;
            }

//            return returnValue;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new TryCatchFinally().new Solution().solve()
        );
    }

}
