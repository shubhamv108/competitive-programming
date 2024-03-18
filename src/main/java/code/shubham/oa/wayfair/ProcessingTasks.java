package code.shubham.oa.wayfair;

import java.util.HashMap;
import java.util.PriorityQueue;

public class ProcessingTasks {

    class Solution {
        void solve(int[] A, int[] T, int k) {
            HashMap<Integer, PriorityQueue<Integer>> typeTasks = new HashMap<>();
            for (int i = 0; i < A.length; ++i)
                typeTasks.computeIfAbsent(T[i], e -> new PriorityQueue<>())
                        .add(A[i]);
        }
    }

}
