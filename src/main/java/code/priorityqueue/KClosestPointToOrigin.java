package code.priorityqueue;

import java.util.PriorityQueue;

public class KClosestPointToOrigin {

    // PriorityQueue
    class Solution {
        class Pair {
            int[] point;
            double dist;
            Pair(int[] p, double d) {
                this.point = p;
                this.dist = d;
            }
        }

        public int[][] kClosest(int[][] points, int k) {
            PriorityQueue<Pair> closest = new PriorityQueue<>((a, b) -> b.dist - a.dist > 0 ? 1 : -1);
            int i = 0;
            for (; i < k; i++)
                closest.offer(new Pair(points[i], dist(points[i])));
            for (; i < points.length; i++) {
                double dist = dist(points[i]);
                if (closest.peek().dist > dist) {
                    closest.poll();
                    closest.offer(new Pair(points[i], dist));
                }
            }
            int[][] result = new int[k][2];
            for (i = 0; i < k; i++)
                result[i] = closest.poll().point;
            return result;
        }

        double dist(int[] pointA) {
            return Math.sqrt(
                            Math.pow((pointA[0] - 0), 2)
                            +
                            Math.pow((pointA[1] - 0), 2)
            );
        }
    }

    // QuickSelect
    class Solution2 {

        class Pair {
            int[] point;
            double dist;
            Pair(int[] p, double d) {
                this.point = p;
                this.dist = d;
            }
        }

        public int[][] kClosest(int[][] points, int k) {
            System.out.print(points.length);
            quickSelect(points, 0, points.length - 1, k - 1);
            int[][] result = new int[k][2];
            for (int i = 0; i < k; i++)
                result[i] = points[i];
            return result;
        }

        void quickSelect(int[][] points, int start, int end, int k) {
            int l = start, r = end;
            System.out.println(l + " " + r);
            double pivot = dist(points[l + (r - l) / 2]);
            while (l <= r) {
                while (l <= r && dist(points[l]) < pivot) l++;
                while (l <= r && dist(points[r]) > pivot) r--;
                if (l <= r) {
                    swap(points, l, r);
                    l++;
                    r--;
                }
            }
            if (k >= l) {
                quickSelect(points, l, end, k);
            } else if (k <= r) {
                quickSelect(points, start, r, k);
            }
            return;
        }

        double dist(int[] point) {
            return Math.sqrt(
                    Math.pow((point[0] - 0), 2)
                            +
                            Math.pow((point[1] - 0), 2)
            );
        }

        void swap(int[][] nums, int i, int j) {
            int[] t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

}
