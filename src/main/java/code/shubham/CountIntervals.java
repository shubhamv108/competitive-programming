package code.shubham;

import java.util.TreeSet;

public class CountIntervals {

    TreeSet<int[]> m = new TreeSet<>((x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
    int count = 0;
    public CountIntervals() {

    }

    public void add(int left, int right) {
        int[] interval = new int[] { left, right };
        int[] floor = m.floor(interval);
        int[] ceil  = m.ceiling(interval);

        if (floor != null && floor[1] > left) {
            interval[0] = floor[1] + 1;
        }
        if (ceil != null && right > ceil[0]) {
            interval[1] = ceil[0] - 1;
            if (ceil[1] < right) {
                int[] interval2 = new int[] { interval[1] + 1, ceil[1] };
                count += (interval2[1] - interval2[0] + 1);
                m.add(interval2);
            }
        }

        m.add(interval);
        count += (interval[1] - interval[0] + 1);
    }

    public int count() {
        return count;
    }

    void main() {
        CountIntervals countIntervals = new CountIntervals();
//        countIntervals.add(2, 3);
//        countIntervals.add(7, 10);
//        System.out.println(countIntervals.count());
//        countIntervals.add(5, 8);
//        System.out.println(countIntervals.count());

        System.out.println(countIntervals.count());
        countIntervals.add(39, 44);
        countIntervals.add(13, 49);
        System.out.println(countIntervals.count());
        System.out.println(countIntervals.count());
        countIntervals.add(47, 50);
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals obj = new CountIntervals();
 * obj.add(left,right);
 * int param_2 = obj.count();
 */
