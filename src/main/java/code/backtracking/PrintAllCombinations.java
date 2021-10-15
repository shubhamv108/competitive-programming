package code.backtracking;

import java.util.ArrayList;
import java.util.HashSet;

public class PrintAllCombinations {
    static HashSet<ArrayList<Integer>> result = new HashSet<>();
    public static void main (String[] args) {
        getCombination(1, 3, 3, new ArrayList<>());
        System.out.println(result);
    }

    static boolean getCombination(int min, int max, int total, ArrayList<Integer> l) {
        if (total < 0)
            return false;
        if (total == 0) {
            result.add(new ArrayList<>(l));
            return true;
        }
        for (int i = min; i <= max; i++) {
            l.add(i);
            boolean a = getCombination(min, max, total - i, l);
            l.remove(l.size() - 1);
//            if (a) return a;
        }
        return false;
    }

}
