package code.shubham.bitmanipulation;

public class DivideIntegersWitoutMultiplicationOrDivision {
    class Solution {
        public int singleNumber(int[] A) {
            int result = 0;

            for (int i = 0; i < 32; ++i) {
                int c = 0;
                for (int a : A)
                    c += (a & (1 << i)) != 0 ? 1 : 0;
                if (c % 3 == 1)
                    result |= (1 << i);
            }
            return result;
        }
    }
}
