package code.shubham.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RatPathsFromTopLeftToBotomRight {
    public static int numberOfPaths(List<List<Integer>> a) {
        if (a == null || a.size() == 0 || a.get(0).get(0) == 0) return 0;
        if (a.get(a.size() - 1).get(a.get(0).size() - 1) == 0)  return 0;

        for (int i = 1; i< a.get(0).size(); i++)
            if (a.get(0).get(i) == 1 && a.get(0).get(i-1) == 1)
                a.get(0).set(i, 1);
            else
                a.get(0).set(i, 0);


        for (int i = 1; i < a.size(); i++)
            if (a.get(i).get(0) == 1 && a.get(i-1).get(0) == 1)
                a.get(i).set(0, 1);
            else
                a.get(i).set(0, 0);


        for (int i = 1; i < a.size(); i++)
            for (int j = 1; j < a.get(0).size(); j++) {
                if (a.get(i).get(j) == 0) continue;
                a.get(i).set(j, a.get(i).get(j - 1) + a.get(i - 1).get(j));
            }

        return a.get(a.size()-1).get(a.get(0).size() -1);

    }

    public static void main(String[] args) {
        List<List<Integer>> a = new ArrayList<>();
        a.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1)));
        a.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1)));
        a.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1)));
        System.out.println(
        numberOfPaths(a));
    }

}
