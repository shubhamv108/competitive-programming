package code.shubham.arrays;

import java.util.ArrayList;

public class PascalTriangle {
    class Solution {
        public ArrayList<ArrayList<Integer>> solve (int A) {
            ArrayList<ArrayList<Integer>> a = new ArrayList<>();
            int c;
            for(int line = 1; line <= A; line++) {
                c = 1;
                a.add(new ArrayList<>());
                for(int i = 1; i <= line; i++) {
                    a.get(i - 1).add(c);
                    c = c * ( line - i ) / i;
                }
            }
            return a;
        }
    }

    public static void main(String[] args) {
        new PascalTriangle().
            new Solution().
            solve(5).
            stream().
            forEach(e -> System.out.println(e));
    }
}
