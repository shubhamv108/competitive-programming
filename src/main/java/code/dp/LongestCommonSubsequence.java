package code.dp;

import java.util.Arrays;

public class LongestCommonSubsequence {

    int[][] dp;
    char[] a, b;
    int lcsLenHelper(int ai, int bi) {
        if(a.length == 0 || b.length == 0 || ai >= a.length || bi >= b.length) return 0;
        if(dp[ai][bi] != -1) return dp[ai][bi];
        else  {
            if(a[ai] == b[bi]) return dp[ai][bi] = 1 + lcsLenHelper(ai+1, bi+1);
            else {
                int f = lcsLenHelper(ai + 1, bi);
                int s = lcsLenHelper(ai, bi + 1);
                return dp[ai][bi] = Math.max(f, s);
            }
        }
    }

    int lcsLen(char a[], char b[])
    {
        this.a = a;
        this.b = b;
        dp = new int [100][100];
        Arrays.fill(dp, -1);
        return lcsLenHelper(0, 0);
    }

}
