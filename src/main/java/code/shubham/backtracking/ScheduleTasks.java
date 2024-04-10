package code.shubham.backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/*
Tasks with memory required to process are given which should be run in a processor.
Tasks : [1,4,5,2,3] (the numbers represent memory needed)
Type of tasks are given in another array
Task Type : [1,2,1,3,4]
Another input is given which is the max memory the processor can run at a time.
Max_memory : 6
Now only two tasks with same task type can run in parallel. Assume each tasks take 1 unit of time. Find out the time taken to complete all the tasks.
Sample output : 3 units. Because there are two tasks of type 1 and adding their memory is 6 which is eq to max_memory. So they ll run in parallel. All other tasks are of different types and their memories are less than max_memory. So they ll each take 1 unit of time.
 */
public class ScheduleTasks {

    class Solution {
        int result = Integer.MAX_VALUE;
        int solve(List<Integer> M, List<Integer> T, int k) {
            recurse(M, T, k, 0, new HashSet<>(), new HashMap<>(), new HashSet<>(), 1);
            return result;
        }

        void recurse(List<Integer> M, List<Integer> T, int k, int sum, HashSet<Integer> p, HashMap<Integer, Integer> pT,
                     HashSet<Integer> done, int curTime) {
            if (M.size() == done.size()) {
                result = Math.min(result, curTime);
                return;
            }

            for (int i = 0; i < M.size(); ++i) {
                if (done.contains(i))
                    continue;

                int c = pT.getOrDefault(T.get(i), 0);
                if (c == 2)
                    continue;

                if (sum + M.get(i) > k) {
                    recurse(M, T, k, 0, new HashSet<>(), new HashMap<>(), done, curTime + 1);
                    continue;
                }

                p.add(i);
                pT.put(T.get(i), c + 1);
                done.add(i);
                recurse(M, T, k, sum + M.get(i), p, pT, done, curTime);
                p.remove(i);
                pT.put(T.get(i), pT.get(T.get(i)) - 1);
                done.remove(i);

            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new ScheduleTasks().new Solution().solve(List.of(7, 2, 3, 9), List.of(1, 2, 1, 3), 10)); // 3
        System.out.println(new ScheduleTasks().new Solution().solve(List.of(1,4,5,2,3), List.of(1,2,1,3,4), 6)); // 3
    }

}
