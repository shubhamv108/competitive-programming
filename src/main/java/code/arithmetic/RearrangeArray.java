package code.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;

public class RearrangeArray {

    /**
     *

     Lets say N = size of the array. Then, following holds true :

     All elements in the array are in the range [0, N-1]
     N * N does not overflow for a signed integer

     Time Complexity: O(N)
     Auxillary Space: (1)


     Input:
     4 3 2 1 0

     Output:
     0 1 2 3 4

     */
    public class Solution {
        public void arrange(ArrayList<Integer> a) {

            int n = a.size();
            for (int i = 0; i < n; i++)
                a.set(i, a.get(i) + (a.get(a.get(i)) % n) * n);

            for (int i = 0; i < n; i++)
                a.set(i, a.get(i)/n);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(4, 3, 2, 1, 0));
        new RearrangeArray().new Solution().arrange(a);
        a.stream().forEach(e -> System.out.print(e + " "));
    }


}
