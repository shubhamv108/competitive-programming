package code.shubham.backtracking.pathprinting;

public class PrintNDigitBinaryStringsWithKBitsSet {

    static void printNBitNumbersWithKBitsSetHelper(int n, int k, char[] p, int pi, int c) {
        if(pi == n) {
            if(c == k) {
                for (int i = 0; i < n; i++) System.out.printf("%c", p[i]);
                System.out.printf("\n");
            }
            return;
        }
        if((k - c) < (n-pi)) {
            p[pi] = '0';
            printNBitNumbersWithKBitsSetHelper(n, k, p, pi + 1, c);
        }
        p[pi] = '1';
        c++;
        printNBitNumbersWithKBitsSetHelper(n, k, p, pi + 1, c);

    }

    static void printNBitNumbersWithKBitsSet(int n, int k) {
        char[] p = new char[n];
        printNBitNumbersWithKBitsSetHelper(n, k, p, 0, 0);
    }

    public static void main(String[] args) {
        printNBitNumbersWithKBitsSet(3, 2);
    }
}
