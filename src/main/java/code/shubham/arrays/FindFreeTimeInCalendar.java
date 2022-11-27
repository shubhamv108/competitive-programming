package code.shubham.arrays;

import java.util.ArrayList;
import java.util.List;


public class FindFreeTimeInCalendar {

    class Interval {
        String start;
        String end;
        Interval(String s, String e) {
            this.start = s;
            this.end = e;
        }

        @Override
        public String toString() {
            return start + "->" + end;
        }
    }

    int isGreater(String x, String y) {
        String[] xx = x.split(":");
        String[] yy = y.split(":");
        Integer xHour = Integer.parseInt(xx[0]);
        Integer xMin = Integer.parseInt(xx[1]);
        Integer yHour = Integer.parseInt(yy[0]);
        Integer yMin = Integer.parseInt(yy[1]);
        if (xHour > yHour) {
            return 1;
        } else if (xHour == yHour) {
            if (xMin > yMin) return 1;
            if (xMin == yMin) return 0;
        }
        return -1;
    };

    List<Interval> getMerged(Interval[] s1, Interval[] s2) {
        if (null == s1 || s1.length == 0 ||null == s2 || s2.length == 0) return null;
        int i = 0;
        int j = 0;
        if (isGreater(s1[0].start, s2[0].start) == 1) {
            Interval[] temp = s1;
            s1 = s2;
            s2 = temp;
        }
        List<Interval> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(s1[0]);
        i++;
        merge(mergedIntervals, s2[0]);
        j++;
        while (i < s1.length || j < s2.length) {
            if (i < s1.length) {
                if (j >= s2.length || isGreater(s1[i].start, s2[j].start) != 1) {
                    merge(mergedIntervals, s1[i++]);
                }
            }
            if (j < s2.length) {
                if (i >= s1.length || isGreater(s2[j].start, s1[i].start) != 1) {
                    merge(mergedIntervals, s2[j++]);
                }
            }
        }
        return mergedIntervals;
    }

    List<Interval> getFreeTime(List<Interval> intervals, String lengthOfFreeTime) {
        List<Interval> result = new ArrayList<>();
        if (null == intervals || intervals.size() == 0) return result;
        if (lengthOfFreeTime.equals("0")) return null;
        int length = Integer.parseInt(lengthOfFreeTime);
        Interval t = intervals.get(0);
        int i = 1;
        for (; i < intervals.size(); i++) {
            if (diff(t.end, intervals.get(i).start) >= length) {
                result.add(new Interval(t.end, intervals.get(i).start));
            }
        }
        return result;
    }

//    Interval mergeBound(List<Interval> m, Interval y) {
//        Interval x = m.get(m.size() - 1);
//        if (isGreater(x.end))
//        if (isGreater(y.start, x.start) == 1) {
//
//        }
//    }

    void merge(List<Interval> m, Interval y) {
        Interval x = m.get(m.size() - 1);
        if (isGreater(x.end, y.start) != -1) {
            x.end = y.end;
        } else {
            m.add(y);
        }
    }

    private int diff(String end, String start) {
        String[] xx = end.split(":");
        String[] yy = start.split(":");
        Integer xHour = Integer.parseInt(xx[0]);
        Integer xMin = Integer.parseInt(xx[1]);
        Integer yHour = Integer.parseInt(yy[0]);
        Integer yMin = Integer.parseInt(yy[1]);
        return Math.abs(((xHour * 60) + xMin) - ((yHour * 60) + yMin));
    }

    public static void main(String[] args) {
        FindFreeTimeInCalendar f = new FindFreeTimeInCalendar();
        Interval[] s1 = new Interval[] {
                f.new Interval("08:30", "10:00"),
                f.new Interval("10:30", "11:30")
        };
        Interval[] s2 = new Interval[] {
                f.new Interval("09:30", "09:45"),
                f.new Interval("10:30", "10:32")
        };
        f.getFreeTime(f.getMerged(s1, s2), "30").forEach(System.out::println);

    }

}
