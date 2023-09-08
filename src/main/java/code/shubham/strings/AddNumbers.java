package code.shubham.strings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddNumbers {


    class ResultII {

        /*
         * Complete the 'addLargeNumbersWithCommas' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts following parameters:
         *  1. STRING a
         *  2. STRING b
         */

        public static String addLargeNumbersWithCommas(String a, String b) {
            char[] result = new char[Math.max(a.length(), b.length())];
            int r = result.length - 1;
            int ai = a.length(), bi = b.length(), carry = 0;
            while (ai > 0 || bi > 0) {
                ai--;
                bi--;

                int sum = carry;
                if (ai > -1) {
                    if (a.charAt(ai) == ',') {
                        result[r--] = ',';
                        continue;
                    } else {
                        sum += (a.charAt(ai) - '0');
                    }
                }

                if (bi > -1) {
                    if (b.charAt(bi) == ',') {
                        result[r--] = ',';
                        continue;
                    } else {
                        sum += (b.charAt(bi) - '0');
                    }
                }

                carry = sum / 10;
                result[r--] = (char) ((sum  % 10) + 48);
            }

            if (carry > 0)
                return carry + new String(result);

            return new String(result);
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        String result = ResultII.addLargeNumbersWithCommas(a, b);
        System.out.println(result);

        bufferedReader.close();
    }
}
