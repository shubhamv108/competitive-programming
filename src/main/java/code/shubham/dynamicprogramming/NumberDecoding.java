package code.shubham.dynamicprogramming;

public class NumberDecoding {
    public int numDecodings(String s) {
        if ("".equals(s)) return 1;
        if (1 == s.length()) return 1;
        int secondSubStringDecodeCount = (s.length() >= 2 ? numDecodings(s.substring(2)) : 1);
        return numDecodings(s.substring(1)) + secondSubStringDecodeCount;
    }

    public int ways (String s, int n) {
        if (0 == n || 1 == n) return 1;
        if (s.charAt(0) == '0') return 0;
        int count = 0;
        if (s.charAt(n - 1) != '0') {
            count = ways(s, n - 1);
        }
        if (s.charAt(n - 2) == '1'
                || (s.charAt(n - 2) == '1' && s.charAt(n - 1) < '7')) {
            count += ways(s, n - 2);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.print(new NumberDecoding().numDecodings("12"));
    }
}
