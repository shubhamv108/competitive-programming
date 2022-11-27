package code.shubham.greedy;

import java.util.ArrayList;

public class Bulbs {
    public int bulbs(ArrayList<Integer> A) {
        if (A.size() == 0)
            return 0;

        if (A.size() == 1)
            if (A.get(0) == 1)
                return 0;
            else
                return 1;

        int count = 0;
        int res = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) == 1 && (count & 1) == 0)
                continue;
            else if(A.get(i) == 0 && (count & 1) == 1)
                continue;
            else if (A.get(i) == 1 && (count & 1) == 1) {
                res++;
                count++;
            } else if (A.get(i) == 0 && (count & 1) == 0) {
                res++;
                count++;
            }
        }
        return res;
    }
}
