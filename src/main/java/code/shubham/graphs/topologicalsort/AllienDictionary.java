package code.shubham.graphs.topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
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

    class Solution2 {
        public String alienOrder(String[] A) {
            StringBuilder result = new StringBuilder();
            LinkedHashSet<Integer>[] next = new LinkedHashSet[26];
            Integer[] inD = new Integer[26];


            for (int j = 0; j < A[0].length(); ++j) {
                int b = A[0].charAt(j) - 97;
                if (inD[b] == null)
                    inD[b] = 0;
            }

            boolean flag = false;
            for (int i = 1; i < A.length; ++i) {
                int j = 0;
                for (; j < A[i - 1].length() && j < A[i].length(); ++j) {
                    int a = A[i - 1].charAt(j) - 97;
                    int b = A[i].charAt(j) - 97;

                    if (inD[b] == null)
                        inD[b] = 0;

                    if (inD[a] == null)
                        inD[a] = 0;

                    if (a != b) {
                        if (next[a] == null)
                            next[a] = new LinkedHashSet<>();

                        if (next[a].add(b)) {
                            ++inD[b];
                            flag =  true;
                        }

                        break;
                    }
                }


                if (!flag && A[i-1].length() > A[i].length())
                    return "";

                for (; j < A[i].length(); ++j) {
                    int b = A[i].charAt(j) - 97;
                    if (inD[b] == null)
                        inD[b] = 0;
                }
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < 26; ++i)
                if (inD[i] != null && inD[i] == 0)
                    q.offer(i);

            while (!q.isEmpty()) {
                int p = q.poll();
                result.append((char) (p + 97));

                if (next[p] == null)
                    continue;

                for (int n : next[p])
                    if (--inD[n] == 0)
                        q.offer(n);
            }

            var indegres = Arrays.stream(inD).filter(Objects::nonNull).count();

            return result.length() != indegres ? "" : result.toString();
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
