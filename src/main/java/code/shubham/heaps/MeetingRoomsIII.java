package code.shubham.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class MeetingRoomsIII {
    class Solution {
        public int mostBooked(int n, int[][] A) {
            int result = 0;
            int[] c = new int[n];
            Arrays.sort(A, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
            PriorityQueue<int[]> u = new PriorityQueue<>((x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
            PriorityQueue<Integer> un = new PriorityQueue<>();
            IntStream.range(0, n).forEach(un::offer);

            for (int[] a : A) {
                while (!u.isEmpty() && u.peek()[0] <= a[0])
                    un.offer(u.poll()[1]);

                int room = -1;
                if (!un.isEmpty())
                    u.offer(new int[] { a[1] , room = un.poll() });
                else
                    u.offer(new int[] { u.peek()[0] + a[1] - a[0] , room = u.poll()[1] });

                ++c[room];
            }

            int max = c[result];
            for (int i = 1; i < n; ++i) {
                if (c[i] > max) {
                    max = c[i];
                    result = i;
                }
            }

            return result;

        }
    }

    public static void main(String[] args) {
        System.out.println(new MeetingRoomsIII().new Solution().mostBooked(3, new int[][] {
                {1,20},{2,10},{3,5},{4,9},{6,8}
        }));
    }
}
