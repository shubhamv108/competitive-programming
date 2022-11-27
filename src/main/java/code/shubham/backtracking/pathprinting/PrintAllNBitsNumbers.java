package code.shubham.backtracking.pathprinting;

import java.util.List;

//tested for upto base 20
public class PrintAllNBitsNumbers {
    private static List<String> ans;
    static void printAllNBitsNumbersWithGivenBase(int n, char[] p, int pi , int base) {
        if(pi == n) {
            ans.add(new String(p));
            for(int i = 0; i < n; i++) System.out.printf("%c", p[i]);
            System.out.printf("\n");
            return;
        }
        for(int i=0;i<base;i++) {
            if(i > 9)
                p[pi] = (char) ((i%10) + 65);
            else
                p[pi] = (char) (i+48);
            printAllNBitsNumbersWithGivenBase(n, p, pi + 1, base);
        }
    }

    public static void main(String[] args) {
        printAllNBitsNumbersWithGivenBase(2, new char[3], 0, 20);
    }
}
