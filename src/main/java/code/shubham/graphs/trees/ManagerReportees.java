package code.shubham.graphs.trees;

import java.util.HashMap;
import java.util.Map;

public class ManagerReportees {

    class Solution {
        Map<String, String> manager = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        Solution(String[][] A) {
            for (String[] a : A) {
                add(a[0], a[1]);
            }
        }

        public int count(String s) {
            return this.count.getOrDefault(s, 0);
        }

        public void add(String manager, String reportee) {
            this.manager.put(reportee, manager);
            updateCount(manager, 1);
        }

        public void change(String repostee, String manager) {
            String oldManager = this.manager.get(repostee);
            if (oldManager.equals(manager))
                return;
            int reporteeCount = this.count.getOrDefault(repostee, 0);
            this.manager.remove(repostee);
            updateCount(oldManager, -(reporteeCount + 1));
            this.manager.put(repostee, manager);
            updateCount(manager, reporteeCount + 1);
        }

        private void updateCount(String manager, int d) {
            count.put(manager, count.getOrDefault(manager, 0) + d);
            String m = this.manager.get(manager);
            if (m != null)
                updateCount(m, d);
        }
    }

    public static void main(String[] args) {
        Solution s = new ManagerReportees().new Solution(new String[][] {{"A", "B"}, {"A", "C"}, {"B", "D"}, {"B", "E"}, {"C", "F"}, {"C", "H"}, {"C", "G"}, {"F", "I"}});
        System.out.println(s.count("A"));
        System.out.println(s.count("B"));
        System.out.println(s.count("C"));
        System.out.println(s.count("F"));
        s.add("F", "J");
        System.out.println(s.count("A"));
        System.out.println(s.count("B"));
        System.out.println(s.count("C"));
        System.out.println(s.count("F"));
        s.change("F", "B");
        System.out.println(s.count("A"));
        System.out.println(s.count("B"));
        System.out.println(s.count("C"));
        System.out.println(s.count("F"));
    }

}
