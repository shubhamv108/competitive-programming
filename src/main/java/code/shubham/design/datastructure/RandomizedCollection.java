package code.shubham.design.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;

public class RandomizedCollection {

    HashMap<Integer, LinkedHashSet<Integer>> m = new HashMap<>();
    ArrayList<Integer> l = new ArrayList<>();
    Random random = new Random();

    public RandomizedCollection() {

    }

    public boolean insert(int val) {
        boolean result = false;
        LinkedHashSet<Integer> p = m.get(val);
        if (p == null || p.size() == 0) {
            if (p == null)
                m.put(val, p = new LinkedHashSet<>());
            result = true;
        }
        p.add(l.size());
        l.add(val);
        return result;
    }

    public boolean remove(int val) {
        LinkedHashSet<Integer> p = m.get(val);;
        if (p == null || p.size() == 0)
            return false;

        Integer lastVal = l.get(l.size() - 1);
        if (lastVal == val) {
            p.remove(l.size() - 1);
            l.remove(l.size() - 1);
            return true;
        }

        int index = p.iterator().next();
        p.remove(index);
        l.set(index, lastVal);
        LinkedHashSet<Integer> s = m.get(lastVal);
        s.remove(l.size() - 1);
        s.add(index);
        l.remove(l.size() - 1);
        return true;
    }

    public int getRandom() {
        return l.get(random.nextInt(l.size()));
    }
}