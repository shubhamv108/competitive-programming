package code.caching;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * ToDo
 * Handle multithreading in cache and ttl update in minHeap
 * @param <K>
 * @param <V>
 */
public class LRUWithTTL<K, V> {

    Node head = new Node();
    Node tail = new Node();
    Map<K, Node> nodeMap;
    int capacity;

    MinHeap minHeap;

    public LRUWithTTL(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
        minHeap = new MinHeap(capacity);
    }

    public V get(K key) {
        V result = null;
        Node node = nodeMap.get(key);
        if (node != null) {
            if (System.currentTimeMillis() > node.ttl) {
                evict(node);
            } else {
                remove(node);
                add(node);
                result = node.value;
            }
        }
        return result;
    }

    public synchronized void put(K key, V value, Long ttlInMilliseconds) {
        Node node = nodeMap.get(key);
        if (node != null) {
            node.value = value;
            remove(node);
            add(node);
        } else {
            if (nodeMap.size() == capacity) {
                nodeMap.remove(tail.prev.key);
                remove(tail.prev);
            }
            Node newNode = new Node(key, value, ttlInMilliseconds);
            add(newNode);
            nodeMap.put(key, newNode);
            if (ttlInMilliseconds != null) {
                minHeap.offer(newNode);
                notifyAll();
            }
        }
    }

    private void add(Node node) {
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
    }

    private void remove(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }

    private void evict() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (minHeap.size == 0) wait();
                if (minHeap.peek().ttl > System.currentTimeMillis()) {
                    Thread.sleep(minHeap.peek().ttl - System.currentTimeMillis());
                }
                Node node = minHeap.poll();
                evict(node);
            }
        }
    }

    private void evict(Node node) {
        remove(node);
        nodeMap.remove(node.key);
        System.out.println("Evicted: " + node);
    }

    class Node {
        K key;
        V value;
        Long ttl;
        Node prev;
        Node next;
        Node() {}
        Node(K key, V value, Long ttl) {
            this.key = key;
            this.value = value;
            if (ttl != null) this.ttl = System.currentTimeMillis() + ttl;
        }

        @Override
        public String toString() {
            return "[" + key + "," + value + "]";
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            builder.append(temp.toString()).append("->");
            temp = temp.next;
        }
        builder.append("\n").append(nodeMap);
        return builder.toString();
    }

    class MinHeap {
        LRUWithTTL.Node[] heap;
        int size = 0;
        //        Comparator<Integer> comparator;
        MinHeap(int k/*, Comparator<Integer> comparator*/) {
            heap = new LRUWithTTL.Node[k];
            // this.comparator = comparator;
        }

        LRUWithTTL.Node peek() {
            return heap[0];
        }

        void offer(LRUWithTTL.Node node) {
            heap[size++] = node;
            maintainHeapOnAncestor(size - 1);
        }

        void maintainHeapOnAncestor(int idx) {
            int parentIdx = (idx - 1) / 2;
            if (parentIdx > -1) {
                if (heap[idx].ttl - heap[parentIdx].ttl < 0) {
                    // if (comparator.compare(heap[idx], heap[parentIdx]) < 0) {
                    LRUWithTTL.Node t = heap[idx];
                    heap[idx] = heap[parentIdx];
                    heap[parentIdx] = t;
                    maintainHeapOnAncestor(parentIdx);
                }
            }
        }

        void replacePeek(LRUWithTTL.Node object) {
            heap[0] = object;
            heapify(0);
        }

        Node poll() {
            Node removed = heap[0];
            heap[0] = newMaxTTLNode();
            swap(0, size - 1);
            size--;
            heapify(0);
            return removed;
        }

        void heapify(int idx) {
            int smallest = idx;
            int left = (2 * idx) + 1;
            int right = (2 * idx) + 2;
            if (left < size  && heap[left].ttl  < heap[smallest].ttl) smallest = left;
            if (right < size && heap[right].ttl < heap[smallest].ttl) smallest = right;
            if (smallest != idx) {
                LRUWithTTL.Node t = heap[smallest];
                heap[smallest] = heap[idx];
                heap[idx] = t;
                heapify(smallest);
            }
        }

        private void swap(int idxA, int idxB) {
            LRUWithTTL.Node temp = heap[idxA];
            heap[idxA]  = heap[idxB];
            heap[idxB]  = temp;
        }

        LRUWithTTL.Node newMaxTTLNode() {
            return new LRUWithTTL.Node(null, null, Long.MAX_VALUE);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        LRUWithTTL<Integer, Integer> lru = new LRUWithTTL<>(10);

        Thread evictor = new Thread(() -> {
            try {
                lru.evict();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        evictor.start();

        Thread.sleep(10000);

        lru.put(1, 1, 1000l);
        lru.put(2, 2, 500l);
        System.out.println(lru.get(1));
        lru.put(3, 3, 1000l);
        System.out.println(lru.get(2));
        lru.put(4, 4, 1000l);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
        System.out.println(lru);
    }

}
