package code.contestpractice.oa.microsoft;

public class NUniqueIntegersWithSum0 {

    class Solution {
        public int[] sumZero(int n) {
            int[] result = new int[n];
            int l = 0, r = n - 1, val = 1;
            while (l < r) {
                result[l++] = -val;
                result[r--] = val;
                val++;
            }
            if (l == r) result[l] = 0;
            return result;
        }
    }
}
