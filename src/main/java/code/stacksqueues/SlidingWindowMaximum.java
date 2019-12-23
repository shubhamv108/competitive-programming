package code.stacksqueues;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class SlidingWindowMaximum {

    class Solution {
        public ArrayList<Integer> slidingMaximum (final List<Integer> A, int B) {
            ArrayList<Integer> result = new ArrayList<>();
            java.util.Deque<Integer> q = new LinkedList<>();
            int i = 0;
            for (;i<B;i++) {
                while (!q.isEmpty() && A.get(i) >= A.get(q.peekLast())) q.removeLast();
                q.offerLast(i);
            }
            for (; i < A.size(); i++) {
                result.add(A.get(q.peekFirst()));
                while (!q.isEmpty() && q.peekFirst() <= i - B) q.removeFirst();
                while (!q.isEmpty() && A.get(i) >= A.get(q.peekLast())) q.removeLast();
                q.offerLast(i);
            }
            result.add(A.get(q.peekFirst()));
            return result;
        }
    }

    class Pair {
        int pos;
        int val;
        Pair(int pos, int val) {
            this.pos = pos;
            this.val = val;
        }
        Pair(int pos) {
            this.pos = pos;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pair) {
                return ((Pair) obj).pos == this.pos;
            }
            return false;
        }
    }

    class Solution2 {

        public ArrayList<Integer> slidingMaximum (final List<Integer> A, int B) {
            ArrayList<Integer> result = new ArrayList<>();
            Queue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.val - a.val);
            IntStream.range(0, B).forEach(i -> maxHeap.offer(new Pair(i, A.get(i))));
            IntStream.range(B, A.size()).forEach(i -> {
                result.add(maxHeap.peek().val);
//                maxHeap.remove(new Pair(i-B));
                maxHeap.offer(new Pair(i, A.get(i)));
                while (maxHeap.peek().pos <= i-B) {
                    maxHeap.poll();
                }
            });
            result.add(maxHeap.peek().val);
            return result;
        }

    }

    class Solution3 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 0 || nums.length < k) return new int[0];
            int[] result = new int[nums.length - k + 1];
            Queue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.val - a.val);
            IntStream.range(0, k).forEach(i -> maxHeap.offer(new Pair(i, nums[i])));
            int i = k;
            for(; i < nums.length; i++) {
                result[i - k] = maxHeap.peek().val;
                maxHeap.offer(new Pair(i, nums[i]));
                while (maxHeap.peek().pos <= i - k)
                    maxHeap.poll();
            }
            result[i - k] = maxHeap.peek().val;
            return result;
        }
    }

    class Solution4 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums.length == 0 || k==0){
                return new int[0];
            }
            int len = nums.length - k + 1;
            int[] res = new int[len];
            int index = 0;
            int max = Integer.MIN_VALUE;
            int maxIndex = -1;
            while(index<len){
                if(maxIndex<index){
                    max = nums[index];
                    for(int i=index+1;i<index+k;i++){
                        if(max<nums[i]) {
                            max = nums[i];
                            maxIndex = i;
                        }
                    }
                }
                else{
                    if(max<=nums[index+k-1]){
                        max = nums[index+k-1];
                        maxIndex = index+k-1;
                    }
                }
                res[index++] = max;
            }
            return res;
        }
    }

    class Solution6 {
        int[] maxSlidingWindow(int[] A, int k) {
            if (A.length == 0 || A.length < k) return new int[0];
            int[] result = new int[A.length - k + 1];
            code.datastructures.Deque<Integer> q = new code.datastructures.Deque<>();
            int i = 0;
            for (; i < k; i++) {
                while (!q.isEmpty() && A[i] >= A[q.peekLast()]) q.removeLast();
                q.addLast(i);
            }
            for (; i < A.length; i++) {
                result[i-k] = A[q.peekFirst()];
                while (!q.isEmpty() && q.peekFirst() <= i - k) q.removeFirst();
                while (!q.isEmpty() && A[i] >= A[q.peekLast()]) q.removeLast();
                q.addLast(i);
            }
            result[i - k] = A[q.peekFirst()];
            return result;

        }
    }


    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 20, 5, -6, 7, 8, 9, 0 };
//        new SlidingWindowMaximum().
//                new Solution().
//                slidingMaximum(Arrays.
//                                stream(arr).
//                                collect(ArrayList::new, ArrayList::add, ArrayList::addAll), 3).
//                stream().
//                forEach(e -> System.out.print(e + " "));
        Arrays.stream(
        new SlidingWindowMaximum().
                new Solution6().
                maxSlidingWindow(arr, 3)).
                forEach(e -> System.out.print(e + "  "));

    }

}
