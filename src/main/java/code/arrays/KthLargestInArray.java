package code.arrays;

import java.util.Arrays;
import java.util.Comparator;

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
        public int findKthLargest(int[] nums, int k) {
            int len = nums.length;
            k = len - k;
            int l = 0, r = len - 1;
            while (l <= r) {
                int pivot = l;
                for (int i = l + 1; i <= r; i++) {
                    if (nums[i] < nums[l]) {
                        pivot++;
                        swap(nums, i, pivot);
                    }
                }
                swap(nums, l, pivot);

                if (pivot == k) return nums[pivot];
                if (k < pivot) r  = pivot - 1;
                else l = pivot + 1;
            }
            return -1;
        }
        void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

    class Solution4 {
        public int findKthLargest(int[] nums, int k) {
            int len = nums.length;
            k = len - k;
            return quickSelect(nums, 0, len - 1, k);
        }

        public int quickSelect(int[] nums, int start, int end, int k) {
            int l = start;
            int r = end;
            int pivot = nums[l + (r - l) /2];
            while(l <= r) {
                while(l <= r && nums[l] < pivot) l++;
                while(l <= r && nums[r] > pivot) r--;

                if(l <= r) {
                    swap(nums, l, r);
                    l++;
                    r--;
                }
            }

            if(k >= l) {
                return quickSelect(nums, l, end, k);
            } else if(k <= r) {
                return quickSelect(nums, start, r, k);
            } else {
                return pivot;
            }
        }

        void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

    public static void main(String[] args) {
        int[] input = new int[] {};
        System.out.println(
                new KthLargestInArray().new Solution3().findKthLargest(
                        new int[] { 3,2,1,5,6,4 }, 2
                )
        );
    }

}
