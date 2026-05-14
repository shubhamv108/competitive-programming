package code.shubham.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegersWithMultipleSumOfTwoCubes {
    class Solution {
        public List<Integer> findGoodIntegers(int n) {
            List<Integer> result = new ArrayList<>();

            Map<Integer, List<int[]>> sums = new HashMap<>();
            int limit = (int) Math.cbrt(n) + 1;

            for (int i = 1; i <= limit; ++i) {
                for (int j = i; j <= limit; ++j) { // ensure a <= b
                    int sum = i*i*i + j*j*j;

                    if (sum > n)
                        break;

                    sums.computeIfAbsent(sum, _ -> new ArrayList<>())
                            .add(new int[]{ i, j });
                }
            }

            for (var e : sums.entrySet())
                if (e.getValue().size() >= 2)
                    result.add(e.getKey());

            Collections.sort(result);

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new IntegersWithMultipleSumOfTwoCubes().new Solution().findGoodIntegers(4104));
        System.out.println(new IntegersWithMultipleSumOfTwoCubes().new Solution().findGoodIntegers(578));
    }
}
