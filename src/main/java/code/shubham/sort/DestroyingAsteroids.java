package code.shubham.sort;

public class DestroyingAsteroids {
    class Solution {
        public boolean asteroidsDestroyed(int M, int[] A) {
            int min = 100001, max = 0;
            for (int a : A) {
                min = Math.min(min, a);
                max = Math.max(max, a);
            }

            int fl = max - min + 1;
            int[] f = new int[fl];
            for (int a : A)
                ++f[a - min];

            for (int i = 0; i < fl; ++i) {
                int p = i + min;
                if (M < p)
                    return false;
                if (f[i] > 0)
                    M += (f[i] * p);
            }

            return true;
        }


    }

    static void main() {
        System.out.println(new DestroyingAsteroids().new Solution()
                                   .asteroidsDestroyed(10, new int[] { 3, 9, 19, 5, 21 }));
    }
}
