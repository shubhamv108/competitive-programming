package code.backtracking.pathprinting;

import java.util.Scanner;

class Solver {

    void validParanthesisOfNPairs(int n, char[] p, int pi, int open, int close) {
        if(close == n) {
            for (char ch: p) System.out.printf("%c", ch);
            System.out.printf(" ");
            return;
        }
        if(open > close) {
            p[pi] = ')';
            validParanthesisOfNPairs(n, p, pi + 1, open, close + 1);
        }
        if(open < n) {
            p[pi] = '(';
            validParanthesisOfNPairs(n, p, pi + 1, open + 1, close);
        }
    }

    public void solve (int n) {
        char[] p = new char[n*2];
        validParanthesisOfNPairs(n, p, 0, 0, 0);
    }
}

public class GenerateAllValidParenthesisCombinations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int N = Integer.parseInt(sc.next());
            new Solver().solve(N);
        }
    }
}
