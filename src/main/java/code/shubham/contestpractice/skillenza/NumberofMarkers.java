package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;


public class NumberofMarkers {

    private class Interval {
        private int s;
        private int e;
        private Interval(int s, int e) {
            this.s = s;
            this.e = e;
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
        String line[] = null;
        int n;
        NumberofMarkers problemNameObject = new NumberofMarkers();
        while (t-- > 0) {
            n = InputUtils.nextInt();
            Interval[] intervals = new Interval[n];
            int i = 0;
            int result = 0;
            while (i < n) {
                line = InputUtils.splitNextLine();
                intervals[i] = problemNameObject.new Interval(Integer.valueOf(line[0]), Integer.valueOf(line[1]));
                i++;
            }
            Arrays.sort(intervals, Comparator.comparingInt(a -> a.s));
            int index = 0;
            for (i = 1; i < n; i++) {
                if (intervals[index].e >= intervals[i].s) {
                    intervals[index].s = Math.min(intervals[index].s, intervals[i].s);
                    intervals[index].e = Math.max(intervals[index].e, intervals[i].e);
                } else {
                    result += intervals[index].e - intervals[index].s + 1;
                    index = i;
                }
            }
            result += intervals[index].e - intervals[index].s + 1;
            System.out.println(
                result
            );
        }
    }
}

