package code.dynamicprogramming;

import code.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MinSumPathInMatrix {

    class Solution {
        public int minPathSum(ArrayList<ArrayList<Integer>> A) {
            Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();
            cache.put(0, new HashMap<>());
            cache.get(0).put(0, A.get(0).get(0));
            int sum = A.get(0).get(0);
            for (int i = 1; i < A.get(0).size(); i++) {
                sum += A.get(0).get(i);
                cache.get(0).put(i, sum);
            }
            sum = A.get(0).get(0);
            for (int i = 1; i < A.size(); i++) {
                cache.put(i, new HashMap<>());
                sum += A.get(i).get(0);
                cache.get(i).put(0, sum);
            }
            for (int i = 1; i < A.size(); i++)
                for (int j = 1; j < A.get(0).size(); j++)
                    cache.get(i).put(j,
                            A.get(i).get(j) +
                                    Math.min(cache.get(i-1).get(j),
//                                            Math.min(cache.get(i-1).get(j-1),
                                                        cache.get(i).get(j-1))/*)*/);
            return cache.get(A.size() - 1).get(A.get(0).size() - 1);
        }
    }

    class Solution2 {
        public int minPathSum(ArrayList<ArrayList<Integer>> A) {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(A.get(0).get(0));
            for (int i = 1; i < A.get(0).size(); i++)
                a.add(A.get(0).get(i) + a.get(i-1));
            for (int i = 1; i < A.size(); i++) {
                for (int j = 0; j < A.get(0).size(); j++) {
                    if (j == 0) a.set(0, a.get(0) + A.get(i).get(0));
                    else a.set(j, A.get(i).get(j) + Math.min(a.get(j), a.get(j - 1)));
                }
            }
            return a.get(A.get(0).size() - 1);
        }
    }

    /**
     * Time: n-1
     * Space: 0
     */
    class Solution3 {
        public int minPathSum(ArrayList<ArrayList<Integer>> A) {
            for (int i = 1; i < A.get(0).size(); i++)
                A.get(0).set(i, A.get(0).get(i) + A.get(0).get(i-1));
            for (int i = 1; i < A.size(); i++) {
                for (int j = 0; j < A.get(0).size(); j++) {
                    if (j == 0) A.get(i).set(j, A.get(i-1).get(j) + A.get(i).get(j));
                    else A.get(i).set(j, A.get(i).get(j) +
                            Math.min(A.get(i-1).get(j), A.get(i).get(j - 1)));
                }
            }
            return A.get(A.size() - 1).get(A.get(0).size() - 1);
        }
    }

    class Solution4 {
        public int minPathSum(ArrayList<ArrayList<Integer>> A) {
            for (int i = 1; i < A.get(0).size(); i++)
                A.get(0).set(i, A.get(0).get(i) + A.get(0).get(i-1));
            for (int i = 1; i < A.size(); i++) {
                for (int j = 0; j < A.get(i).size(); j++) {
                    if (j == 0) A.get(i).set(j, A.get(i-1).get(j) + A.get(i).get(j));
                    else A.get(i).set(j, A.get(i).get(j) +
                            Math.min(A.get(i-1).size() >= j ? Integer.MAX_VALUE : A.get(i-1).get(j),
                                     A.get(i).get(j - 1)));
                }
            }
            return A.get(A.size() - 1).get(A.get(0).size() - 1);
        }
    }

    public static void main(String[] args) {
        int[][] a = {{20, 29, 84, 4,   32, 60, 86, 8,  7},
                     {77, 69, 85, 83,  81, 78, 22, 45, 43, 63},
                     {60, 21, 0,  94,  59, 88, 9,  54, 30, 80},
                     {40, 78, 52, 58,  26, 84, 47, 0,  24, 60},
                     {40, 17, 69, 5,   38, 5,  75, 59, 35, 26},
                     {64, 41, 85, 22,  44, 25, 3,  63, 33, 13},
                     {2,  21, 39, 51,  75, 70, 76, 57, 56, 22},
                     {31, 45, 47, 100, 65, 10, 94, 96, 81, 14}};
//        int[][] a = {{1, 3},
//                    {4, 3}};
        System.out.println(new MinSumPathInMatrix().new Solution4().minPathSum(ArrayUtils.getArrayListFrom2D(a)));
    }

}
