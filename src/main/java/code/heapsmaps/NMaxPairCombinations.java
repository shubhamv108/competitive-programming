package code.heapsmaps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class NMaxPairCombinations {

    private class Solution {
        private int[] solve(int[] A, int[] B) {
            int[] result = new int[A.length];
            Arrays.sort(A);
            Arrays.sort(B);
            Set<String> s = new HashSet<>();
            PriorityQueue<Pair> q = new PriorityQueue<>();
            q.offer(new Pair(A[A.length - 1] + B[B.length - 1], A.length - 1, B.length - 1));
            s.add((A.length - 1) + ":" + (B.length -1));
            Pair p = null;
            for (int i = 0; i < A.length; i++) {
                p = q.poll();
                result[i] = p.sum;
                if (p.x > 0 && !s.contains((p.x - 1) + ":" + p.y)) {
                    q.offer(new Pair(A[p.x - 1] + B[p.y], p.x - 1, p.y));
                    s.add((p.x - 1) + " " + p.y);
                }
                if (p.y  > 0 && !s.contains(p.x + ":" + (p.y - 1))) {
                    q.offer(new Pair(A[p.x] + B[p.y - 1], p.x, p.y - 1));
                    s.add(p.x + " " + (p.y - 1));
                }
            }
            return result;
        }
    }

    private class Pair implements Comparable<Pair> {
        private int sum;
        private int x, y;
        private Pair(int sum, int x, int y) {
            this.sum = sum;
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair p) {
            return p.sum - this.sum;
        }
    }

    public static void main(String[] args) {
        Arrays.stream(
                new NMaxPairCombinations().new Solution().solve(new int[] {3,2,4,2}, new int[] {4,3,1,2})
        )
                .forEach(System.out::println);

    }

}
