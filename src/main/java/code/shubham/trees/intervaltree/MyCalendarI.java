package code.shubham.trees.intervaltree;

import java.util.TreeMap;

public class MyCalendarI {

    class Solution {
        class Node {
            int start, end;
            Node left, right;
            Node() {}
            Node(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        class BooleanResult {
            boolean result;
        }

        Node root = null;

        public Solution() {

        }

        public boolean book(int start, int end) {
            BooleanResult result = new BooleanResult();
            Node node = book(root, start, end, result);
            if (root == null)
                root = node;
            return result.result;
        }

        Node book(Node node, int start, int end, BooleanResult result) {
            if (node == null) {
                result.result = true;
                return new Node(start, end);
            }

            if (end <= node.start)
                node.left = book(node.left, start, end, result);

            else if (node.end <= start)
                node.right = book(node.right, start, end, result);

            return node;
        }
    }

    class Solution2 {

        TreeMap<Integer, Integer> calendar = new TreeMap<>();

        public Solution2() {

        }

        public boolean book(int start, int end) {
            Integer prev = calendar.floorKey(start),
                    next = calendar.ceilingKey(start);
            if ((prev == null || calendar.get(prev) <= start) &&
                    (next == null || end <= next)) {
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        MyCalendarI.Solution2 obj = new MyCalendarI().new Solution2();
        int[][] intervals = {{47,50},{33,41},{39,45},{33,42},{25,32},{26,35},{19,25},{3,8},{8,13},{18,27}};
        for (int[] interval : intervals)
            System.out.println(obj.book(interval[0], interval[1]));

    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */