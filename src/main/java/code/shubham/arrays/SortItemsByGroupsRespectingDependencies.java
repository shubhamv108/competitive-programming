package code.shubham.arrays;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortItemsByGroupsRespectingDependencies {
    class Solution {
        int ri = 0;
        public int[] sortItems(int n, int m, int[] group, List<List<Integer>> bI) {
            int[] result = new int[n];

            HashMap<Integer, LinkedList<Integer>> gr = new HashMap<>();
            IntStream.range(0, n)
                    .forEach(i -> gr.computeIfAbsent(group[i], e -> new LinkedList<>()).add(i));

            int ri = 0;
            for (int i = 0; i < n; i++)
                add(i, result, gr, group, bI);

            return result;
        }

        void add(int item, int[] result, HashMap<Integer, LinkedList<Integer>> gr, int[] group,List<List<Integer>> bI) {
            int g = group[item];

            if (g == -2)
                return;

            while (!gr.get(g).isEmpty())
                add(gr.get(g).poll(), result, gr, group, bI);

            List<Integer> b = bI.get(item);
            if (b != null)

                for (Integer bi : b)
                    add(bi, result, gr, group, bI);


            if (ri < result.length)
                result[ri++] = item;

            group[item] = -2;
        }
    }

    public static void main(String[] args) {
        new SortItemsByGroupsRespectingDependencies().new Solution()
                .sortItems(
                        8,
                        2,
                        new int[] {-1,-1,1,0,0,1,0,-1},
                        new ArrayList<>(
                                Arrays.asList(
                                Arrays.asList(),
                                Arrays.asList(6),
                                Arrays.asList(5),
                                Arrays.asList(6),
                                Arrays.asList(3, 6),
                                Arrays.asList(),
                                Arrays.asList(),
                                Arrays.asList())));
    }
}
