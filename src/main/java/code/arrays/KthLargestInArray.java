package code.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class KthLargestInArray {

    class Solution {

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

        class MinHeap {

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
                if (left < size && heap[left] < heap[smallest]) smallest = left;
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

    // QuickSelect (Recursive)
    class Solution2 {
        public int findKthLargest(int[] nums, int k) {
            int len = nums.length;
            return quickSelect(nums, 0, len - 1, k);
        }

        int quickSelect(int[] nums, int l, int r, int k) {
            int pivot = l;
            for (int i = l; i < r; i++) {
                if (nums[i] <= nums[r]) {
                    swap(nums, pivot, i);
                    pivot++;
                }
            }
            swap(nums, pivot, r);

            int count = r - pivot + 1;
            if (count == k) return nums[pivot];
            if (count > k) return quickSelect(nums, pivot + 1, r, k);
            return quickSelect(nums, l, pivot - 1, k - count);
        }

        void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

    // QuickSelect (Iterative)
    class Solution3 {
        public int findKthLargest(int[] A, int k) {
            int len = A.length, l = 0, r = len - 1;
            k = len - k;

            while (l <= r) {
                int pivot = l;
                for (int i = l + 1; i <= r; i++)
                    if (A[i] < A[l]) {
                        pivot++;
                        swap(A, i, pivot);
                    }

                swap(A, l, pivot);

                if (pivot == k)
                    return A[pivot];
                if (k < pivot)
                    r  = pivot - 1;
                else
                    l = pivot + 1;
            }
            return -1;
        }
        void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

    // QuickSelect (Recursive)
    class Solution4 {
        public int findKthLargest(int[] A, int k) {
            return quickSelect(A, 0, A.length - 1, A.length - k);
        }

        int quickSelect(int[] A, int start, int end, int k) {
            int l = start, r = end, pivot = A[l + (r - l) / 2];

            while (l <= r) {
                while (l <= r && A[l] < pivot)
                    l++;
                while (l <= r && pivot < A[r])
                    r--;

                if (l <= r)
                    swap(A, l++, r--);
            }

            if (k >= l)
                return quickSelect(A, l, end, k);
            else if (k <= r)
                return quickSelect(A, start, r, k);

            return pivot;
        }

        void swap(int[] A, int x, int y) {
            int t = A[x];
            A[x] = A[y];
            A[y] = t;
        }
    }

    // TreeMap
    class Solution5 {
        public int findKthLargest(int[] A, int k) {
            TreeMap<Integer, Integer> freq = new TreeMap<>(Collections.reverseOrder());
            for (int i = 0; i < A.length; i++)
                freq.put(A[i], freq.getOrDefault(A[i], 0) + 1);

            int count = 0;
            for(Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                count += entry.getValue();
                if (count >= k)
                    return entry.getKey();
            }
            return -1;
        }
    }

    // TreeMap
    class Solution51 {
        public int findKthLargest(int[] A, int k) {
            TreeMap<Integer, Integer> freq = new TreeMap<>();
            for (int a : A)
                freq.put(a, freq.getOrDefault(a, 0) + 1);

            int min = freq.firstKey(), max = freq.lastKey(), count = 0;
            for(int i = max; i >= min; i = freq.lowerKey(i)) {
                count += freq.getOrDefault(i, 0);
                if (count >= k)
                    return i;
            }
            return -1;
        }
    }

    // Counting - Sort
    class Solution6 {
        public int findKthLargest(int[] A, int k) {
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int i = 0; i < A.length; i++) {
                if (max < A[i])
                    max = A[i];
                if (min > A[i])
                    min = A[i];
            }

            int[] freq = new int[max - min + 1];
            for (int a : A)
                freq[a - min]++;

            int count = 0;
            for(int i = max - min; i >= 0; i--) {
                count += freq[i];
                if (count >= k)
                    return i + min;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] input = new int[] {};
        System.out.println(
                new KthLargestInArray().new Solution6().findKthLargest(
                        new int[] { 1 }, 1
                )
        );
    }

}
