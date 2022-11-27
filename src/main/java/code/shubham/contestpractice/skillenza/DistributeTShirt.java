package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;


public class DistributeTShirt {

    private class Solution {

        private int[] ages;

        private Solution(int[] ages) {
            this.ages = ages;
        }

        private int[] solve() {
            int[] tShirts = new int[ages.length];
            tShirts[0] = 1;
            IntStream.range(1, ages.length).forEach(i -> {
                tShirts[i] = ages[i] > ages[i - 1] ? tShirts[i - 1] + 1 : 1;
            });

            IntStream.range(0, ages.length - 1).map(i -> (ages.length - 1) - i -1).forEach(i -> {
                tShirts[i] = Math.max(tShirts[i], ages[i] > ages[i + 1] ? tShirts[i + 1] + 1 : tShirts[i]);
            });
            return tShirts;
        }

    }

    private static class InputUtils {

        private static BufferedReader BR;
        private static InputStreamReader inputStreamReader;

        public static BufferedReader getBR() {
            if (null == BR) {
                inputStreamReader = new InputStreamReader(System.in);
                BR = new BufferedReader(inputStreamReader);
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
        String[] line = null;
        int n;
        DistributeTShirt distributeTShirt = new DistributeTShirt();
        while (t-- > 0) {
            n = InputUtils.nextInt();
            line = InputUtils.splitNextLine();
            Arrays.stream(distributeTShirt.new Solution(Arrays.stream(line).mapToInt(Integer::valueOf).toArray()).solve())
                    .forEach(a -> System.out.print(a + " "));
            System.out.println();
        }
    }
}


