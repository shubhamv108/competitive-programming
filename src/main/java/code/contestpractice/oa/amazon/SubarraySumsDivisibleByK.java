package code.contestpractice.oa.amazon;

public class SubarraySumsDivisibleByK {
    class Solution {
        public int subarraysDivByK(int[] A, int K) {
            int[] count = new int[K];
            count[0] = 1;
            int prefix = 0, res = 0;
            for (int a : A) {
                prefix = (prefix + a % K + K) % K;
                res += count[prefix]++;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new SubarraySumsDivisibleByK()
                    .new Solution()
                        .subarraysDivByK(
                            new int[] { 4,5,0,-2,-3,1 }
                            , 5
                        )
        );
    }
}
