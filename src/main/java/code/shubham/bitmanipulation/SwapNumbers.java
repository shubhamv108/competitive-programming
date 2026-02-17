package code.shubham.bitmanipulation;

import java.util.Arrays;

public class SwapNumbers {

    void swap(int[] A, int x, int y) {
        A[x] = A[x] ^ A[y];
        A[y] = A[x] ^ A[y];
        A[x] = A[x] ^ A[y];
    }

    public static void main(String[] args) {
        int[] A = new int[] {1, 2};
        new SwapNumbers().swap(A, 0, 1);
        Arrays.stream(A).forEach(System.out::println);
    }

}
