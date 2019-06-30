package code.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Pratillipi {

    /**
     * ToDo
     */
    private static class One {
        private class Solution {

            private int N, K, val[];
            private Map<Integer, Set<Integer>> adj = new HashMap<>();
            private int result;

            private Solution(int N, int K, int[] val) {
                this.N = N;
                this.K = K;
                this.val = val;
            }

            private void insertEdge(int u, int v) {
                Set<Integer> children = adj.get(u);
                if (children == null) {
                    children = new HashSet<>();
                    adj.put(u, children);
                }
                adj.get(u).add(v);
            }

            private int solve() {
                subTreeSum(1);
                return result;
            }

            private class NodeSum {
                private int n;
                private int sum;
                private NodeSum(int n, int sum) {
                    this.n = n;
                    this.sum = sum;
                }

                @Override
                public boolean equals(Object obj) {
                    boolean isEqual = false;
                    if (obj instanceof NodeSum) {
                        isEqual = n == ((NodeSum) obj).n;
                    }
                    return isEqual;
                }
            }

            private int subTreeSum(int r) {
                int sum = val[r-1];
                Set<Integer> children = adj.get(r);
                if (children != null) {
                    Set<NodeSum> childSums = new TreeSet<>((x, y) -> x.sum - y.sum);
                    children.forEach(c -> childSums.add(new NodeSum(c, subTreeSum(c))));
                    for (NodeSum nodeSum : childSums) {
                        if (nodeSum.sum > K - sum) {
                            result++;
                            children.remove(nodeSum.n);
                        } else {
                            sum += nodeSum.sum;
                        }
                    }
                }

                return sum;
            }

        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] arr = new int[N];
            IntStream.range(0, N).forEach(i -> arr[i] = sc.nextInt());
            Solution solution = new One().new Solution(N, K, arr);
            for (int i = 0; i < N - 1; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                solution.insertEdge(u, v);
            }
            System.out.println(solution.solve());
        }
    }

    /**
     * ToDo
     */
    private static class Two {

        private class Solution {

            final int mod = 10000000;

            private Solution() {

            }

            private int solve(int n, int m) {
                int result = 0;
                if (n == 0) return 0;
                if (n == 1) return 3;
                if (m == 0) return 2;
                if (m >= n) {
                    m = n - 1;
                    result += 1;
                }
                if (m > 0)
                    for (int i = m; i > 0; i--)
                        result += getCombinations(n, m);
                return result + 2;
            }

            private int getCombinations(int n, int m) {
                int num = IntStream.range(n-m, n+1)
                        .reduce(1, (prod, i) -> prod = (prod * i) % mod);
                int d = IntStream.range(1, n-m)
                        .reduce(1, (prod, i) -> prod = (prod * i) % mod);
                return ((Double) Math.floor(num/d)).intValue() * 2;
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
            int n = InputUtils.nextInt();
            int m = InputUtils.nextInt();
            System.out.println(
                new Two().new Solution().solve(n, m)
            );
        }
    }

}