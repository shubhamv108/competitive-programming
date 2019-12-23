package code.datastructures;

import java.util.Scanner;

public class PriorityQueue {

    private final static int INFINITY          = Integer.MAX_VALUE;
    private final static int NEGATIVE_INFINITY = Integer.MIN_VALUE;
    private int[] a;
    private int capacity;
    private int heapSize;
    private boolean isMinHeap;

    public PriorityQueue(int[] a, int heapSize, boolean isMinHeap) {
        this.a = a;
        this.capacity  = a.length;
        this.heapSize  = heapSize;
        this.isMinHeap = isMinHeap;
    }

    public  boolean isFull()    { return heapSize == capacity; }
    public  int size()          { return heapSize; }
    public  int peek()          { return rootElement(); }
    public  int poll()          { int a = rootElement(); remove(0); return a; }
    private int rootElement()   { return a[0];}
    private int root()          { return 0; }
    private int left(int idx)   { return  2 * idx + 1; }
    private int right(int idx)  { return  2 * idx + 2; }
    private int parent(int idx) { return  (idx - 1)/2; }
    private void buildMinHeap() { for (int i = heapSize / 2 - 1; i >= 0; i--) minHeapify(i); }
    private void buildMaxHeap() { for (int i = heapSize / 2 - 1; i >= 0; i--) maxHeapify(i); }

    private void swap(int idxA, int idxB) {
        int temp = a[idxA];
        a[idxA]  = a[idxB];
        a[idxB]  = temp;
    }

    private void maintainMinHeapOnAncestors(int idx) {
        int child = idx;
        int parent = parent(child);
        while (parent >= 0 && a[parent] > a[child]) {
            swap(child, parent);
            child = parent;
            parent = parent(parent);
        }
    }

    private void insertIntoLastPos(int e) {
        heapSize++;
        a[heapSize - 1] =  e;
    }

    private void insertIntoLastPosOfMinHeap(int e) {
        insertIntoLastPos(e);
        maintainMinHeapOnAncestors(heapSize - 1 );
    }

    private void insertIntoMinHeap(int e) {
        if(heapSize == capacity) return;
        insertIntoLastPosOfMinHeap(e);
    }

    private void maintainMaxHeapOnAncestors(int idx) {
        int child = idx;
        int parent = parent(child);
        while(parent >= 0 && a[parent] > a[child]) {
            swap(child, parent);
            child = parent;
            parent = parent(parent);
        }
    }

    private void insertIntoLastPosOfMaxHeap(int e) {
        insertIntoLastPos(e);
        maintainMaxHeapOnAncestors(heapSize - 1);
    }

    private void insertIntoMaxHeap(int e) {
        if(heapSize == capacity) return;
        insertIntoLastPosOfMaxHeap(e);
    }

    private void insert(int e) {
        if(isMinHeap) insertIntoMinHeap(e);
        else insertIntoMaxHeap(e);
    }

    public void add(int e) {
        insert(e);
    }

    private void insertForcefullyIntoMinHeap(int e) {
        if(heapSize == capacity) {
            if(e > rootElement()) {
                a[0] = e;
                minHeapify(root());
            }
        } else insertIntoLastPosOfMinHeap(e);
    }

    private void insertForcefullyIntoMaxHeap(int e) {
        if(heapSize == capacity) {
            if(e < rootElement()) {
                a[0] = e;
                maxHeapify(root());
            } else insertIntoLastPosOfMaxHeap(e);
        }
    }

    private void insertForcefully(int e) {
        if(isMinHeap) insertForcefullyIntoMinHeap(e);
        else insertForcefullyIntoMaxHeap(e);
    }

    public void addForcefully(int e) { insertForcefully(e); }

    private void printHeap()         { for(int i = 0; i < heapSize; i++) System.out.printf("%d ", a[i]); }

    public void print()              { printHeap(); }

    private void printHeapLevelWiseUtil(int startIdx, int lastIdx) {
        if(startIdx >= heapSize) return;
        for (int i = startIdx; i <= lastIdx; i++) {
            if (i < heapSize) System.out.printf("%d ", a[i]);
            else break;
        }
        System.out.printf("\n");
        printHeapLevelWiseUtil(left(startIdx), right(lastIdx));
    }

    private void printHeapLevelWise() { printHeapLevelWiseUtil(0, 0); }

    public  void printLevelWise()     { printHeapLevelWise(); }

    private void minHeapify(int idx) {
        int smallest = idx;
        int left  = left(idx);
        int right = right(idx);
        if(left  < heapSize && a[left]  < a[smallest]) smallest = left;
        if(right < heapSize && a[right] < a[smallest]) smallest = right;
        if(smallest != idx) {
            swap(smallest, idx);
            minHeapify(smallest);
        }
    }

    private void maxHeapify(int idx) {
        int largest = idx;
        int left  = left(idx);
        int right = right(idx);
        if(left  < heapSize && a[left]  > a[largest]) largest = left;
        if(right < heapSize && a[right] > a[largest]) largest = right;
        if(largest != idx) {
            swap(largest, idx);
            maxHeapify(largest);
        }
    }

    private int deleteElementFromMinHeap(int idx) {
        a[idx] = Integer.MIN_VALUE;
        maintainMinHeapOnAncestors(idx);
        swap(0, heapSize - 1);
        heapSize--;
        minHeapify(0);
        return heapSize;
    }

    private int deleteElementFromMaxHeap(int idx) {
        a[idx] = Integer.MAX_VALUE;
        maintainMaxHeapOnAncestors(idx);
        swap(0, heapSize - 1);
        heapSize--;
        maxHeapify(0);
        return heapSize;
    }

    public int remove(int idx) {
        if(isMinHeap) return deleteElementFromMinHeap(idx);
        else return deleteElementFromMaxHeap(idx);
    }

    // 1 4 2 8 7 9 3 16 10 14
    public static void main(String[] args) {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        PriorityQueue pq;
        int n;
        int[] a;
        while(t-- > 0) {
            n = sc.nextInt();
            a = new int[n];
            pq = new PriorityQueue(a,0,true);
            for(int i = 0; i<n; i++) pq.add(sc.nextInt());
            pq.printLevelWise();
            System.out.printf("\n");
            int d = sc.nextInt();
            while(d-- > 0) {
                int idx = sc.nextInt();
                pq.remove(idx);
                pq.printHeap();
                System.out.printf("\n");
            }
        }
    }

}
