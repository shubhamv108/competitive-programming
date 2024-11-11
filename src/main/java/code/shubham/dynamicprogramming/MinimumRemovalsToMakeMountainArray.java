package code.shubham.dynamicprogramming;

public class MinimumRemovalsToMakeMountainArray {

    class Solution {
        public int minimumMountainRemovals(int[] A) {
            int len = A.length, i, j;
            int[] lr = new int[len];
            lr[0] = 1;
            for (i = 1; i < len; ++i)
                for (j = 0; j < i; ++j)
                    if (A[i] > A[j])
                        lr[i] = Math.max(lr[j] + 1, lr[i]);

            int[] rl = new int[len];
            rl[len - 1] = 1;
            for (i = len - 2; i > -1; --i)
                for (j = len - 1; j > i; --j)
                    if (A[i] > A[j])
                        rl[i] = Math.max(rl[i], rl[j] + 1);

            int result = 0;
            for (i = 0; i < A.length; ++i)
                if (lr[i] > 1 && rl[i] > 1)
                    result = Math.max(result, lr[i] + rl[i] - 1);

            return len - result;
        }
    }

    public static void main(String[] args) {    
        System.out.println(
            new MinimumRemovalsToMakeMountainArray().new Solution().minimumMountainRemovals(new int[] {9,8,1,7,6,5,4,3,2,1})
        );
    }

}
