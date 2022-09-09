package code.arithmetic;

import java.util.Arrays;

public class RecordedPowerOf2 {
    class Solution {
        public boolean reorderedPowerOf2(int n) {
            int[] a = digitFreq(n);
            for (int i = 0; i < 32; i++)
                if (Arrays.equals(a, digitFreq(1 << i)))
                    return true;
            return false;
        }

        int[] digitFreq(int n) {
            int[] a = new int[10];
            while (n > 0) {
                a[n % 10]++;
                n = n/10;
            }
            return a;
        }
    }
}
