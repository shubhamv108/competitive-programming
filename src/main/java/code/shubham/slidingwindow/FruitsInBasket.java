package code.shubham.slidingwindow;

public class FruitsInBasket {

    class Solution {
        public int totalFruit(int[] A) {
            int start = 0, k = 2, result = 0;
            int[] f = new int[A.length + 1];
            for (int end = 0; end < A.length; ++end) {

                if (f[A[end]]++ == 0) {
                    while (k == 0)
                        if (--f[A[start++]] == 0)
                            ++k;
                    --k;
                }

                result = Math.max(result, end - start + 1);
            }

            return result;
        }
    }

}
