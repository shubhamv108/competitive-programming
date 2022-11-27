package code.shubham.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        recur(candidates, 0, target, new LinkedList(), result);
        return result;
    }

    void recur(int[] A, int a, int sum, LinkedList<Integer> elements, List<List<Integer>> result) {
        if (a >= A.length) return;
        if (sum < 0) return;
        if (sum == 0) { result.add(new ArrayList(elements)); return; }
        while (a < A.length && sum - A[a] >= 0) {
            elements.offer(A[a]);
            recur(A, a, sum - A[a], elements, result);
            a++;
            elements.pollLast();
        }
    }
}
