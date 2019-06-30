package code.contestpractice.hackerearth;

import java.io.*;
import java.util.*;


public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        for(int t_i=0; t_i<T; t_i++)
        {
            String S = br.readLine();
            int Q = Integer.parseInt(br.readLine().trim());
            String[] arr_x = br.readLine().split(" ");
            int[] x = new int[Q];
            for(int i_x=0; i_x<arr_x.length; i_x++)
            {
                x[i_x] = Integer.parseInt(arr_x[i_x]);
            }
            String[] arr_y = br.readLine().split(" ");
            int[] y = new int[Q];
            for(int i_y=0; i_y<arr_y.length; i_y++)
            {
                y[i_y] = Integer.parseInt(arr_y[i_y]);
            }

            int out_ = solve(y, x,Q, S);
            System.out.println(out_);
            System.out.println("");
        }

        wr.close();
        br.close();
    }


    static int solve(int[] y, int[] x,int Q, String S){

        class Point {
            int x, y;
            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Point point = (Point) o;
                return x == point.x &&
                        y == point.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }
        }

        Set<Point> reachablePoints = new HashSet<>();
        Map<Integer, Set<Point>> m = new HashMap<>();

        int l = S.length();
        int curX = 0;
        int curY = 0;
        reachablePoints.add(new Point(curX, curY));
        for (int i = 0; i < l; i++) {
            curX = 0;
            curY = 0;
            for (int j = i; j < l; j++) {

                if (m.containsKey(j)) {
                    Set<Point> set = m.get(j);
                    Point xx = new Point(curX, curY);
                    if (set.contains(xx)) {
                        continue;
                    } else {
                        set.add(xx);
                    }
                } else {
                    Set<Point> s = new HashSet<>();
                    s.add(new Point(curX, curY));
                    m.put(j, s);
                }

                char c = S.charAt(j);
                if (c == 'U') curY += 1;
                if (c == 'L') curX -= 1;
                if (c == 'R') curX += 1;
                if (c == 'D') curY -= 1;
                reachablePoints.add(new Point(curX, curY));
            }
        }

        int total = 0;

        for (int i=0; i<Q; i++) {
            if (reachablePoints.contains(new Point(x[i], y[i]))) {
                total++;
            }
        }

        return total;

    }
}