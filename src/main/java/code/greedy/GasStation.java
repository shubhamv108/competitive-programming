package code.greedy;

import java.util.List;

public class GasStation {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int canCompleteCircuit(final List<Integer> A, final List<Integer> B) {
        if (A.size() == 1)
            return A.get(0) - B.get(0) < 0 ? -1 : 0;

        int start = 0;
        int end = A.size() > 1 ? 1 : 0;
        int cur = A.get(start) - B.get(start);
        while (start != end || cur < 0) {
            while (cur < 0 && start != end) {
                cur -= A.get(start) - B.get(start);
                start = (start + 1) % A.size();
                if (start == 0)
                    return -1;
            }
            cur += A.get(end) - B.get(end);
            end = (end + 1) % A.size();
        }
        return start;
    }
}

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 1)
            return gas[0] - cost[0] < 0 ? -1 : 0;

        int start = 0;
        int end = 1;
        int cur = gas[start] - cost[start];
        while (start != end || cur < 0) {
            while (cur < 0 && start != end) {
                cur -= gas[start] - cost[start];
                start = (start + 1) % gas.length;
                if (start == 0)
                    return -1;
            }
            cur += gas[end] - cost[end];
            end = (end + 1) % gas.length;
        }
        return start;
    }
}
