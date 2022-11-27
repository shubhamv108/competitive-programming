package code.shubham.greedy;

import java.util.stream.IntStream;

public class CanAssignToSchedule {
    class Time implements Comparable<Time> {
        int hour;
        int minutes;
        Time(String time) {
            String[] arr = time.split(":");
            this.hour = Integer.valueOf(arr[0]);
            this.minutes = Integer.valueOf(arr[1]);
        }
        @Override
        public int compareTo(Time that) {
            if (this.hour == that.hour) {
                return this.minutes - that.minutes;
            }
            return this.hour - that.hour;
        }
    }

    class TimeUtil {
        Time[] convert(String[] interval) {
            return new Time[] { new Time(interval[0]), new Time(interval[1]) };
        }
    }

    class Interval {
        Time start;
        Time end;
        Interval(Time start, Time end) {
            this.start = start;
            this.end = end;
        }
        Interval(String... interval) {
            this(new Time(interval[0]), new Time(interval[1]));
        }
    }

    class QuickSortArray {
        void swap(String[][] arr, int i, int j) {
            String[] temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        int partition (String[][] arr, int low, int high) {
            String[] pivot = arr[high];
            int i = (low - 1);
            for (int j = low; j <= high - 1; j++) {
                if (new Time(arr[j][0]).compareTo(new Time(pivot[0])) < 0) {
                    i++;
                    if (i < j) {
                        swap(arr, i, j);
                    }
                }
            }
            swap(arr, i + 1, high);
            return (i + 1);
        }
        void sort(String[][] arr, int low, int high) {
            if (low < high) {
                int partitionIndex = partition(arr, low, high);
                sort(arr, low, partitionIndex - 1);
                sort(arr, partitionIndex + 1, high);
            }
        }
        void sort(String[][] arr) {
            sort(arr, 0, arr.length - 1);
        }
    }

    class ArrayList<T> {
        int size = 0;
        int capacity = 8;
        Object[] array = new Object[this.capacity];
        void add(T t) {
            if (size == capacity) {
                this.increaseCapacity();
            }
            this.array[this.size++] = t;
        }
        void increaseCapacity() {
            this.capacity *= 2;
            Object[] arrayCopy = new Object[this.capacity];
            IntStream.range(0, this.size).forEach(i -> arrayCopy[i] = array[i]);
            this.array = arrayCopy;
        }
    }

    Node getScheduledQueue(String[][] scheduled) {
        Node temp = new Node(new Interval("00:00", "00:00"));
        Node linkedList = temp;
        for (String[] schedule : scheduled) {
            temp.next = new Node(new Interval(schedule));
            temp = temp.next;
        }
        temp.next = new Node(new Interval("24:00", "24:00"));
        MergeSortLinkedList mergeSort = new MergeSortLinkedList();
        return mergeSort.sort(linkedList);
    }

    boolean[] schedule(String[][] scheduled, String[][] incomings) {
        boolean[] result = new boolean[incomings.length];
        Node scheduledQueue = getScheduledQueue(scheduled);
        for (int i = 0;i < incomings.length; i++) {
            result[i] = insertIntoScheduledQueue(scheduledQueue, incomings[i]);
        }
        return result;
    }

    boolean insertIntoScheduledQueue(Node node, String[] intervalString) {
        Interval interval = new Interval(intervalString);
        while (node != null) {
            if (interval.start.compareTo(node.interval.end) < 0) {
                break;
            }
            if (node.interval.end.compareTo(interval.start) <= 0) {
                if (node.next != null) {
                    if (interval.start.compareTo(node.next.interval.start) <= 0 && interval.end.compareTo(node.next.interval.start) <= 0) {
                        Node temp = new Node(interval);
                        temp.next = node.next;
                        node.next = temp;
                        return true;
                    }
                }
            }
            node = node.next;
        }
        return false;
    }

    class Node {
        Interval interval;
        Node next;
        Node() {}
        Node(Interval interval) {
            this.interval = interval;
        }
    }

    class MergeSortLinkedList {
        Node sort(Node head) {
            if (head.next == null) {
                return head;
            }
            Node mid = mid(head);
            Node head2 = mid.next;
            mid.next = null;
            head = sort(head);
            head2 = sort(head2);
            return merge(head, head2);
        }
        Node merge(Node head1, Node head2) {
            Node merged = new Node(null);
            Node temp = merged;
            while (head1 != null && head2 != null) {
                if (head1.interval.start.compareTo(head2.interval.start) < 0) {
                    temp.next = head1;
                    head1 = head1.next;
                } else {
                    temp.next = head2;
                    head2 = head2.next;
                }
                temp = temp.next;
            }
            while (head1 != null) {
                temp.next = head1;
                temp = temp.next;
                head1 = head1.next;
            }
            while (head2 != null) {
                temp.next = head2;
                temp = temp.next;
                head2 = head2.next;
            }
            return merged.next;
        }
        Node mid(Node head) {
            Node slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    public static void main(String[] args) {
        String[][] scheduled = new String[][]{new String[]{"23:00","23:30"},new String[]{"10:00","11:00"},
                new String[]{"14:00","16:00"}};
        String[][] incoming = new String[][]{new String[]{"11:00","11:30"},new String[]{"12:00","15:00"},
                new String[]{"11:15","13:43"},new String[]{"17:00","18:40"}};
        boolean[] result = new CanAssignToSchedule().schedule(scheduled, incoming);
        for (boolean e: result) {
            System.out.println(e);
        }
    }

}
