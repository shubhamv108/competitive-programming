package code.shubham.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class DimmestStar {

    private Map<Integer, Set<Integer>> edges;
    private class Solution {

        private Solution() {
            edges = new HashMap<>();
        }

        private void addEdge(Integer u, Integer v) {
            Set<Integer> l = edges.get(u);
            if (null == l) {
                l = new TreeSet<>((x, y) -> y - x);
                edges.put(u, l);
            }
            l.add(v);

        }
        private Integer solve(Integer p) {
            Set<Integer> v = new HashSet<>();
            Queue<Integer> q = new LinkedList<>();
            q.offer(p);
            Integer c = null;
            Set<Integer> s = null;
            while (!q.isEmpty()) {
                c = q.peek();
                s = edges.get(c);
                if (s != null) {
                    s.stream().filter(e -> !v.contains(e)).forEach(q::offer);
                }
                if (q.size() == 1) return c;
                q.poll();
                v.add(c);
            }
            return p;
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
        DimmestStar dimmestStar = new DimmestStar();
        while (t-- > 0) {
            n = InputUtils.nextInt();
            Solution solution = dimmestStar.new Solution();
            while (n-- > 0) {
                line = InputUtils.splitNextLine();
                solution.addEdge(Integer.valueOf(line[0]), Integer.valueOf(line[1]));
            }
            String p = InputUtils.nextLine();
            System.out.println(
                 solution.solve(Integer.valueOf(p))
            );
        }
    }
}

