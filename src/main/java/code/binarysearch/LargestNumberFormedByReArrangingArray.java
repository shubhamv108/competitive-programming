package code.binarysearch;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LargestNumberFormedByReArrangingArray {

    final Comparator<Integer> c = (Integer u, Integer v) -> {
        String w = u.toString();
        String x = v.toString();
        String wx = w + x;
        String xw = x + w;
        if(wx.compareTo(xw) > 0)
            return -1;
        return 1;
    };

    void rearrangeArrayToFormLargestNumber(int a[]) {
        if (a == null || a.length == 0) throw new IllegalArgumentException();
        List<Integer> l = Arrays.stream(a).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        Collections.sort(l, c);
        l.forEach(System.out::print);
    }

    public static void main(String[] args) {
        new LargestNumberFormedByReArrangingArray().rearrangeArrayToFormLargestNumber(new int[]{1, 20, 9, 90, 89});
    }
}
