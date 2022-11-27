package code.shubham.greedy;

import java.util.ArrayList;

public class HighestProduct {
    class Solution {
        public int maxp3(ArrayList<Integer> A) {
            if (A.size() < 3) return -1;
            A.sort((a, b) -> a - b);
            return Math.max(A.get(A.size() - 1) * A.get(A.size() - 2) * A.get(A.size() - 3),
                    A.get(A.size() - 1) * A.get(0) * A.get(1));
        }
    }

    class Solution2 {
        public int maxp3(ArrayList<Integer> A) {
            if (A.size() < 3)
                return -1;

            int maxA = Integer.MIN_VALUE, maxB = Integer.MIN_VALUE, maxC = Integer.MIN_VALUE;
            int minA = Integer.MAX_VALUE, minB = Integer.MAX_VALUE;

            for (int i = 0; i < A.size(); i++) {
                if (A.get(i) > maxA) {
                    maxC = maxB;
                    maxB = maxA;
                    maxA = A.get(i);
                }
                else if (A.get(i) > maxB) {
                    maxC = maxB;
                    maxB = A.get(i);
                } else if (A.get(i) > maxC)
                    maxC = A.get(i);

                if (A.get(i) < minA) {
                    minB = minA;
                    minA = A.get(i);
                } else if(A.get(i) < minB)
                    minB = A.get(i);
            }

            return Math.max(minA * minB * maxA,
                    maxA * maxB * maxC);
        }
    }

}
