package code.shubham.arrays;

public class MajorityElement {
    class Solution {
        public int majorityElement(int[] A) {
            int a = A[0], c = 1;
            for (int i = 1; i < A.length; ++i) {
                if (A[i] == a)
                    ++c;
                else if (c == 1) {
                    a = A[i];
                    c = 0;
                } else if (c == 0) {
                    a = A[i];
                    c = 1;
                } else
                    --c;
            }
            return a;
        }
    }
}
