package code.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Anagrams {
    class Solution {
        public ArrayList<ArrayList<Integer>> anagrams (final List<String> A) {
            HashMap<String, ArrayList<Integer>> m = new HashMap();
            IntStream.range(0, A.size()).
                    forEach(i -> {
                        String s = A.get(i);
                        char[] a = s.toCharArray();
                        Arrays.sort(a);
                        s = String.valueOf(a);
                        if (m.containsKey(s)) m.get(s).add(i + 1);
                        else m.put(s, new ArrayList<>(Arrays.asList(i + 1)));
                    });
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            m.values().forEach(l -> result.add(l));
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new Anagrams().
                new Solution().
                anagrams(Arrays.stream(new String[] { "cat", "dog", "god", "tca" }).
                        collect(toList()))
        );
    }
}
