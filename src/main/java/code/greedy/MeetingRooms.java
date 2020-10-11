package code.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MeetingRooms {

    static class Interval implements Comparable<Interval> {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Interval that) {
            if (this.start - that.start == 0) {
                return this.end - that.end;
            }
            return this.start - that.start;
        }
    }

    // nlogn + n^2
    public class Solution1 {
        public int solve(ArrayList<ArrayList<Integer>> A) {
            List<Interval> intervals = A.stream().map(a -> new Interval(a.get(0), a.get(1)))
                    .collect(Collectors.toList());
            Collections.sort(intervals);

            List<Interval> rooms = new ArrayList<>();
            int result = Integer.MIN_VALUE;
            for (int i = 0; i < intervals.size(); i++) {
                Interval b = intervals.get(i);
                boolean isRoomAssigned = false;
                for (int j = 0; j < rooms.size(); j++) {
                    Interval a = rooms.get(j);
                    if (b.start < a.end) continue;
                    else {
                        a.start = b.start;
                        a.end = b.end;
                        isRoomAssigned = true;
                        break;
                    }
                }
                if (!isRoomAssigned) {
                    rooms.add(b);
                }
                result = Math.max(result, rooms.size());
            }
            return result;
        }
    }

    // O(n)
    class Solution2 {

        public int solve(ArrayList<ArrayList<Integer>> A) {
            int result = 0;
            TreeMap<Integer, int[]> timings = new TreeMap<>();
            A.stream().forEach(a -> {
                int[] c = timings.get(a.get(0));
                if (c == null) timings.put(a.get(0), c = new int[2]);
                c[0]++;

                c = timings.get(a.get(1));
                if (c == null) timings.put(a.get(1), c = new int[2]);
                c[1]++;
            });

            int rooms = 0;
            for (Map.Entry<Integer, int[]> e : timings.entrySet()) {
                rooms += e.getValue()[0];
                rooms -= e.getValue()[1];
                result = Math.max(result, rooms);
            }
            return result;
        }
    }

    public static void main(String[] args) {

    }

}
