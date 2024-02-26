package code.shubham.dynamicprogramming;

public class ObtainPalindromicArray {

    /*
    ToDo
     */
    class Solution {
//        boolean solve(String[] A) {}

        boolean recurse(String a, String b, String[] A, int x ,int y, int c) {
//            if (Math.abs(a.length() - b.length()) > 2)
//                return false;
//
//            return (arePalin(a, b) && recurse(A, x + 1, y - 1, 0) ) ||
//                    (c == 0 && recurse(a + b.charAt(0), b.substring(1), A, x, y, 1)) ||
//                    (c == 0 && recurse(a.substring(0, a.length() - 2), a.charAt(a.length() -1) + b, A, x, y, 1));

            return false;

        }

        boolean arePalin(String a, String b) {
            if (a.length() != b.length())
                return false;
            int l = 0, r = b.length() - 1;
            while (l < r)
                if (a.charAt(l++) != b.charAt(r--))
                    return false;
            return true;
        }
    }

    public static void main(String[] args) {

    }
}
