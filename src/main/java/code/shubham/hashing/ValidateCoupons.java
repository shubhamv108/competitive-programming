package code.shubham.hashing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class ValidateCoupons {

    class Solution {
        public List<String> validateCoupons(String[] code, String[] A, boolean[] isActive) {
            ArrayList<String> result = new ArrayList<>();
            Comparator<Integer> c = Comparator.comparing(x -> A[x]);
            HashMap<String, PriorityQueue<Integer>> m = new HashMap<>();
            for (int i = 0; i < code.length; ++i) {
                if (!isActive[i] || code[i] == null || code[i].isEmpty())
                    continue;

                m.computeIfAbsent(A[i], k -> new PriorityQueue<>(c)).add(i);
            }

            Stream.of("electronics", "grocery", "pharmacy", "restaurant")
                    .map(m::get)
                    .filter(Objects::nonNull)
                    .forEach(q -> {
                        while (!q.isEmpty()) {
                            result.add(code[q.poll()]);
                        }
                    });

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ValidateCoupons().new Solution()
                                   .validateCoupons(
                                           new String[] {"SAVE20","","PHARMA5","SAVE@20"},
                                           new String[] {"restaurant","grocery","pharmacy","restaurant"},
                                           new boolean[] {true,true,true,true}));
    }

}
