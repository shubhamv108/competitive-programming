package code.shubham.strings;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    class Solution {
        public List<String> fullJustify(String[] A, int max) {
            ArrayList<String> result = new ArrayList<>();
            for (int i = 0; i < A.length;) {
                ArrayList<String> line = getWords(A, i, max);
                i += line.size();
                result.add(justify(line, max, A, i));
            }
            return result;
        }

        ArrayList<String> getWords(String[] A, int ai, int max) {
            ArrayList<String> w = new ArrayList<>();
            int length = 0;
            for (; ai < A.length && length + A[ai].length() <= max; ++ai) {
                w.add(A[ai]);
                length += A[ai].length() + 1;
            }
            return w;
        }

        String justify(ArrayList<String> line, int max, String[] w, int wi) {
            int base = line.stream()
                    .mapToInt(String::length)
                    .sum()
                    + line.size() - 1;

            int spaces = max - base;

            if (line.size() == 1 || wi == w.length)
                return String.join(" ", line) + " ".repeat(spaces);

            int eachWord = spaces / (line.size() - 1);
            int extra = spaces % (line.size() - 1);

            for (int i = 0; i < line.size() - 1; ++i)
                line.set(i, line.get(i) + " ".repeat(eachWord));

            for (int i = 0; i < extra; ++i)
                line.set(i, line.get(i) + " ");

            return String.join(" ", line);
        }
    }
}
