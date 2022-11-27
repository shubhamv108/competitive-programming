package code.shubham.arrays;

import java.util.stream.IntStream;

public class CountFrequency {
    void printfrequency(int arr[], int n) {
        IntStream.range(0, n).forEach(i -> arr[i] -= 1);
        IntStream.range(0, n).forEach(i -> arr[arr[i] % n] += n);
        IntStream.range(0, n).forEach(i -> System.out.println(i + 1 + "->" + arr[i] / n));
    }

    public static void main(String[] args) {
        new CountFrequency().printfrequency(new int[] {2, 3, 3, 2, 5}, 5);
    }
}

