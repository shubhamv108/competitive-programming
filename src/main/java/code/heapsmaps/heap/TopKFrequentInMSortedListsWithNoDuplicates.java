package code.heapsmaps.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given m sorted lists with each having an average size of n (no duplicates within the same list), find out the most frequently ocurring k elements and return them sorted by the frequency.
 *
 * Note: Duplicates don't occur in the same list, but they can surely come up across the lists.
 *
 * Eg.     m = 4, k = 2
 *
 * [1, 2, 4, 8]
 * [1, 2, 3]
 * [1, 3, 5, 11, 12, 16]
 * [1, 2]
 *
 * Answer: [1, 2]
 *
 * In the above question, we have to return a list containing the K most frequent elements sorted by the frequency.
 */
public class TopKFrequentInMSortedListsWithNoDuplicates {
    class Solution {
        public List<Integer> topK(List<Integer>[] A, int k) {
            int m = A.length;
            int maxSizeArray = Arrays.stream(A).map(List::size).max((x, y) -> x - y).get();
            PriorityQueue<int[]> q = new PriorityQueue<>(m, (x, y) -> x[0] - y[0]);
            PriorityQueue<int[]> topK = new PriorityQueue<>(k, (x, y) -> x[0] - y[0]);
            for (int a = 0; a < A.length; a++)
                q.offer(new int[] { A[a].get(0), a, 0 });
            int e, count, p[], a, nextIndex;
            while (!q.isEmpty()) {
                e = q.peek()[0];
                count = 0;
                while (!q.isEmpty() && q.peek()[0] == e) {
                    count++;
                    p = q.poll();
                    a = p[1];
                    nextIndex = p[2] + 1;
                    if (A[a].size() == nextIndex)
                        continue;
                    q.offer(new int[]{A[a].get(nextIndex), a, nextIndex});
                }

                if (topK.size() < k) {
                    topK.offer(new int[] { count, e });
                    continue;
                }

                if (!topK.isEmpty() && topK.peek()[0] > count)
                    continue;
                topK.poll();
                topK.offer(new int[] { count, e });
            }

            LinkedList<Integer> result = new LinkedList<>();
            int idx = k - 1;
            while (!topK.isEmpty()) {
                result.offerFirst(topK.poll()[1]);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int m = 4;
        List[] l = new List[m];
        l[0] = new ArrayList(Arrays.asList(1,2,4,8));
        l[1] = new ArrayList<>(Arrays.asList(1,2,3));
        l[2] = new ArrayList<>(Arrays.asList(1,3,5,11,12,16));
        l[3] = new ArrayList<>(Arrays.asList(1,2));
        System.out.println(
            new TopKFrequentInMSortedListsWithNoDuplicates().
                    new Solution().
                    topK(l, 2)
        );
    }
}
