package code.binarysearch;

public class FrequencyOfGivenNumberInSortedArrayWithDuplicates {

    private static long[] a = { -2, -1, 0, 1, 1 ,1, 2, 3, 5, 6 };
    private static int n = 2;

    public static int find(int l, int r) {
        if (l == r) {
            if (a[l] == n) return 1;
            return 0;
        }
        int m = (r + l)/2;
        if (n < a[m]) return find(l, m-1);
        if (n > a[m]) return find(m+1, r);
        if (a[m] == n) {
            int f = 1;
            if (a[m-1] == n) f += 1 + find(l, m - 2);
            if (a[m+1] == n) f += 1 + find(m + 2, r);
            return f;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(find(0, a.length - 1));
    }

}
