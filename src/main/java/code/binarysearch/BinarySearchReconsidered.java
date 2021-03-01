package code.binarysearch;

public class BinarySearchReconsidered {

    int find(int a[], int size, int val) {
        if (size <= 0) return -1;
        int lower = 0, upper = size;
        while (upper - lower > 1) {
            int m = lower + (upper - lower) / 2;
            if (val < a[m]) upper = m;
            else            lower = m;
        }
        return (val == a[lower]) ? lower : -1;
    }

}
