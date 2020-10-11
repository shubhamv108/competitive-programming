package code.arrays;

import java.util.Comparator;

public class KthLargestInArray {

    public int findKthLargest(int[] nums, int k) {
        MinHeap heap = new MinHeap(k/*, (x, y) -> x - y*/);
        int i = 0;
        for (; i < k; i++) {
            heap.offer(nums[i]);
        }

        for (; i < nums.length; i++) {
            if (heap.peek() < nums[i]) {
                heap.replacePeek(nums[i]);
            }
        }

        return heap.peek();

    }

    static class MinHeap {

        int[] heap;
        int size = 0;
//        Comparator<Integer> comparator;
        MinHeap(int k/*, Comparator<Integer> comparator*/) {
            heap = new int[k];
            // this.comparator = comparator;
        }

        int peek() {
            return heap[0];
        }

        void offer(int object) {
            heap[size++] = object;
            maintainHeapOnAncestor(size - 1);
        }

        void maintainHeapOnAncestor(int idx) {
            int parentIdx = (idx - 1) / 2;
            if (parentIdx > -1) {
                if (heap[idx] - heap[parentIdx] < 0) {
                    // if (comparator.compare(heap[idx], heap[parentIdx]) < 0) {
                    int t = heap[idx];
                    heap[idx] = heap[parentIdx];
                    heap[parentIdx] = t;
                    maintainHeapOnAncestor(parentIdx);
                }
            }
        }

        void replacePeek(int object) {
            heap[0] = object;
            heapify(0);
        }

        void heapify(int idx) {
            int smallest = idx;
            int left = (2 * idx) + 1;
            int right = (2 * idx) + 2;
            if (left < size  && heap[left]  < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;
            if (smallest != idx) {
                int t = heap[smallest];
                heap[smallest] = heap[idx];
                heap[idx] = t;
                heapify(smallest);
            }
        }

    }

}
