package code.shubham.hashing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidSudoku {

    /**
     * ToDo
     */
    public class Solution {
        public int isValidSudoku (final List<String> A) {
            if (A == null ||
                    A.size() == 0 ||
                    A.size() != A.get(0).length() ||
                    A.stream().map(String::length).filter(l -> l != A.size()).count() > 0)
                return 0;
            Set<Character> s = new HashSet<>();
            for (int i = 0; i < A.size(); i++) {
                s.clear();
                for (int j = 0; j < A.get(i).length(); j++) {
                    char c = A.get(i).charAt(j);
                    if (c == '.') continue;
                    if (s.contains(c)) return 0;
                    s.add(c);
                }
            }
            return 0;
        }
    }

}
