package code.shubham.dynamicprogramming.jumpgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class JumpGameIV {
    class Solution {
        public int minJumps(int[] A) {
            HashMap<Integer, ArrayList<Integer>> pos = new HashMap<>();
            for (int i = 0; i < A.length; i++) {
                ArrayList<Integer> l = pos.get(A[i]);
                if (l == null)
                    pos.put(A[i], l = new ArrayList<>());
                l.add(i);
            }

            int result = 0;
            HashSet<Integer> visited = new HashSet<>();
            Queue<Integer> q = new LinkedList<>();
            q.offer(0);
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int p = q.poll();
                    if (A.length - 1 == p)
                        return result;

                    ArrayList<Integer> l = pos.get(A[p]);
                    if (p > 0)
                        l.add(p-1);
                    if (p < A.length -1)
                        l.add(p+1);

                    for (int n: l)
                        if (!visited.contains(n)) {
                            visited.add(n);
                            q.offer(n);
                        }

                    l.clear();
                }
                result++;
            }

            return result;
        }
    }
}
