package code.shubham.bitmanipulation;

public class HammingWeight {
    public class Solution {
        public int hammingWeight(int n) {
            int result = 0;
            while (n != 0) {
                n &= (n-1);
                result++;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new HammingWeight().new Solution().hammingWeight(31)
        );
    }
}
