package code.shubham.contestpractice.oa.swiggy;

import java.util.Arrays;

public class ProgrammingContest {

    class Solution {
        int solve(int[] ratings) {
            int result = 0;
            Arrays.sort(ratings);
            for (int i = 1; i < ratings.length; i+=2)
                result += (ratings[i] - ratings[i-1]);
            return result;
        }
    }

    public static void main(String[] args) {
        ProgrammingContest programmingContest = new ProgrammingContest();
        Solution solution = programmingContest.new Solution();
        System.out.println(solution.solve(new int[] { 4, 2, 5, 1 }));
    }

}
