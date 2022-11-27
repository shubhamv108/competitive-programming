package code.shubham.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChooseAFlask {
    class Solution {
        /*
        umOrders : an integer representing the number of orders;
requirements: a list of integers representing the requirements for the orders;
flaskTypes : an integer representing the number of flask types;
totalMarks : an integer representing the total number of markings;
markings: a list of pairs of integers where the first int
         */
        int solve(int numOrders, int[] requirements, int flaskTypes, int totalMarks, int[][] markings) {
            HashMap<Integer, ArrayList<Integer>> markedFlasks = new HashMap<>();
            for (int[] marking : markings){
                ArrayList<Integer> list = markedFlasks.getOrDefault(marking[0], new ArrayList<>());
                list.add(marking[1]);
                markedFlasks.put(marking[0], list);
            }

            int minWaste = Integer.MAX_VALUE, result = 0;
            for (Map.Entry<Integer, ArrayList<Integer>> markedFlask : markedFlasks.entrySet()) {
                ArrayList<Integer> marks = markedFlask.getValue();
                int waste = 0;
                boolean flag = false;
                for (int req : requirements) {
                    int m = getHigherBoundIndex(marks, req);
                    if (m == -1) {
                        flag = true;
                        break;
                    }
                    waste += marks.get(m) - req;
                }
                if (flag) continue;
                if (waste < minWaste) {
                    minWaste = waste;
                    result = markedFlask.getKey();
                }
                else if (waste == minWaste) {
                    result = Math.min(result, markedFlask.getKey());
                }
            }
            return result;
        }

        public int getHigherBoundIndex(ArrayList<Integer> A, int searchValue) {
            if (A.get(A.size() - 1) < searchValue) return -1;
            if (searchValue < A.get(0)) return 0;
            int l = 0, r = A.size() - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (searchValue == A.get(m)) {
                    return m;
                } if (m < A.size() - 1 && A.get(m) < searchValue && searchValue < A.get(m+1)) {
                    return m + 1;
                }
                if (A.get(m) < searchValue) {
                    l = m+1;
                } else {
                    r = m-1;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ChooseAFlask().new Solution().solve(4, new int[]{4, 6, 6, 7}, 3, 9,
                        new int[][] {{0, 3}, {0,5}, {0,7},{1,6},{1,8}, {1,9}, {2,3}, {2,5}, {2,6}})
        );
    }
}
