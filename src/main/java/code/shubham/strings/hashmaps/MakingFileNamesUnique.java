package code.shubham.strings.hashmaps;

import java.util.HashMap;

public class MakingFileNamesUnique {
    class Solution {
        public String[] getFolderNames(String[] A) {
            int n = A.length;
            HashMap<String, Integer> m = new HashMap<>();
            for (int i = 0; i < n; ++i) {
                String a = A[i];

                int c = m.getOrDefault(a, 0);
                while (m.containsKey(a))
                    a = A[i] + "(" + ++c + ")";

                m.put(a, 0);
                m.put(A[i], c);
                A[i] = a;
            }
            return A;
        }
    }
}
