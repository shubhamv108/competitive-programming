package code.shubham.contestpractice.oa.swiggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class HungerGames {

    static class Solution {
        public static void main(String args[] ) throws Exception {
            int[] nM = InputUtils.nextLineIntArray();
            int n = nM[0], k = nM[1];
            PriorityQueue<Integer> both = new PriorityQueue<>();
            PriorityQueue<Integer> ram = new PriorityQueue<>();
            PriorityQueue<Integer> shyam = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                int[] A = InputUtils.nextLineIntArray();
                System.out.println(A[0] + " " + A[1] + " " + A[2]);
                if (A[1] - 1 == 0 && A[2] - 1 == 0)
                    both.offer(A[0]);
                else if (A[1] == 1)
                    ram.offer(A[0]);
                else if (A[2] == 1)
                    shyam.offer(A[0]);
            }

            int cost = 0;

            while (k-- > 0) {
                if (!both.isEmpty() && !ram.isEmpty() && both.peek() < ram.peek()) {
                    cost += both.poll();
                } else if (!both.isEmpty() && !ram.isEmpty() && both.peek() < ram.peek()) {
                    cost += both.poll();
                } else if (!ram.isEmpty() && !shyam.isEmpty()) {
                    cost += ram.poll();
                    cost += shyam.poll();
                } else if (!both.isEmpty()) {
                    cost += both.poll();
                } else {
                    System.out.print(-1);
                    return;
                }
            }
            System.out.println(cost);
        }

        private static class InputUtils {
            private static BufferedReader BR;
            private static InputStreamReader inputStreamReader;
            static {
                BR = getBR();
            }
            public static BufferedReader getBR() {
                if (null == BR) {
                    inputStreamReader = new InputStreamReader(System.in);
                    BR = new BufferedReader(inputStreamReader);
                }
                return BR;
            }
            public static int[] nextLineIntArray() {
                return Arrays.stream(splitNextLine()).mapToInt(Integer::valueOf).toArray();
            }
            public static long[] nextLinelongArray() {
                return Arrays.stream(splitNextLine()).mapToLong(Long::valueOf).toArray();
            }
            public static double[] nextLinedoubleArray() {
                return Arrays.stream(splitNextLine()).mapToDouble(Double::valueOf).toArray();
            }
            public static Integer[] nextLineIntegerArray() {
                return Arrays.stream(splitNextLine()).mapToInt(Integer::valueOf).boxed().toArray(Integer[]::new);
            }
            public static Long[] nextLineLongArray() {
                return Arrays.stream(splitNextLine()).mapToLong(Long::valueOf).boxed().toArray(Long[]::new);
            }
            public static Double[] nextLineDoubleArray() {
                return Arrays.stream(splitNextLine()).mapToDouble(Double::valueOf).boxed().toArray(Double[]::new);
            }
            public static String[] splitNextLine() {
                return splitNextLine(BR, "\\s");
            }
            public static String[] splitNextLine(BufferedReader br) {
                return splitNextLine(br, " ");
            }
            public static String[] splitNextLine(BufferedReader br, String regex) {
                return nextLine(br).split(regex);
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

    }

}
