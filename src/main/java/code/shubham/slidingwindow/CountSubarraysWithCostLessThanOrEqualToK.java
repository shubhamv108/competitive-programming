package code.shubham.slidingwindow;

import java.util.LinkedList;

public class CountSubarraysWithCostLessThanOrEqualToK {
    class Solution {
        public long countSubarrays(int[] A, long k) {
            long result = 0;
            LinkedList<Integer> max = new LinkedList<>();
            LinkedList<Integer> min = new LinkedList<>();

            for (int r = 0, l = 0; r < A.length; ++r) {
                while (!min.isEmpty() && A[min.peekLast()] < A[r])
                    min.removeLast();
                while (!max.isEmpty() && A[max.peekLast()] > A[r])
                    max.removeLast();

                min.offer(r);
                max.offer(r);

                while (l <= r) {
                    while (!min.isEmpty() && min.peek() < l)
                        min.poll();
                    while (!max.isEmpty() && max.peek() < l)
                        max.poll();

                    long cost = (long) (A[min.peek()] - A[max.peek()]) * (r - l + 1);
                    if (cost <= k)
                        break;

                    ++l;
                }

                result += (r - l + 1);
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new CountSubarraysWithCostLessThanOrEqualToK().new Solution().countSubarrays(new int[] {1, 3, 2}, 4));
        System.out.println(new CountSubarraysWithCostLessThanOrEqualToK().new Solution().countSubarrays(new int[] {5, 5, 5, 5},0));
        System.out.println(new CountSubarraysWithCostLessThanOrEqualToK().new Solution().countSubarrays(new int[] {1, 2, 3}, 0));
    }
}
