package code.shubham.greedy;

import java.util.ArrayList;

public class DistributeCandy {
    public int candy(ArrayList<Integer> A) {

        int[] candies = new int[A.size()];

        candies[0] = 1;
        for (int i = 1; i < A.size(); i++)
            candies[i] = A.get(i) > A.get(i-1) ? candies[i-1] + 1 : 1;

        int result = candies[candies.length - 1];
        int cur;
        for (int i = A.size() - 2; i >= 0; i--) {
            cur = A.get(i) > A.get(i+1) ? candies[i+1] + 1 : 1;
            result += Math.max(candies[i], cur);
            candies[i] = cur;
        }

        return result;
    }

}
