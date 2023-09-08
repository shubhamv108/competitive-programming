package code.shubham.strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class TargetStringFromDictionary {
    class Solution {
        boolean solve(String[] dict, String W) {
            Node root = new Node();
            IntStream.range(0, dict.length).forEach(i -> root.insert(dict[i], 0, i));
            return recurse(W, 0, root, new HashSet<>());
        }

        boolean recurse(String W, int index, Node root, HashSet<Integer> visited) {
            if (index == W.length())
                return true;

            LinkedList<int[]> words = new LinkedList<>();
            find(W, index, root, visited, words);

            for (int[] pair: words) {
                visited.add(pair[1]);
                if (recurse(W, pair[0], root, visited))
                    return true;
                visited.remove(pair[1]);
            }

            return false;
        }

        void find(String W, int index, Node node, HashSet<Integer> visited, LinkedList<int[]> words) {
            if (!node.pos.isEmpty())
                for (int p: node.pos)
                    if (visited.add(p))
                        words.addFirst(new int[] {index, p});

            if (index == W.length())
                return;

            int nextIndex = W.charAt(index) - 'a';
            if (node.next[nextIndex] == null)
                return;

            find(W, index + 1, node.next[nextIndex], visited, words);
        }
    }

    private static class Node {
        ArrayList<Integer> pos = new ArrayList<>();
        Node[] next = new Node[26];

        void insert(String w, int index, int p) {
            if (index == w.length()) {
                pos.add(p);
                return;
            }

            int nextIndex = w.charAt(index) - 'a';
            if (next[nextIndex] == null)
                next[nextIndex] = new Node();

            next[nextIndex].insert(w, index + 1, p);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new TargetStringFromDictionary().new Solution().solve(
                        new String[] {"d", "ed", "de", "co", "co"},
                        "cocode"
                )
        );
    }
}
