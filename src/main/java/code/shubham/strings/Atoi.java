package code.shubham.strings;

public class Atoi {

    public static int atoi(final String A) {
        int negative = 1;
        boolean pos = false;
        boolean isZero = false;
        int firstDigit = 0;
        int result = 0;
        for (int i = 0; i < A.length(); i++) {
            if (result == 0 && A.charAt(i) == '0') {
                isZero = true;
                continue;
            } else if (result == 0 && A.charAt(i) == '-') {
                negative = -1;
                continue;
            } else if (result == 0 && A.charAt(i) == '+') {
                pos = true;
                continue;
            } else if (result != 0 && (A.charAt(i) == '-')) {
                return result;
            } else if ((negative == 1 && !pos && result == 0 && !isZero) && A.charAt(i) == ' ') {
                continue;
            }
            int a = (int) A.charAt(i);
            if (a >= 48 && a < 58) {
                if (digits(result) > 9 || (digits(result) == 9 && (firstDigit > 2 || (a - 48) > 7))) {
                    if (negative < 0) return Integer.MIN_VALUE;
                    else return Integer.MAX_VALUE;
                }
                result *= 10;
                result += (a - 48);
                if (digits(result) ==  1) firstDigit = result;
            } else {
                break;
            }
        }
        return negative * result;
    }

    private static int digits(int n) {
        return (int) Math.log10(Math.abs(n)) + 1;
    }

    public static void main(String[] args) {
        System.out.println(atoi("-5121478262 8070067M75 X199R 547 8C0A11 93I630 4P4071 029W433619 M3 5 14703818 776366059B9O43393"));
    }

}
