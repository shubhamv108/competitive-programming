package code.shubham.dynamicprogramming;

public class InsertionsToMakeStringPalindrome {
    int[][] dp;
    char[] s;
    int insertionsToMakePalindromeHelper(int l, int h) {
        if(l >= h) return 0;
        if(dp[l][h] != 0) return dp[l][h];
        else {
            if(s[l] == s[h])  return dp[l][h] = insertionsToMakePalindromeHelper(l+1, h-1);
            else              return dp[l][h] = 1 + Math.min(insertionsToMakePalindromeHelper(l+1, h),
                                                             insertionsToMakePalindromeHelper(l, h-1));
        }
    }

    int insertionsToMakePalindrome(char a[])
    {
        if(a.length == 0) return 0;
        dp = new int[a.length+1][a.length+1];
        s = a;
        return insertionsToMakePalindromeHelper(0, a.length - 1);
    }

    public static void main(String[] args) {

    }
}
