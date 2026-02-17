package code.shubham.bitmanipulation;

public class XORInGivenRange {

    class Solution {
        int solve(int l, int r) {
            int xor1ToL = xor1ToN(l - 1);
            int xor1ToR = xor1ToN(r);
            return xor1ToL ^ xor1ToR;
        }
    }

    int xor1ToN(int n) {
        int r = n % 4;
        if (r == 1)
            return 1;
        if (r == 2)
            return n+1;
        if (r == 3)
            return 0;
        return n;
    }
}
