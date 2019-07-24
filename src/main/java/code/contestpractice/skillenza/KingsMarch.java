package code.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KingsMarch {

    private class Pair {
        int points;
        int paths;
        public Pair(int points, int paths) {
            this.points = points;
            this.paths = paths;
        }

        @Override
        public String toString() {
            return points + " " + paths;
        }
    }

    private class Solution {

        public Pair solve() {
            String[] line = null;
            int n = InputUtils.nextInt();
            Pair[] pointsPaths = new Pair[n];
            for (int i = 0; i < n; i++) {
                line = InputUtils.splitNextLine();
                for (int j = 0; j < n; j++) {
                   assignPointsPathsPair(line[j], i, j, pointsPaths);
                }
            }
            return pointsPaths[n-1];
        }

        private void assignPointsPathsPair(String s, int i, int j, Pair[] pointsPaths) {
            Pair result = null;
            if (s.equals("x")) pointsPaths[j] = new Pair(-1, 0);
            else if (s.equals("e")) pointsPaths[j] = new Pair(0, 1);
            else {
                result = new Pair(-1, 0);
                if (pointsPaths[j] != null && pointsPaths[j].points != -1) {
                    result.points = pointsPaths[j].points;
                    result.paths = pointsPaths[j].paths;
                }
                if (j > 0 && pointsPaths[j-1].points != -1) {
                    if (result.points == pointsPaths[j-1].points) {
                        result.paths = result.paths + pointsPaths[j-1].paths;
                    } else if (pointsPaths[j-1].points > result.points) {
                        result.points = pointsPaths[j-1].points;
                        result.paths = pointsPaths[j-1].paths;
                    }
                }
                pointsPaths[j] = result;
                if(pointsPaths[j].points != -1 && !s.equals("s"))
                    pointsPaths[j].points += Integer.valueOf(s);
            }
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
            Pair p = new KingsMarch().new Solution().solve();
            if (p.points < 0) {
                p.points = 0;
                p.paths = 0;
            }
            System.out.println(p);
        }
    }

    /**
     * 25
     * 2
     * e 1
     * 1 s
     * 2
     * e x
     * x s
     * 3
     * e x x
     * x x x
     * x x s
     * 3
     * e 1 1
     * 1 1 1
     * 1 1 s
     * 4
     * e 1 1 1
     * 1 1 1 1
     * 1 1 1 1
     * 1 1 1 s
     * 4
     * e 1 1 1
     * 1 x 1 1
     * 1 1 x 1
     * 1 1 1 s
     * 5
     * e 1 1 1 1
     * 1 1 1 1 1
     * 1 1 1 1 1
     * 1 1 1 1 1
     * 1 1 1 1 s
     * 5
     * e 1 1 1 x
     * 1 x x x 1
     * 1 x 9 x 1
     * 1 x x x 1
     * x 1 1 1 s
     * 3
     * e 2 3
     * 2 x 2
     * 1 2 s
     * 3
     * e 2 1
     * 2 x 2
     * 1 2 s
     * 3
     * e 2 x
     * 2 x 2
     * x 2 s
     * 3
     * e 1 x
     * 4 x x
     * x 1 s
     * 3
     * e 1 1
     * 1 x 1
     * 1 1 s
     * 3
     * e 1 1
     * x x x
     * 1 1 s
     * 6
     * e 9 6 1 5 5
     * 2 4 9 3 x 1
     * 6 2 8 x 4 5
     * 7 9 7 1 1 1
     * 2 3 5 4 4 4
     * 4 4 3 9 8 s
     * 9
     * e 4 x 9 1 7 5 9 1
     * 2 9 8 2 9 6 x 8 8
     * 9 5 9 5 7 1 x 2 1
     * 2 3 8 9 x 3 8 7 8
     * 8 8 9 2 x 2 7 8 2
     * 4 6 2 6 8 7 9 5 9
     * x 6 3 8 8 3 5 8 7
     * 9 5 7 3 5 8 4 8 1
     * x 4 4 5 8 7 4 1 s
     * 7
     * e 2 8 7 6 7 4
     * 2 6 6 2 x 6 7
     * 3 1 1 4 x 7 2
     * 1 4 2 6 1 7 6
     * x 7 8 9 x 4 x
     * 7 1 1 4 x 2 4
     * 8 6 5 9 1 1 s
     * 7
     * e 5 4 9 9 3 7
     * x 3 1 4 5 7 5
     * 3 6 3 x 6 x 5
     * 7 1 5 8 x 9 1
     * 8 4 3 9 6 8 3
     * 2 x x 5 9 3 7
     * 3 2 6 4 7 4 s
     * 3
     * e 6 1
     * 4 8 9
     * 9 9 s
     * 10
     * e x 6 2 x 3 5 9 8 4
     * 3 3 5 7 4 2 8 8 4 8
     * 4 2 5 8 8 5 5 x 7 2
     * 1 5 3 2 1 9 3 4 6 9
     * x x 7 1 4 3 8 3 x 1
     * x 8 8 8 8 1 4 9 5 9
     * 7 6 9 2 2 6 1 4 7 4
     * 7 9 8 2 1 4 9 8 4 x
     * 8 6 2 3 1 6 3 3 3 5
     * 2 5 7 7 9 2 4 6 3 s
     * 7
     * e 5 7 6 1 6 2
     * 1 7 8 6 3 9 1
     * 9 5 6 8 9 7 x
     * 8 5 8 5 8 7 8
     * 1 4 4 6 4 1 5
     * 8 9 6 1 5 x 8
     * 9 9 5 2 8 8 s
     * 10
     * e 6 4 9 x x 6 x 1 x
     * 4 8 6 x 2 8 x 6 5 6
     * 3 4 1 9 7 4 5 6 1 2
     * x 4 3 x 9 9 1 1 6 4
     * 9 5 3 x 8 4 5 3 x 3
     * x x x x 8 1 6 8 x x
     * 5 4 9 x x x x 5 2 x
     * 1 6 6 2 x 2 1 x 6 2
     * 5 3 x 8 9 x x 2 2 1
     * 2 3 6 1 7 8 7 3 1 s
     * 6
     * e x 9 3 8 3
     * 5 x 3 9 9 x
     * 9 7 3 8 6 1
     * x 3 8 6 2 6
     * 8 3 5 1 1 x
     * 5 x 9 6 x s
     * 7
     * e x 8 6 4 3 x
     * x x 3 8 2 6 8
     * 2 6 x 5 5 8 4
     * 4 4 4 2 7 9 x
     * x 7 8 3 x 6 3
     * 4 3 6 6 x 1 7
     * 1 x 8 2 9 5 s
     * 4
     * e 2 2 2
     * 1 x x 2
     * 1 x x 1
     * 1 1 2 s
     */

}
