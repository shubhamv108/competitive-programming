package code.shubham.bitmanipulation;

public class MinimumBitFlipsToConvertNumber {
    class Solution {
        public int minBitFlips(int start, int goal) {
            int result = 0;
            for (int i = 0; i < 31; ++i) {
                boolean is1Set = (start & (1 << i)) != 0;
                boolean is2Set = (goal & (1 << i)) != 0;
                if (is1Set != is2Set)
                    ++result;
            }
            return result;
        }

        public int minBitFlips2(int start, int goal) {
            int result = 0;
            int xor = start ^ goal;
            while (xor != 0) {
                result += xor  & 1;
                xor >>= 1;
            }
            return result;
        }
    }
}
