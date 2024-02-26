package code.shubham;

import java.util.HashMap;

public class TwoSumIIIDataStructureDesign {
    public class TwoSum {
        /**
         * @param number: An integer
         * @return: nothing
         */
        private final HashMap<Integer, Integer> m = new HashMap<>();
        public void add(int n) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }

        /**
         * @param value: An integer
         * @return: Find if there exists any pair of numbers which sum is equal to the value.
         */
        public boolean find(int value) {
            int diff; Integer f;
            for (int n : m.keySet()) {
                diff = value - n;
                f = m.get(diff);
                if (f != null && (diff != n || f > 1))
                    return true;
            }
            return false;
        }
    }
}
