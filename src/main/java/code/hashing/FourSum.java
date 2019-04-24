package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class FourSum {
    class Solution {
        public ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> A, int B) {
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            HashSet<ArrayList<Integer>> s = new HashSet<>();
            A.sort(null);
            int sum;
            for (int i = 0; i < A.size() ; i++) {
               for (int j = i + 1; j < A.size(); j++) {
                   int k = j + 1;
                   int l = A.size() - 1;
                   while (k < l) {
                       sum = A.get(i) + A.get(j) + A.get(k) + A.get(l);
                       if (sum == B) {
                           ArrayList<Integer> t = new ArrayList<>();
                           t.add(A.get(i));
                           t.add(A.get(j));
                           t.add(A.get(k));
                           t.add(A.get(l));
                           if (!s.contains(t)) {
                               s.add(t);
                               result.add(t);
                           }
                           k++;
                           l--;
                       } else if (sum < B) {
                           k++;
                       } else {
                           l--;
                       }
                   }
               }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        new FourSum().
                new Solution().
                fourSum (new ArrayList<>(Arrays.asList(1, 0, -1, 0, -2, 2)), 0).
                forEach(l -> {
                    l.forEach(e -> System.out.print(e + " "));
                    System.out.println();
                });
    }
}
