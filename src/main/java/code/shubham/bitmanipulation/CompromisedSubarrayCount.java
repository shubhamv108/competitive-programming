package code.shubham.bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CompromisedSubarrayCount {

    long solve(List<Integer> A) {
        long result = 0;
        int n = A.size();
        HashMap<Integer, Integer> li = new HashMap<>();
        List<int[]> or = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            int a = A.get(j);
            li.put(a, j);
            List<int[]> nextOr = new ArrayList<>();
            nextOr.add(new int[] { a, j });
            for (int[] p : or) {
                int newor = p[0] | a;
                if (nextOr.get(nextOr.size() - 1)[0] == newor) {
                    nextOr.get(nextOr.size() - 1)[1] = p[1];
                } else {
                    nextOr.add(new int[] { newor, p[1] });
                }
            }

            or = nextOr;

            for (int i = 0; i < or.size(); ++i) {
                int cur = or.get(i)[0];
                int l = or.get(i)[1];
                int r = (i == 0) ? j : or.get(i-1)[1] - 1;

                Integer lastPos = li.get(cur);
                if (lastPos != null && lastPos >= l)
                    result += Math.min(r, lastPos) - l + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new CompromisedSubarrayCount().solve(Arrays.asList(2, 4, 7)));
    }
}
