package code.contestpractice.techgig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SquareMatrix {
    public static void main(String args[] ) throws Exception {
        SquareMatrix sm = new SquareMatrix();
        int count  = 0;
        int N = InputUtils.nextInt();
        Character[][] s = new Character[N][N];
        int ii = 0;
        while (ii < N) s[ii++] = Arrays.stream(InputUtils.splitNextLine("#")).map(e -> e.charAt(0)).toArray(Character[]::new);
        char[] k = InputUtils.nextLine().toCharArray();
        Match[] oldM = new Match[N];
        Match[] newM = new Match[N];
        if (k[0] == s[0][0]) oldM[0] = sm.new Match(1, 1, 1, 0);
        newM[0] = sm.new Match(0, 0, 0, 0);
        for (int i = 1; i < N; i++) {
            oldM[i] = sm.new Match(0, 0, 0, 0);
            newM[i] = sm.new Match(0, 0, 0, 0);
            if (s[i][0] == k[oldM[i-1].h]) {
                oldM[i].h = oldM[i-1].h + 1;
            } else if (s[i][0] == k[0]) oldM[i].h = 1;
            if (k[0] == s[i][0]) { oldM[i].v = 1; oldM[i].ud = 1; oldM[i].ld = 1; }
            count = updateCount(oldM[i], count, N);
        }
        for (int c = 1; c < N; c++) {
            for (int r = 0; r < N; r++) {
                if (r - 1 > -1 && s[r][c] == k[newM[r-1].h]) {
                    newM[r].h = newM[r-1].h + 1;
                } else if (k[0] == s[r][c]) { newM[r].h = 1; } else newM[r].h = 0;
                if ( s[r][c] == k[oldM[r].v] ) {
                    newM[r].v = oldM[r].v + 1;
                } else if (s[r][c] == k[0]) newM[r].v = 1; else  newM[r].v = 0;
                if (r-1 > -1 && s[r][c] == k[oldM[r - 1].ud]) {
                    newM[r].ud = oldM[r-1].ud + 1;
                } else if (k[0] == s[r][c]) { newM[r].ud = 1; } else newM[r].ud = 0;
                if (r+1 < N && s[r][c] == k[oldM[r+1].ld]) {
                    newM[r].ld = oldM[r+1].ld + 1;
                } else if (k[0] == s[r][c]) { newM[r].ld = 1;  } else newM[r].ld = 0;
                count = updateCount(newM[r], count, N);
            }
            oldM = newM;
            newM = IntStream.range(0, N).mapToObj(i -> sm.new Match(0, 0, 0, 0)).toArray(Match[]::new);
        }
        System.out.println(count);

    }

    private static int updateCount(Match m, int count, int N) {
        if (m.v == N) count++;
        if (m.h == N) count++;
        if (m.ud == N) count++;
        if (m.ld == N) count++;
        return count;
    }

    class Match {
        int h;
        int v;
        int ud;
        int ld;
        Match(int h, int v, int ud, int ld) {
            this.h = h;
            this.v = v;
            this.ud = ud;
            this.ld = ld;
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

        public static String[] splitNextLine(String s) {
            return splitNextLine(BR, s);
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
}

