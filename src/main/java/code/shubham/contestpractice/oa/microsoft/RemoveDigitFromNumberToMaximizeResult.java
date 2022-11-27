package code.shubham.contestpractice.oa.microsoft;

public class RemoveDigitFromNumberToMaximizeResult {
    class Solution {
        public String removeDigit(String number, char digit) {
            char[] N = number.toCharArray();
            int len = N.length, index = len-1;
            for (int i = 0; i < len-1; i++) {
                if (N[i] == digit) {
                    index = i;
                    if (N[i+1] > N[i])
                        return i == 0 ?  number.substring(1, len) : number.substring(0, i) + number.substring(i+1, len);
                }
            }

            if (N[len - 1] == digit)
                index = len-1;

            return index == len - 1 ? number.substring(0, index) : number.substring(0, index) + number.substring(index+1, len);
        }
    }
}
