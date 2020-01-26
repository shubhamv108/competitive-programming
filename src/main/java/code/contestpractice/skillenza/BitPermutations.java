package code.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BitPermutations {

    private class Solution {

        private int n;
        private long sum;

        private Solution(int n) {
            this.n = n;
        }

        private long solve() {
            permutate(getBinary(n), 0);
            return sum;
        }

        void permutate(char[] s, int idx) {
            if(idx == s.length - 1) {
                this.sum += Integer.parseInt(new String(s), 2);
                return;
            }
            for(int i = idx; i < s.length; i++) {
                boolean check = shouldSwap(s, idx, i);
                if (check) {
                    swap(s, idx, i);
                    permutate(s, idx + 1);
                    swap(s, idx, i);
                }
            }
        }

        boolean shouldSwap(char s[], int idx, int pos) {
            for (int i = idx; i < pos; i++) {
                if (s[i] == s[pos]) {
                    return false;
                }
            }
            return true;
        }

        void swap(char[] s, int a, int b) {
            char temp = s[a];
            s[a] = s[b];
            s[b] = temp;
        }

        private char[] getBinary(int n) {
            int[] binaryNum = new int[1000];
            int i = 0;
            while (n > 0) {
                binaryNum[i++] = n % 2;
                n = n / 2;
            }

            char[] binaryChar = new char[i];
            for (int j = i - 1; j >= 0; j--) {
                binaryChar[i - j - 1] = (char) (binaryNum[j] + '0');
            }
            return binaryChar;
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
            return splitNextLine(" ");
        }

        public static String[] splitNextLine(String regex) {
            return splitNextLine(BR, regex);
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
        int n;
        BitPermutations problemNameObject = new BitPermutations();
        while (t-- > 0) {
            n = InputUtils.nextInt();
            System.out.println(
                problemNameObject.new Solution(n).solve()
            );
        }
    }
}

