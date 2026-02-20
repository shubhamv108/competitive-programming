package code.shubham.bitmanipulation;

public class HasAlternatingBits {
    class Solution {
        public boolean hasAlternatingBits(int n) {
            boolean prev = (n & 1) == 1;
            for (int i = 1; i < 31; ++i) {
                boolean cur = (n & (1 << i)) == 1;
                if (prev == cur)
                    return false;
                prev = cur;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new HasAlternatingBits().new Solution().hasAlternatingBits(5));
    }
}
