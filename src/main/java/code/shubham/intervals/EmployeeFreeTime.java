package code.shubham.intervals;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class EmployeeFreeTime {

    class Solution {

        int[][] findEmployeeFreeTime(int[][] A) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int[] a : A) {
                min = Math.min(min, a[0]);
                max = Math.max(max, a[1]);
            }

            int[] range = new int[max - min + 1];
            for (int[] a : A) {
                ++range[a[0] - min];
                --range[a[1] - min];
            }

            ArrayList<int[]> result = new ArrayList<>();
            int busyEmployees = 0, freeTimeStart = -1;
            for (int i = 0; i < range.length; ++i) {
                busyEmployees += range[i];
                if (busyEmployees != 0 && freeTimeStart != -1) {
                    result.add(new int[]{ freeTimeStart, i });
                    freeTimeStart = -1;
                } else if (busyEmployees == 0 && freeTimeStart == -1) {
                    freeTimeStart = i;
                }
            }

            return result.toArray(int[][]::new);
        }

    }


    // Definition for an Interval.
    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };

    // Time: O(N * logN), Space: O(N)
    class Solution2 {
        public List<Interval> employeeFreeTime(List<List<Interval>> A) {
            TreeMap<Integer, Integer> range = new TreeMap<>();
            for (List<Interval> a : A) {
                for (Interval aa : a) {
                    range.merge(aa.start, 1, Integer::sum);
                    range.merge(aa.end, -1, Integer::sum);
                }
            }

            ArrayList<Interval> result = new ArrayList<>();
            int busyEmployees = 0, freeTimeStart = -1;
            for (int i : range.keySet()) {
                busyEmployees += range.getOrDefault(i, 0);
                if (busyEmployees != 0 && freeTimeStart != -1) {
                    result.add(new Interval(freeTimeStart, i));
                    freeTimeStart = -1;
                } else if (busyEmployees == 0 && freeTimeStart == -1) {
                    freeTimeStart = i;
                }
            }

            return result;
        }
    }

    class Solution3 {
        public List<Interval> employeeFreeTime(List<List<Interval>> A) {
            PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> A.get(x[0]).get(x[1]).start == A.get(y[0]).get(y[1]).start
                    ? A.get(x[0]).get(x[1]).end - A.get(y[0]).get(y[1]).end
                    : A.get(x[0]).get(x[1]).start - A.get(y[0]).get(y[1]).start);

            for (int i = 0; i < A.size(); ++i)
                q.offer(new int[] { i, 0 } );

            ArrayList<Interval> result = new ArrayList<>();
            while (!q.isEmpty()) {
                int[] p = q.poll();
                if (p[1] < A.get(p[0]).size() - 1)
                    q.offer(new int[] { p[0], p[1] + 1 });

                int[] nextInterval = q.peek();
                if (nextInterval == null)
                    break;

                if (A.get(p[0]).get(p[1]).end < A.get(nextInterval[0]).get(nextInterval[1]).start)
                    result.add(new Interval(A.get(p[0]).get(p[1]).end, A.get(nextInterval[0]).get(nextInterval[1]).start));
            }

            return result;
        }
    }

    class Solution4 {
        public List<Interval> employeeFreeTime(List<List<Interval>> A) {
            PriorityQueue<int[]> q = new PriorityQueue<>((x, y) ->
                 A.get(x[0]).get(x[1]).start == A.get(y[0]).get(y[1]).start
                         ? A.get(x[0]).get(x[1]).end - A.get(y[0]).get(y[1]).end
                         : A.get(x[0]).get(x[1]).start - A.get(y[0]).get(y[1]).start);

            for (int i = 0; i < A.size(); ++i)
                q.offer(new int[] { i, 0 } );

            ArrayList<Interval> result = new ArrayList<>();
            Interval prev = A.get(q.peek()[0]).get(q.peek()[1]);
            while (!q.isEmpty()) {
                int[] p = q.poll();
                Interval cur = A.get(p[0]).get(p[1]);

                cur.end = Math.max(cur.end, prev.end);
                if (prev.end < cur.start)
                    result.add(new Interval(prev.end, cur.start));

                if (p[1] < A.get(p[0]).size() - 1)
                    q.offer(new int[] { p[0], p[1] + 1 });
                prev = cur;
            }

            return result;
        }
    }
}
