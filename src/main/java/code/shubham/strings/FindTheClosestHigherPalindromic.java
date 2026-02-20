package code.shubham.strings;

public class FindTheClosestHigherPalindromic {
    public String nearestPalindromic(String A) {
        int n = A.length();
        if (A.matches("9+")) {
            StringBuilder sb = new StringBuilder("1");
            for (int i = 0; i < n - 1; ++i)
                sb.append("0");
            sb.append("1");
            return sb.toString();
        }

        int midIdx = (n + 1) / 2;
        String half = A.substring(0, midIdx);
        boolean isOdd = (n & 1) == 1;
        StringBuilder[] c = new StringBuilder[3];
        c[0] = reflect(half, isOdd);
        if (c[0].toString().compareTo(A) > 0)
            return c[0].toString();
        c[1] = reflect(increment(half), isOdd);
        return c[1].toString();
    }

    StringBuilder reflect(String A, boolean isOdd) {
        StringBuilder sb = new StringBuilder(A);
        StringBuilder a = new StringBuilder(A);
        if (isOdd)
            a.deleteCharAt(A.length() - 1);

        return sb.append(new StringBuilder(a).reverse());
    }

    String increment(String A) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        char[] a = A.toCharArray();

        for (int i = a.length - 1; i >= 0; --i) {
            int d = (a[i] - '0') + carry;
            if (a[i] == 9) {
                sb.append(0);
                carry = 1;
            } else if (i == a.length -1) {
                sb.append(d + 1);
            } else
                sb.append(d);
        }
        if (carry != 0)
            sb.append(carry);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new FindTheClosestHigherPalindromic().nearestPalindromic("123"));
    }
}
