package code.shubham.heapsmaps;

import input.InputUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {

    HashMap<Integer, Integer> set = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!set.containsKey(val)) {
            return set.put(val, list.size()) == null && list.add(val);
        } else {
            return false;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer index = set.remove(val);
        if (index != null) {
            if (index < list.size()) {
                int lastIndexValue = list.get(list.size() - 1);
                list.set(index, lastIndexValue);
                set.put(lastIndexValue, index);
            }
            list.remove(list.size() - 1);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        while (true) {
            int operaton = InputUtils.nextInt();
            if (1 == operaton) {
                boolean param_1 = obj.insert(InputUtils.nextInt());
            }
            else if (0 == operaton) {
                boolean param_2 = obj.remove(InputUtils.nextInt());
            }
            System.out.println(obj.getRandom());
        }
    }

}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */