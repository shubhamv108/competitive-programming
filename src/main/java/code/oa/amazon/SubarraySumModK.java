package code.oa.amazon;

public class SubarraySumModK {

    class Solution {
        public int subarrayModByK(int[] A, int K) {
            int[] count = new int[K];
            count[0] = 1;
            int prefix = 0, res = 0;
            for (int a : A) {
                prefix = (prefix + a / K + K) / K;
                res += count[prefix]++;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new SubarraySumModK().new Solution().subarrayModByK(new int[] {0, 5, 3}, 7)
        );
    }

}
