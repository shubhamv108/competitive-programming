package code.contestpractice.oa.arcesium;

public class WeirdStock {

    class Solution {
        public int solve(int n, int m) {
            int result = 0;
            while (n < m){
                m = (m & 1) == 1 ? m + 1 : m / 2;
                result++;
            }
            return result + (n - m);
        }
    }

    public static void main(String[] args) {
        System.out.println(
//                new WeirdStock().new Solution().solve(10, 1)
//                new WeirdStock().new Solution().solve(3, 4)
                new WeirdStock().new Solution().solve(1, 3)
        );
    }
}
