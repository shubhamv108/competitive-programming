package code.shubham.strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordBreakII {
    class Solution {
        public List<String> wordBreak(String s, List<String> wordDict) {
            ArrayList<String> result = new ArrayList<>();
            Set<String> W = wordDict.stream().collect(Collectors.toSet());
//            recurse(s, W, new ArrayList<>(), result);
            return result;
        }

        void recurse(String s, HashSet<String> W, ArrayList<String> cur, ArrayList<String> result) {
            if (s.length() == 0) {
                result.add(cur.stream().collect(Collectors.joining(" ")).toString());
                return;
            }

            for (String w : W) {
                if (s.startsWith(w)) {
                    W.remove(w);
                    cur.add(w);
                    recurse(s.substring(w.length()), W, cur, result);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }
}
