package code.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DistinctNumbersInWindow {
    public ArrayList<Integer> dNums (ArrayList<Integer> A, int B) {
        ArrayList<Integer> a = new ArrayList<>();
        if (A.size() < B ) return a;
        int c;
        HashMap<Integer, Integer> w = new HashMap<>();
        for (int i = 0; i < B; i++) {
            if  (w.containsKey(A.get(i))) c = w.get(A.get(i)) + 1;
            else c = 1;
            w.put(A.get(i), c);
        }
        a.add(w.size());
        int j = 0;
        for (int i = B; i < A.size(); i++) {
            if  (w.get(A.get(j)) == 1) w.remove(A.get(j));
            else w.put(A.get(j), w.get(A.get(j)) - 1);
            j++;
            if  (w.containsKey(A.get(i))) c = w.get(A.get(i)) + 1;
            else c = 1;
            w.put(A.get(i), c);
            a.add(w.size());
        }
        return a;
    }

    public static void main(String[] args) {

        int   B = 5;
        System.out.println(
            new DistinctNumbersInWindow().dNums(new ArrayList<>(Arrays.asList(45, 45, 3, 3, 3, 45, 45, 45, 45, 3, 3, 3, 3, 3, 3, 45, 45, 45, 45, 3, 3, 45, 3, 45, 3)), 5)
        );
    }
}
