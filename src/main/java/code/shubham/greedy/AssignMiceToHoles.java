package code.shubham.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AssignMiceToHoles {

    public int mice(ArrayList<Integer> A, ArrayList<Integer> B) {
        if (A.size() != B.size())
            return -1;

        A.sort(Comparator.comparingInt(a -> a));
        B.sort(Comparator.comparingInt(a -> a));
        int max = Math.min(A.get(0), B.get(0)) - 1;
        for (int i = 0; i < A.size(); i++)
            max = Math.max(max, Math.abs(A.get(i) - B.get(i) - 1));

        return max;
    }

}
