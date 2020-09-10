package code.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

public class LongestArithmeticProgression {

    public int solve(final List<Integer> A) {
        if (A.size() <= 2) return A.size();
        int L[][] = new int[A.size()][A.size()];
        int llap = 2;
        for (int i = 0; i < A.size(); i++) L[i][A.size() - 1] = 2;
        for (int j = A.size() - 2; j >= 1; j--) {
            int i = j - 1, k = j + 1;
            while (i >= 0 && k <= A.size() - 1) {
                if (A.get(i) + A.get(k) < 2 * A.get(j)) k++;
                else if (A.get(i) + A.get(k) > 2 * A.get(j)) {
                    L[i][j] = 2;
                    i--;
                } else {
                    L[i][j] = L[j][k] + 1;
                    llap = Math.max(llap, L[i][j]);
                    i--;
                    k++;
                }
            }
            while (i >= 0) {
                L[i][j] = 2;
                i--;
            }
            System.out.println("------------");
            Arrays.stream(L).forEach(a -> {
                Arrays.stream(a).forEach(e -> System.out.print(e + " "));
                System.out.println();
            });
            System.out.println("------------");
        }

        return llap;
    }

    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(100, 10, 8, 300, 6, 1, 4, 2);
        new LongestArithmeticProgression().solve(l);
    }

}
