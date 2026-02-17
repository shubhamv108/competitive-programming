package code.shubham.priorityqueue;

import code.shubham.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class FindServersThatHandledMostNumberOfRequests {
    class Solution {
        public List<Integer> busiestServers(int k, int[] A, int[] L) {
            int[] count = new int[k];
            TreeSet<Integer> f = new TreeSet<>();
            PriorityQueue<Pair<Integer, Integer>> b = new PriorityQueue<>((x, y) -> x.getKey() - y.getKey());

            for (int i = 0; i < k; ++i)
                f.add(i);


            int max = 0;
            for (int i = 0; i < A.length; ++i) {
                int a = A[i];

                while (!b.isEmpty() && b.peek().getKey() <= a)
                    f.add(b.remove().getValue());

                if (f.isEmpty())
                    continue;

                Integer serv = f.ceiling(i % k);
                if (serv == null)
                    serv = f.first();

                f.remove(serv);
                b.add(new Pair<>(a + L[i], serv));
                ++count[serv];
                max = Math.max(max, count[serv]);

            }

            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < k; ++i)
                if (count[i] == max)
                    result.add(i);

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FindServersThatHandledMostNumberOfRequests().new Solution().busiestServers(3, new int[] {1,2,3,4,8,9,10}, new int[] {5,2,10,3,1,2,2}));
    }
}
