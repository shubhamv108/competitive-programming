package code.arrays;

import java.util.ArrayList;
import java.util.Collections;

public class NextPermutation {

    class Solution {
        public void nextPermutation (ArrayList<Integer> A) {
            int n = A.size();
            int k = -1;
            int l = 0;

            for(int i = 0; i < n-1; i++){
                if(A.get(i) < A.get(i+1))
                    k = i;
            }

            if(k == -1){
                Collections.sort(A);
                return;
            }

            for(int i = k+1; i < n; i++){
                if(A.get(i) > A.get(k)){
                    l = i;
                }
            }
            int temp = A.get(l);
            A.set(l, A.get(k));
            A.set(k, temp);
            int j = k + 1;
            int last = n-1;
            while(j <= last){
                temp = A.get(j);
                A.set(j, A.get(last));
                A.set(last, temp);
                j++;
                last--;
            }

            for(int i = 0; i < A.size(); i++)
                System.out.print(A.get(i) + " ");
        }
    }

    public static void main(String[] args){
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(1);
        A.add(3);
        A.add(2);
        new NextPermutation().new Solution().nextPermutation(A);
    }
}
