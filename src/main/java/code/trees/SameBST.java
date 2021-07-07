package code.tree;

import java.util.ArrayList;
import java.util.Arrays;

public class SameBST {
    boolean sameBSTs(ArrayList<Integer> aL1,
                     ArrayList<Integer> aL2) {
        if (aL1.size() != aL2.size())
            return false;
        if (aL1.size() == 0)
            return true;
        if (aL1.get(0) != aL2.get(0))
            return false;

        ArrayList<Integer> aLLeft1 = new ArrayList<>();
        ArrayList<Integer> aLRight1 = new ArrayList<>();
        ArrayList<Integer> aLLeft2 = new ArrayList<>();
        ArrayList<Integer> aLRight2 = new ArrayList<>();
        for (int i = 1; i < aL1.size(); i++) {
            if (aL1.get(i) < aL1.get(0))
                aLLeft1.add(aL1.get(i));
            else
                aLRight1.add(aL1.get(i));

            if (aL2.get(i) < aL2.get(0))
                aLLeft2.add(aL2.get(i));
            else
                aLRight2.add(aL2.get(i));
        }

        return sameBSTs(aLLeft1, aLLeft2) &&
                sameBSTs(aLRight1, aLRight2);
    }

    public static void main(String[] args) {
        ArrayList<Integer> aL1 =
                new ArrayList<>(Arrays.
                        asList(3, 5, 4, 6, 1, 0, 2));
        ArrayList<Integer> aL2 =
                new ArrayList<>(Arrays.
                        asList(3, 1, 5, 2, 4, 6, 0));

        System.out.println(new SameBST().sameBSTs(aL1, aL2));
    }
}
