package code.shubham.graphs.bfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class FindAllPeopleWithSecret {
    class Solution {
        public List<Integer> findAllPeople(int n, int[][] A, int firstPerson) {
            ArrayList<Integer> result = new ArrayList<>();
            ArrayList<ArrayList<int[]>> g = new ArrayList<>();
            IntStream.range(0, n).forEach(i -> g.add(new ArrayList<>()));
            g.get(0).add(new int[] {firstPerson, 0});
            g.get(firstPerson).add(new int[] {0, 0});
            for (int[] a : A) {
                g.get(a[0]).add(new int[] {a[1], a[2]});
                g.get(a[1]).add(new int[] {a[0], a[2]});
            }

            Integer[] v = new Integer[n];
            v[0] = 0;
            PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
            q.offer(new int[] {0, 0});
            while (!q.isEmpty()) {
                int[] p = q.poll();
                if (v[p[0]] != null && v[p[0]] < p[1])
                    continue;

                for (int[] next : g.get(p[0])) {
                    if (p[1] <= next[1] && (v[next[0]] == null ||  next[1] < v[next[0]])) {
                        v[next[0]] = next[1];
                        q.offer(new int[] { next[0], next[1] });
                    }
                }
            }

            for (int i = 0; i < n; ++i)
                if (v[i] != null)
                    result.add(i);

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new FindAllPeopleWithSecret().new Solution()
                        .findAllPeople(
                                6,
                                new int[][]  {
                                    { 1,2,5 },
                                    { 2,3,8 },
                                    { 1,5,10 }
                                },
                                1));
    }
}
