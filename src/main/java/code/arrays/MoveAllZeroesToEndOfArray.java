package code.arrays;

import java.util.Arrays;

public class MoveAllZeroesToEndOfArray {

    void move1(int[] A) {
        int c = 0;
        for (int i = 0; i < A.length; i++) {
            if (0 != A[i]) {
                if (c != i) {
                    A[c] = A[i];
                    A[i] = 0;
                }
                c++;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = new int[] { 9, 0, 1, 0, 0, 1 ,0 ,3 , 5, 6, 9, 0, 8 };
        new MoveAllZeroesToEndOfArray().move1(A);
        Arrays.stream(A).forEach(System.out::print);
    }

}
