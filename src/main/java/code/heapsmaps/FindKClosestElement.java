package code.heapsmaps;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElement {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();

        int l = 0;
        int r = arr.length - k;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        for (int i = l; i < l+k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        new FindKClosestElement().findClosestElements(new int[]{1,2,5,5,6,6,7,7,8,9},
        7,
        7)
            .forEach(System.out::println);
    }

}
