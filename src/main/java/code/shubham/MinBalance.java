package code.shubham;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MinBalance {

    public List<String> minBalance(List<List<String>> A) {
        Map<String, Long> balances = new HashMap<>();
        TreeMap<Long, Set<String>> f = new TreeMap<>();

        for (List<String> a : A) {
            updateBalance(a.get(0), -Integer.parseInt(a.get(2)), balances, f);
            updateBalance(a.get(1), Integer.parseInt(a.get(2)), balances, f);
        }

        long min = f.firstEntry().getKey();
        if (min >= 0)
            return List.of("Nobody has negative balance");

        return f.get(min).stream().sorted().toList();
    }

    void updateBalance(String a, int amt, Map<String, Long> balances, Map<Long, Set<String>> f) {
        long old = balances.getOrDefault(a, 0L);
        long n = old + amt;
        balances.put(a, n);
        Set<String> set = f.getOrDefault(old, Collections.emptySet());
        set.remove(a);
        if (set.isEmpty())
            f.remove(old);
        f.computeIfAbsent(n, _ -> new HashSet<>()).add(a);
    }

    public static void main(String[] args) {
        System.out.println(new MinBalance().minBalance(Arrays.asList(
                Arrays.asList("A", "B", "2"),
                Arrays.asList("B", "A", "2"),
                Arrays.asList("A", "B", "2"),
                Arrays.asList("C", "A", "2"),
                Arrays.asList("A", "C", "1"))));
    }
}
