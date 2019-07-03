package code.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class NumberOfPies {

    private class Solution {
        private String solve(Integer[] weights, int sum) {
            int m = weights.length;
            boolean[][] subSet = new boolean[2][sum + 1];
            for (int i = 0; i <= m; i++)
                for (int j = 0; j <= sum; j++)
                    if (j == 0)
                        subSet[i%2][j] = true;
                    else if (i == 0)
                        subSet[i%2][j] = false;
                    else if (weights[i - 1] <= j)
                        subSet[i % 2][j] = subSet[(i + 1) % 2][j - weights[i - 1]]
                                            || subSet[(i + 1) % 2][j];
                    else
                        subSet[i % 2][j] = subSet[(i + 1) % 2][j];
            return subSet[m % 2][sum] ? "YES" : "NO";
        }
    }

    private static class InputUtils {

        private static BufferedReader BR;

        public static BufferedReader getBR() {
            if (null == BR) {
                BR = new BufferedReader(new InputStreamReader(System.in));
            }
            return BR;
        }

        public static String[] splitNextLine() {
            return splitNextLine(BR, " ");
        }

        public static String[] splitNextLine(BufferedReader br) {
            return splitNextLine(br, " ");
        }

        public static String[] splitNextLine(BufferedReader br, String regex) {
            return nextLine().split(regex);
        }

        public static String nextLine() {
            return nextLine(getBR());
        }

        public static String nextLine(BufferedReader br) {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public static int toInteger(String s) {
            return Integer.valueOf(s);
        }

        public static long toLong(String s) {
            return Long.valueOf(s);
        }

        public static int nextInt() {
            return toInteger(nextLine());
        }

        public static long nextLong() {
            return toLong(nextLine());
        }

    }

    public static void main(String[] args) {
        int t = InputUtils.nextInt();
        NumberOfPies numberOfPies = new NumberOfPies();
        while(t-- > 0) {
            String[] line = InputUtils.splitNextLine();
            int m = Integer.valueOf(line[0]);
            int n = InputUtils.nextInt();
            Integer[] weights = IntStream.range(0, m)
                    .mapToObj(i -> Integer.valueOf(line[i+1]))
                    .toArray(Integer[]::new);
            System.out.println(numberOfPies.new Solution().solve(weights, n));
        }
    }

}
