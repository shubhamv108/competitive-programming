package code.dynamicprogramming.spoj;

import java.lang.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ANARC05H {
    static int N;
    static char[] a;

    static int solve(int prevSum, int idx) {
        int ret = 0, curSum = 0;
        if(idx == N) return 1;
        for(int i=idx; i<N; i++) {
            curSum += a[idx] - '0';
            if(curSum >= prevSum) ret += solve(curSum, i + 1);
        } return ret;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = 1;
        while(true) {
            String s = reader.readLine();
            if ("bye".equals(s)) break;
            N = s.length();
            a = s.toCharArray();
            System.out.println((t++) + ". " + solve(0, 0));
        }
    }

}
