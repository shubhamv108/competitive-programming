package code.shubham.trees.intervaltree;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarThree {

    TreeMap<Integer, Integer> m = new TreeMap<>();

    public MyCalendarThree() {

    }

    public int book(int start, int end) {
        m.put(start, m.getOrDefault(start, 0) + 1);
        m.put(end, m.getOrDefault(end, 0) - 1);
        int result = 0, count = 0;
        for (Map.Entry<Integer, Integer> e : m.entrySet()) {
            count += e.getValue();
            result = Math.max(result, count);
        }

        return result;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
