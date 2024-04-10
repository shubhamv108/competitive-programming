package code.shubham.arrays;

public class MinimumNumberOfTapsToOpenToWaterAGarden {
    class Solution {
        public int minTaps(int n, int[] A) {
            int[] maxReach = new int[A.length];
            int i = 0, result = 0;
            for (; i < A.length; ++i)
                maxReach[Math.max(0, i - A[i])] = Math.max(Math.max(0, i - A[i]), Math.min(A.length - 1, i + A[i]));


            int nextEnd = 0, curEnd = 0;
            for (i = 0; i < A.length; ++i) {
                if (i > nextEnd)
                    return -1;
                if (i > curEnd) {
                    ++result;
                    curEnd = nextEnd;
                }
                nextEnd = Math.max(nextEnd, maxReach[i]);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinimumNumberOfTapsToOpenToWaterAGarden()
                        .new Solution()
                        .minTaps(5, new int[] {4,1,1,1,1,1}));
    }
}

interface ai {
    void meth();
}

class a {
    public void meth() {
        System.out.println("meth");
    }
}

class b extends a implements ai {
    public static void main(String[] args) {
        new b().meth();
    }
}
/* *** /* */
/* // */
// /* // */
