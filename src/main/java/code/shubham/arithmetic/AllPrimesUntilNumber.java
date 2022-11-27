package code.shubham.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class AllPrimesUntilNumber {

    // Sieve of Eratosthenes
    public class Solution {
        public ArrayList<Integer> sieve(int A) {
            boolean[] b = new boolean[A + 1];
            Arrays.fill(b, true);

            for (int i = 2; i * i <= A; i++)
                if (b[i])
                    for (int j = i * i; j <= A; j += i)
                        b[j] = false;

            return IntStream.range(2, A+1).filter(i -> b[i] == true)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        }
    }


}
