package code.shubham;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FindAllPossibleRecepiesFromGivenSupplies {
    class Solution {
        public List<String> findAllRecipes(String[] R, List<List<String>> I, String[] supplies) {
            ArrayList<String> result = new ArrayList<>();
            HashSet<String> S = new HashSet<>(Arrays.asList(supplies));
            HashMap<String, ArrayList<String>> m = new HashMap<>();
            HashMap<String, Integer> indg = new HashMap<>();
            int i, c;
            for (i = 0; i < R.length; ++i) {
                c = 0;
                for (String ing : I.get(i)) {
                    if (S.contains(ing))
                        continue;
                    m.computeIfAbsent(ing, e -> new ArrayList<>()).add(R[i]);
                    ++c;
                }
                if (c == 0)
                    result.add(R[i]);
                else
                    indg.put(R[i], c);
            }

            ArrayList<String> l;
            for (i = 0; i < result.size(); ++i) {
                l = m.remove(result.get(i));
                if (l != null)
                    for (String next : l)
                        if (indg.merge(next, -1, Integer::sum) == 0)
                            result.add(next);
            }

            return result;
        }
    }
}
