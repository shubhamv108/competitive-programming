package code.binary;

import java.util.stream.Stream;

public class AddBinary {
    class Solution {
        public String addBinary(String a, String b) {
            char[] A = a.toCharArray(), B = b.toCharArray();
            int posA = A.length -1 , posB = B.length - 1, posR = Math.max(posA, posB) + 1, i;
            char[] result = new char[posR + 1], sum;
            char carry = '0';
            while (posA > -1 && posB > -1) {
                sum = sum(A[posA--], B[posB--], carry);
                result[posR--] = sum[1];
                carry = sum[0];
            }

            while (posA > -1) {
                sum = sum(A[posA--], '0', carry);
                result[posR--] = sum[1];
                carry = sum[0];
            }

            while (posB > -1) {
                sum = sum(B[posB--], '0', carry);
                result[posR--] = sum[1];
                carry = sum[0];
            }

            if (carry == '1') {
                result[posR--] = '1';
            }

            return new String(result, posR + 1, result.length - (posR + 1));
        }

        char[] sum(char a, char b, char c) {
            long ones = Stream.of(a, b, c).filter(e -> e == '1').count();
            if (ones == 3)
                return new char[] {'1', '1'};
            if (ones == 2)
                return new char[] {'1', '0'};
            if (ones == 1)
                return new char[] {'0', '1'};
            return new char[] { '0', '0' };
        }
    }

    class Solution2 {
        public String addBinary(String a, String b) {
            char[] A = a.toCharArray(), B = b.toCharArray();
            int posA = A.length -1 , posB = B.length - 1;
            StringBuilder result = new StringBuilder();
            int carry = 0, sum;
            while (posA > -1 || posB > -1) {
                sum = 0;
                if (posA > -1)
                    sum += (A[posA--] - '0');

                if (posB > -1)
                    sum += (B[posB--] - '0');

                sum += carry;

                if (sum > 1)
                    carry = 1;
                else
                    carry = 0;

                if (sum == 1 || sum == 3)
                    result.insert(0, '1');
                else
                    result.insert(0, '0');
            }

            if (carry == 1)
                result.insert(0, carry);

            return result.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new AddBinary().new Solution2().addBinary("1010", "1011")
        );
    }
}
