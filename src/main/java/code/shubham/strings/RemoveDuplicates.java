package code.shubham.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class RemoveDuplicates {

    public String removeDuplicateLetters(String s) {
        HashSet<Character> visited = new HashSet<>();
        LinkedList<Character> l = new LinkedList<>();

        HashMap<Character, Integer> counters = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            counters.put(c, counters.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counters.put(c, counters.get(c) - 1);
            if (!visited.contains(c)) {
                while (!l.isEmpty() && l.peekLast() >= c && counters.get(l.peekLast()) > 0) {
                    visited.remove(l.peekLast());
                    l.removeLast();
                }
                l.offerLast(c);
                visited.add(c);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!l.isEmpty()) result.append(l.removeFirst());
        return result.toString();
    }

    public static void main(String[] args) {

    }

}
