package code.shubham.bitmanipulation;

public class ReverseBits {
    class Solution {
        public int reverseBits(int n) {
            int l = 31, r = 0;
            while (l > r) {
                int lth = 1 << l, rth = 1 << r;
                boolean lset = (n & lth) != 0;
                boolean rset = (n & rth) != 0;
                if (lset != rset) {
                    n ^= lth;
                    n ^= rth;
                }
                --l;
                ++r;
            }
            return n;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ReverseBits().new Solution().reverseBits(43261596));
    }
}
