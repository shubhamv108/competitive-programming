package code.shubham.priorityqueue;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;

public class CampusBikes {

    public class Solution {
        public int[] assignBikes(int[][] workers, int[][] bikes) {
            Comparator cmp = newComparator(workers, bikes);
            PriorityQueue<int[]> q = new PriorityQueue<>(cmp);
            int workerCount = workers.length;
            int bikeCount = bikes.length;
            for (int i = 0; i < workerCount; i++)
                for (int j = 0; j < bikeCount; j++) {
                    int dist = dist(workers[i], bikes[j]);
                    q.offer(new int[] { dist, i, j } );
                }

            int[] result = new int[workerCount];
            BitSet assignedWorkers = new BitSet();
            BitSet assignedBikes = new BitSet();
            while (!q.isEmpty()) {
                int[] mapping = q.poll();
                if (!assignedWorkers.get(mapping[1])
                        && !assignedBikes.get(mapping[2])) {
                    result[mapping[1]] = mapping[2];
                    assignedWorkers.set(mapping[1]);
                    assignedBikes.set(mapping[2]);
                }
            }
            return result;
        }

        Comparator<int[]> newComparator(int[][] workers, int[][] bikes) {
            return (a, b) -> a[0] == b[0]
                                ? a[1] == b[1]
                                    ? a[2] - b[2]
                                    : a[1]  - b[1]
                                : a[0] - b[0];
        }

        int dist(int[] p1, int[] p2) {
            return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        }
    }

    public class Solution2 {
        public int[] assignBikes(int[][] workers, int[][] bikes) {
            int n = workers.length;
            int m = bikes.length;
            ArrayList<int[]>[] dist = new ArrayList[20001];
            for (int i = 0; i < 2001; i++) dist[i] = new ArrayList<int[]>();
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    dist[dist(workers[i], bikes[j])].add(new int[] { i, j });

            int[] result = new int[n];
            BitSet assignedWorkers = new BitSet();
            BitSet assignedBikes = new BitSet();
            for (int i = 0; i < 2001; i++) {
                for (int[] mapping : dist[i]) {
                    if (!assignedWorkers.get(mapping[0]) && !assignedBikes.get(mapping[1])) {
                        result[mapping[0]] = mapping[1];
                        assignedWorkers.set(mapping[0]);
                        assignedBikes.set(mapping[1]);
                    }
                }
            }
            return result;
        }


        int dist(int[] p1, int[] p2) {
            return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        }
    }
}