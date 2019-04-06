package code.stacksqueues;

import java.util.*;

public class SlidingWindowMaximum {

    class Solution {
        public ArrayList<Integer> slidingMaximum (final List<Integer> A, int B) {
            ArrayList<Integer> result = new ArrayList<>();
            Deque<Integer> q = new LinkedList<>();
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

    public static void main(String[] args) {
        int[] arr = {12, 1, 8, 90, 57, 89, 56};
        new SlidingWindowMaximum().
                new Solution().
                slidingMaximum(Arrays.
                                stream(arr).
                                collect(ArrayList::new, ArrayList::add, ArrayList::addAll), 3).
                stream().
                forEach(e -> System.out.print(e + " "));

    }

}
