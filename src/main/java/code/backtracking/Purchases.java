package code.backtracking;

import java.util.Arrays;
import java.util.List;

public class Purchases {

    int result = 0;
    int purchase(List<int[]> prices, int budget) {
        recurse(0, prices, budget);
        return result;
    }

    void recurse(int index, List<int[]> prices, int budget) {
        if (budget < 0) return;
        if (index == prices.size()) {
            if (budget >= 0)
                result++;
            return;
        }
        for (int p : prices.get(index)) {
            budget -= p;
            recurse(index + 1, prices, budget);
            budget += p;
        }
    }


    public static void main(String[] args) {
        int[] a = {2, 3};
        int[] b = {4};
        int[] c = {2, 3};
        int[] d = {2, 1};
        System.out.println(
                new Purchases().purchase(Arrays.asList(a, b, c, d), 10));
    }
}
