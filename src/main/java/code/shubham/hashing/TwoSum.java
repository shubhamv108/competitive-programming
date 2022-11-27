package code.shubham.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
    class Solution {
        public ArrayList<Integer> twoSum(final List<Integer> A, int B) {
            ArrayList<Integer> result = new ArrayList<>();
            Map<Integer, Integer> m = new HashMap<>();
            for (int i = 0; i < A.size(); i++) {
                if (m.containsKey(B - A.get(i))) {
                    result.add(m.get(B - A.get(i)) + 1);
                    result.add(i + 1);
                    break;
                } else {
                    m.putIfAbsent(A.get(i), i);
                }
            }
            return result;
        }
    }

    // for elements in array less than target
    class Solution2 {
        public int[] twoSum(int[] num, int target) {
            ArrayList[] rem = new ArrayList[target];
            for (int i = 0 ; i < num.length; i++) {
                if (num[i] <= target) {
                    int r = target == 0 ? target : num[i] % target;
                    ArrayList<Integer> n = rem[r];
                    if (n == null) {
                        rem[r] = n = new ArrayList<>();
                    }
                    n.add(i);
                }
            }
            int i = 0;
            while (i++ < target/2) {
                ArrayList<Integer> x = rem[i];
                ArrayList<Integer> y = rem[target - i];
                if (x != null && y != null) {
                    if (x == y && x.size() > 1) {
                        return new int[] { x.get(0), x.get(1) };
                    } else {
                        return new int[] { x.get(0), y.get(0) };
                    }
                }
            }
            return new int[2];
        }
    }

    public static void main(String[] args) {
        new TwoSum().
                new Solution().
                twoSum(new ArrayList<>(Arrays.asList(2, 2,  7, 11, 15)), 9).
                forEach(System.out::print);

//            Arrays.stream(new TwoSum().new Solution2().twoSum(new int[] {0, 4, 3, 0}, 0))
//                  .forEach(System.out::println);
    }

}
