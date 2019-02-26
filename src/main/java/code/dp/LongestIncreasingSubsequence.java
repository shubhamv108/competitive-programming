package code.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    int[] a;
    int[][] dp;
    int n;

    int longestLISLengthHelper(int l, int r) {
        if(l >= n || r >= n) return 0;

//        if(a[r] > a[l]) return 1 + longestLISLengthHelper(l+1, r+1)
//        else return 1 + longestLISLengthHelper()
//
//        if(dp[l][h] != -1) return dp[l][h];
//        else {
//            if()
        return 0;
    }

    int longestLISLengthHelper(int idx) {
//        if(idx >= n) return 0;
//
//
//        for(int i=idx+1;i<n;i++) {
//            if(a[idx] > a[i]) {
//
//            }
//        }
//
//
//        if(dp[l][h] != -1) return dp[l][h];
//        else {
//            if()
//        }
        return 0;
    }

    int longestLISLength(int arr[]) {
        if(arr.length == 0) return 0;
        this.a = arr;
        this.n = a.length;
        dp = new int[arr.length + 1][arr.length + 1];
        Arrays.fill(dp, -1);
        return longestLISLengthHelper(0, 1);
    }

}
