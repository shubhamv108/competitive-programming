package code.dynamicprogramming;

import code.utils.ArrayUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence {

    int[][] dp;
    char[] a, b;
    int lcsLenHelper(int ai, int bi) {
        if(ai < 0 || bi < 0) return 0;
        if(dp[ai][bi] != -1)    return dp[ai][bi];
        else if(a[ai] == b[bi]) return dp[ai][bi] = 1 + lcsLenHelper(ai-1, bi-1);
             else               return dp[ai][bi] = Math.max(lcsLenHelper(ai - 1, bi), lcsLenHelper(ai, bi - 1));
    }



    int lcsLen(char a[], char b[]) {
        String aa = "da";
        this.a = a;
        this.b = b;
        dp = new int [100][100];
        ArrayUtils.fill2DWithMinusOne(dp);
        return lcsLenHelper(a.length-1, b.length-1);
    }

    public static void main(String[] args) {
        String A = "abcba";
        String B = "bca";
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        System.out.println(new LongestCommonSubsequence().lcsLen(a, b));
    }

    static class TestClass {

        private static Map<Integer, Map<Integer, Integer>> cache;
        private static String a, b;
        public static void main(String args[] ) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            a = br.readLine();
            b = br.readLine();
            if (a.length() == 0 || b.length() == 0) {
                System.out.println(0);
                return;
            }
            cache = new HashMap<>();
            System.out.println(lcsLength(0, 0));
        }

        private static int lcsLength(int ai, int bi) {
            if (ai >= a.length() || bi >= b.length()) return 0;
            if (cache.get(ai) == null) cache.put(ai, new HashMap<>());
            if (cache.get(ai).get(bi) != null) return cache.get(ai).get(bi);
            else if (a.charAt(ai) == b.charAt(bi)) {
                cache.get(ai).put(bi, 1 + lcsLength(ai+1, bi+1));
            } else {
                cache.get(ai).put(bi, 0);
            }
            return cache.get(ai).get(bi);
        }
    }

    static class SolutionDP {

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String A = br.readLine();
            String B = br.readLine();
            if (A.length() == 0 || B.length() == 0) {
                System.out.println(0);
                return;
            }
            int cache[][] = new int[2][B.length() + 1];
            int bi = 0;
            int maxLength = 0;
            for (int i = 0; i <= A.length(); i++) {
                bi = i & 1;
                for (int j = 0; j <= B.length(); j++) {
                    if (i == 0 || j == 0) cache[bi][j] = 0;
                    else if (A.charAt(i - 1) == B.charAt(j - 1)) {
                        cache[bi][j] = cache[1 - bi][j - 1] + 1;
                        maxLength = Math.max(cache[bi][j], maxLength);
                    }
                    else cache[bi][j] = 0;
                }
            }
            System.out.println(maxLength);
        }
    }

    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            return lcs(text1.toCharArray(), text2.toCharArray(), text1.length(), text2.length(), new int[text1.length() + 1][text2.length() + 1]);
        }

        int lcs(char[] a, char[] b, int n, int m, int[][] dp) {
            if (n == 0 || m == 0)
                return 0;
            if (dp[n][m] != 0)
                return dp[n][m];
            if (a[n-1] == b[m-1])
                return dp[n][m] = 1 + lcs(a, b, n-1, m-1, dp);
            return dp[n][m] = Math.max(lcs(a, b, n-1, m, dp), lcs(a, b, n, m-1, dp));
        }
    }

}
