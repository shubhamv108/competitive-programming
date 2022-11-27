package code.shubham.arrays;

import java.util.ArrayList;

public class RotateMatrix {

    public class Solution {
        public void rotate(ArrayList<ArrayList<Integer>> a) {
            int n = a.size();
            int tmp;
            for (int i = 0; i < n-1; i++) {
                for (int j = i+1; j<n; j++) {
                    tmp = a.get(i).get(j);
                    a.get(i).set(j, a.get(j).get(i));
                    a.get(j).set(i, tmp);
                }
            }

            for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < n; j++) {
                    tmp = a.get(j).get(i);
                    a.get(j).set(i, a.get(j).get(n-1-i));
                    a.get(j).set(n-1-i, tmp);
                }
            }
        }
    }

}
