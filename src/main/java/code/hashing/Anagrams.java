package code.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Anagrams {
    class Solution {
        public ArrayList<ArrayList<Integer>> groupAnagrams (final List<String> A) {
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

    class Solution2 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<Integer, List<String>> m = new HashMap<>();
            List<List<String>> result = new ArrayList<>();
            for (String str : strs) {
                int key = getHash(str);
                List<String> t = m.get(key);
                if (t == null) {
                    m.put(key, t = new ArrayList<>());
                    result.add(t);
                }
                t.add(str);
            }
            return result;
        }

        public int getHash(String s) {
            int count = 0;
            for(int i = 0 ; i < s.length() ; i++){
                int chh = (s.charAt(i) - 'a' + 1);
                count += (chh * (17+chh) * (23+chh) * (29+chh) * (41+chh));
            }
            return count;
        }
    }
    public static void main(String[] args) {
        System.out.println(
            new Anagrams().
                new Solution2().
                    groupAnagrams(/*Arrays.stream(*/new String[] { "cat", "dog", "god", "tca" })
                        /*.collect(toList()))*/
        );
    }
}
