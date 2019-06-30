package code.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class SpoiltForChoice {

    private class Solution {
        private int minSecondTime = -1;
        private int maxSecondTime = -1;
        private int duration = -1;
        private int seconds = 86401;
        private int[] secondsCounter = new int[seconds];
        private int maxChannels = 0;
        private int durationSeconds = 0;
        private void addShow(String s, String e) {
            int startSeconds = toSecond(s);
            int endSeconds   = toSecond(e);
            for (int i = startSeconds + 1; i <= endSeconds; i++) {
                if (i == 0) continue;
                if (secondsCounter[i] == 0) {
                    durationSeconds++;
                }
                if (secondsCounter[i] == 1) {
                    durationSeconds--;
                }
                secondsCounter[i] += 1;
                maxChannels = Math.max(maxChannels, secondsCounter[i]);
            }
        }

        private String getSolution() {
            return maxChannels + " " + toTime(durationSeconds);
        }

        private String toTime(int seconds) {
            int secs  = seconds % 60;
            seconds   = ((Double) Math.floor(seconds / 60)).intValue();
            int mins  = seconds % 60;
            seconds   = ((Double) Math.floor(seconds / 60)).intValue();
            int hours = seconds % 24;

            DecimalFormat formatter = new DecimalFormat("00");
            return formatter.format(hours) + ":" + formatter.format(mins) + ":" + formatter.format(secs);
        }

        private int toSecond(String s) {
            String[] time = s.split(":");
            return  (Integer.valueOf(time[0]) * 60 * 60) +
                    (Integer.valueOf(time[1]) * 60) +
                    (Integer.valueOf(time[2]));
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
        while (t-- > 0) {
            int n = InputUtils.nextInt();
            Solution solution = new SpoiltForChoice().new Solution();
            for (int i = 0 ; i < n; i++) {
                String[] line = InputUtils.splitNextLine();
                String s = line[0];
                String e = line[1];
                solution.addShow(s, e);
            }
            System.out.println(solution.getSolution());
        }
    }

}
