package code.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ThreeSum {

    public int threeSumClosest(ArrayList<Integer> A, int B) {
        int min = Integer.MAX_VALUE;
        int result = 0;
        Collections.sort(A);

        for(int i = 0; i < A.size(); i++){
            int j = i+1;
            int k = A.size()-1;
            while(j < k){
                int sum = A.get(i) + A.get(j) + A.get(k);
                int diff = Math.abs(sum - B);
                if(diff == 0)
                    return sum;
                if(diff < min){
                    min = diff;
                    result = sum;
                }
                if(sum <= B)
                    j++;
                else
                    k--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            new ThreeSum().threeSumClosest(new ArrayList<>(Arrays.asList(-1, 2, 1, -4)), 1)
        );
    }
}
