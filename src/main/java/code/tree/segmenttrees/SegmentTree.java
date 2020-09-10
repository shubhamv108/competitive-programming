package code.tree.segmenttrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SegmentTree<Data> {

    int capacity;
    int size;
    Node[] nodes;

    SegmentTree(Data[] data) {
        if (Objects.isNull(data)) throw new IllegalArgumentException();
        initialize(data.length);
        Arrays.stream(data).forEach(this::add);
    }

    SegmentTree() {}

    SegmentTree(int capacity) {
        initialize(capacity);
    }

    void initialize(int capacity) {
        this.capacity = capacity;
        nodes = new Node[getTotalNodesCount(capacity)];
    }

    int getTotalNodesCount(int capacity) {
        this.capacity = capacity;
        int height = (int) Math.ceil(Math.log(capacity) / Math.log(2));
        return (2 * (int) Math.pow(2, height)) - 1;
    }

    void add(Data data) {
        update(data, size, 0, capacity - 1, 0);
    }

    void remove(int position) {
        update(null, position, 0, capacity - 1, 0);
    }

    void update(Data data, int position) {
        update(data, position, 0, capacity - 1, 0);
    }

    // Time complexity - O(h)
    Node update(Data data, int position, int start, int end, int cur) {
        if (position < start || position > end) return nodes[cur];
        if (start == end) {
            if (Objects.isNull(nodes[cur])) { nodes[cur] = new Node(data); size++; }
            else nodes[cur].set(data);
            return nodes[cur];
        }
        int mid = start + (end - start) / 2;
        Node left  = update(data, position, start, mid, (2 * cur) + 1);
        Node right = update(data, position, mid + 1, end, (2 * cur) + 2);
        if (Objects.isNull(nodes[cur])) nodes[cur] = new Node();
        nodes[cur].setMin(min(left, right));
        nodes[cur].setSum(sum(left, right));
        return nodes[cur];
    }

    Data getMinInRange(int rangeStart, int rangeEnd) {
        return RMQ(rangeStart, rangeEnd, 0, capacity - 1, 0);
    }

    Data RMQ(int rangeStart, int rangeEnd, int start, int end, int cur) {
        if (rangeStart <= start && end <= rangeEnd) {
            return (Data) nodes[cur].min;
        }
        if (rangeStart > end || rangeEnd < start) {
            return (Data) (Integer) Integer.MAX_VALUE;
        }
        int mid = start + (end - start) / 2;
        return min(RMQ(rangeStart, rangeEnd, start, mid, (2 * cur) + 1),
                   RMQ(rangeStart, rangeEnd, mid + 1, end, (2 * cur) + 2));
    }

    Data getSumInRange(int rangeStart, int rangeEnd) {
        return RangeSumQuery(rangeStart, rangeEnd, 0, capacity - 1, 0);
    }

    Data RangeSumQuery(int rangeStart, int rangeEnd, int start, int end, int cur) {
        if (rangeStart <= start && end <= rangeEnd) {
            return (Data) nodes[cur].sum;
        }
        if (rangeStart > end || rangeEnd < start) {
            return null;
        }
        int mid = start + (end - start) / 2;
        return sum(RangeSumQuery(rangeStart, rangeEnd, start, mid, (2 * cur) + 1),
                   RangeSumQuery(rangeStart, rangeEnd, mid + 1, end, (2 * cur) + 2));
    }

    Collection<Data> getAllDataElements() {
        Collection<Data> dataElements = new ArrayList<>();
        populateAllDataElements(0, capacity - 1, 0, dataElements);
        return dataElements;
    }

    void populateAllDataElements(int start, int end, int cur, Collection<Data> dataElements) {
        if (start == end) { dataElements.add((Data) nodes[cur].min); return; }
        int mid = start + (end - start) / 2;
        populateAllDataElements(start, mid, (2 * cur) + 1, dataElements);
        populateAllDataElements(mid + 1, end, (2 * cur) + 2, dataElements);
    }

    void construct(Data[] array) {
        if (Objects.isNull(array)) throw new IllegalArgumentException();
        initialize(array.length);
        construct(array, 0, capacity - 1, 0);
    }

    Node construct(Data[] array, int start, int end, int cur) {
        if (start == end) {
            nodes[cur] = new Node(array[start]);
            return nodes[cur];
        }
        int mid = start + (end - start) / 2;
        Node left  = construct(array, start, mid, (2 * cur) + 1);
        Node right = construct(array, mid + 1, end, (2 * cur) + 2);
        nodes[cur] = new Node();
        nodes[cur].setMin(min(left, right));
        nodes[cur].setSum(sum(left, right));
        return nodes[cur];
    }

    static class Node<Data> {
        Data min;
        Data sum;

        Node() {}

        Node(Data data) {
            this.min = data;
            this.sum = data;
        }

        void set(Data data) {
            setMin(data);
            setSum(data);
        }

        void setMin(Data data) {
            if (Objects.isNull(data)) this.min = (Data) (Integer) Integer.MAX_VALUE;
            else this.min = data;
        }

        void setSum(Data data) {
            if (Objects.isNull(data)) this.sum = (Data) (Integer) 0;
            else this.sum = data;
        }

        @Override
        public String toString() {
            return String.format("Sum=%s;Min=%s", sum, min);
        }
    }

    static <Data> Data min(Node x, Node y) {
        if (Objects.isNull(x)) return (Data) y.min;
        if (Objects.isNull(y)) return (Data) x.min;
        return min((Data) x.min, (Data) y.min);
    }

    static <Data> Data min(Data x, Data y) {
        if (Objects.isNull(x)) return y;
        if (Objects.isNull(y)) return x;
        if (x instanceof Integer || y instanceof Integer) {
            return (Data) (Integer) Math.min((Integer) x, (Integer) y);
        }
        return null;
    }

    static <Data> Data sum(Node x, Node y) {
        if (Objects.isNull(x)) return (Data) y.sum;
        if (Objects.isNull(y)) return (Data) x.sum;
        return sum((Data) x.sum, (Data) y.sum);
    }

    static <Data> Data sum(Data x, Data y) {
        if (Objects.isNull(x)) return y;
        if (Objects.isNull(y)) return x;
        if (x instanceof Integer || y instanceof Integer) {
            return (Data) (Integer) ((Integer) x + (Integer) y);
        }
        return null;
    }

    @Override
    public String toString() {
        return Arrays.stream(nodes).collect(Collectors.toList()).toString();
    }

    public static void main(String[] args) {
        Integer[] array = Stream.of(1, 3, 5, -7, 9, 11).toArray(Integer[]::new);
        SegmentTree segmentTree = new SegmentTree(array);
        segmentTree.update(-10, 2);
        System.out.println(segmentTree);
        System.out.println(segmentTree.getMinInRange(4, 5));
        System.out.println(segmentTree.getSumInRange(2, 5));
        System.out.println(segmentTree.getMinInRange(0, 5));
        System.out.println(segmentTree.getSumInRange(0, 5));
        System.out.println(segmentTree.getMinInRange(3, 5));
        System.out.println(segmentTree.getSumInRange(3, 5));
        segmentTree.remove(2);
        System.out.println(segmentTree);
        System.out.println(segmentTree.getSumInRange(2, 2));
        SegmentTree segmentTree1 = new SegmentTree();
        segmentTree1.construct(array);
        System.out.println(segmentTree1);
        System.out.println(segmentTree.getAllDataElements());
    }

}
