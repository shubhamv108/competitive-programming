package code.arrays.sorting;

public class BubbleSort {

    void sort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] { -1, 3, 5, 2 };
        new BubbleSort().sort(a);
        java.util.Arrays.stream(a).forEach(e -> System.out.print(e + " "));
    }

}
