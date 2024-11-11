package code.shubham.graphs.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class AllienDictionary {
    public class Solution {
        /**
         * @param A: a list of words
         * @return: a string which is correct order
         */
        public String alienOrder(String[] A) {
            StringBuilder result = new StringBuilder();
            if (A == null || A.length == 0)
                return "";

            HashMap<Character, ArrayList<Character>> neighbors = new HashMap<>();
            Integer[] inDegrees = new Integer[26];

            IntStream.range(0, A.length)
                    .forEach(i -> IntStream.range(0, A[i].length())
                            .forEach(ai -> neighbors.putIfAbsent(A[i].charAt(ai), new ArrayList<>())));
            boolean flag = false;
            for (int i = 1; i < A.length; ++i) {
                for (int j = 0; j < A[i - 1].length() && j < A[i].length(); ++j) {
                    if (A[i - 1].charAt(j) != A[i].charAt(j)) {
                        neighbors.get(A[i-1].charAt(j)).add(A[i].charAt(j));
                        flag = true;
                        break;
                    }
                }
                if (flag == false && A[i-1].length() > A[i].length())
                    return "";

            }

            neighbors
                    .entrySet()
                    .stream()
                    .peek(e -> {
                        int idx = e.getKey() - 'a';
                        inDegrees[idx] = inDegrees[idx] == null ? 0 : inDegrees[idx];
                    })
                    .map(Map.Entry::getValue)
                    .flatMap(ArrayList::stream)
                    .mapToInt(ch -> ch - 'a')
                    .peek(idx -> inDegrees[idx] = inDegrees[idx] == null ? 0 : inDegrees[idx])
                    .forEach(ch -> ++inDegrees[ch]);

            PriorityQueue<Character> q = new PriorityQueue<>();
            for (int i = 0; i < 26; ++i)
                if (inDegrees[i] != null && inDegrees[i] == 0)
                    q.add((char) (i + 'a'));

            while (!q.isEmpty()){
                char p = q.poll();
                result.append(p);

                ArrayList<Character> neigh = neighbors.get(p);
                if (neigh == null)
                    continue;

                for (char neighbor : neigh) {
                    --inDegrees[neighbor - 'a'];
                    if (inDegrees[neighbor - 'a'] == 0)
                        q.add(neighbor);
                }
            }

            return neighbors.keySet().size() > result.length() ? "" : result.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(
//            new AllienDictionary().new Solution().alienOrder(new String[] {"abc","bcd","qwert","ab"})
            new AllienDictionary().new Solution().alienOrder(new String[] {
                    "abc", "ab"
            })
        );
    }
}
