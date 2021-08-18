package code.arithmetic;

public class ReverseAnInteger {

    public class Solution {
        public int reverse(int A) {
            long rev = 0;
            while (A != 0) {
                rev = (rev * 10) + (A % 10);
                A /= 10;
            }
            if (rev < Integer.MIN_VALUE || rev > Integer.MAX_VALUE)
                return 0;
            return (int) rev;
        }
    }


}
