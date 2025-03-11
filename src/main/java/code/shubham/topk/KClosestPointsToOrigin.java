package code.shubham.topk;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    class Solution {

        class Pair {
            int[] point;
            double dist;
            Pair(int[] p, double d) {
                this.point = p;
                this.dist = d;
            }
        }

        public int[][] kClosest(int[][] A, int k) {
            PriorityQueue<Pair> q = new PriorityQueue<>((a, b) -> b.dist - a.dist > 0 ? 1 : -1);

            int i = 0;
            for (; i < k; i++)
                q.offer(new Pair(A[i], dist(A[i])));

            for (; i < A.length; ++i) {
                double d = dist(A[i]);
                if (q.peek().dist > d) {
                    q.poll();
                    q.offer(new Pair(A[i], d));
                }
            }

            int[][] result = new int[k][2];
            for (i = 0; i < k; ++i)
                result[i] = q.poll().point;
            return result;
        }

        double dist(int[] a) {
            return Math.sqrt(
                    Math.pow((a[0] - 0), 2)
                            +
                            Math.pow((a[1] - 0), 2)
                            );
        }
    }

    class Solution2 {

        public int[][] kClosest(int[][] A, int k) {
            return quickSelect(A, k);
        }

        int[][] quickSelect(int[][] A, int k) {
            int l = 0, r = A.length - 1, p = A.length;
            while (p != k) {
                p = partition(A, l, r);
                if (p < k)
                    l = p;
                else
                    r = p - 1;
            }

            return Arrays.copyOf(A, k);
        }

        int partition(int[][] A, int l, int r) {
            int[] m = A[l + (r - l) / 2];
            double mdist = dist(m);
            while (l < r) {
                if (dist(A[l]) >= mdist) {
                    int[] t = A[l];
                    A[l] = A[r];
                    A[r] = t;
                    --r;
                } else {
                    ++l;
                }
            }

            if (dist(A[l]) < mdist)
                ++l;
            return l;
        }

        double dist(int[] a) {
            return
                    Math.pow((a[0] - 0), 2)
                            +
                            Math.pow((a[1] - 0), 2);
        }
    }

}
