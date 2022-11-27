package code.shubham.heapsmaps;

public class MaxHeap {
    class Node {
        int n;
        Node(int n) {
            this.n = n;
        }
    }
    
    int size = 0;
    Node[] heap;
    MaxHeap(int k) {
        this.heap = new Node[k];
    }
    Node peek() {
        return this.heap[0];
    }
    void offer(Node node) {
        this.heap[size++] = node;
        this.maintainHeapOnAncestor(size-1);
    }
    void maintainHeapOnAncestor(int idx) {
        int parentIdx = (idx-1)/2;
        while (parentIdx > -1 && heap[parentIdx].n < heap[idx].n) {
            swap(parentIdx, idx);
            idx = parentIdx;
            parentIdx = (parentIdx-1)/2;
        }
    }
    void swap(int a, int b) {
        Node t = heap[a];
        heap[b] = heap[a];
        heap[a] = t;
    }
    void replaceRoot(Node n) {
        this.heap[0] = n;
        heapify(0);
    }
    Node poll() {
        Node removed = heap[0];
        heap[0] = new Node(-0x80000000);
        this.swap(0, size-1);
        size--;
        heapify(0);
        return removed;
    }
    void heapify(int idx) {
        int largest = idx;
        int left = (idx*2)+1;
        int right = (idx*2)+2;
        if (left < size && heap[left].n > heap[largest].n) largest = left;
        if (right < size && heap[right].n > heap[largest].n) largest = right;
        if (largest != idx) {
            swap(largest, idx);
            heapify(largest);
        }
    }
    Node[] getHeapArray() {
        return this.heap;
    }
}