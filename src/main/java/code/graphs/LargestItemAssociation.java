package code.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class LargestItemAssociation {

    class Solution {
        public List<String> largestItemAssociation(List<PairString> itemAssociation) {
            Map<String, ArrayList<String>> graph = new HashMap<>();
            for (PairString pair : itemAssociation) {
                ArrayList<String> l = graph.get(pair.first);
                if (l == null) graph.put(pair.first, l = new ArrayList<>());
                l.add(pair.second);
                l = graph.get(pair.second);
                if (l == null) graph.put(pair.second, l = new ArrayList<>());
                l.add(pair.first);
            }

            HashSet<String> visited = new HashSet<>();
            int max = 0;
            ArrayList<String> result = new ArrayList<>();
            ItemHolder lexSmallestItem = null;
            for (String v: graph.keySet()) {
                if (!visited.contains(v)) {
                    ArrayList<String> l = new ArrayList<>();
                    ItemHolder smallest = new ItemHolder();
                    int count = this.visit(v, graph, visited, l, smallest);
                    if (count > max) {
                        max = count;
                        result = l;
                        lexSmallestItem = smallest;
                    }
                    if (count == max) {
                        if (smallest.lexSmallestItem.compareTo(lexSmallestItem.lexSmallestItem) < 0) {
                            result = l;
                            lexSmallestItem = smallest;
                        }
                    }
                }
            }
            Collections.sort(result);
            return result;
        }

        class ItemHolder {
            String lexSmallestItem;
        }

        int visit(String v, Map<String, ArrayList<String>> graph, HashSet<String> visited, ArrayList<String> assoc, ItemHolder itemHolder) {
            if (visited.contains(v)) return 0;
            visited.add(v);
            assoc.add(v);
            if (itemHolder.lexSmallestItem == null || itemHolder.lexSmallestItem.compareTo(v) > 0) {
                itemHolder.lexSmallestItem = v;
            }
            int count = 1;
            for (String n : graph.get(v))
                count += visit(n, graph, visited, assoc, itemHolder);
            return count;
        }
    }

    public static class PairString {
        String first;
        String second;

        public PairString(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        PairString pairString = new PairString("item1", "item2");
        PairString pairString2 = new PairString("item2", "item3");
        PairString pairString3 = new PairString("item4", "item4");
        System.out.println(
                new LargestItemAssociation().new Solution()
                        .largestItemAssociation(Arrays.asList(pairString, pairString2, pairString3))
        );
    }

}
