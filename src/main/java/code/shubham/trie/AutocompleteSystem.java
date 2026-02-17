package code.shubham.trie;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AutocompleteSystem {

    Node trie = new Node();
    Searcher searcher = new Searcher(trie);
    String[] A;
    StringBuffer cur = new StringBuffer();

    public AutocompleteSystem(String[] A, int[] T) {
        this.A = A;
        for (int i = 0; i < A.length; ++i)
            trie.add(A[i], 0, i, T[i], A);
    }

    public List<String> input(char c) {
        if (c == '#') {
            return Collections.emptyList();
        }
        return Arrays
                .stream(searcher.search(c))
                .mapToObj(i -> this.A[i])
                .toList();
    }
}

class Searcher {
    Node trie, cur = null;

    Searcher(Node trie) {
        this.trie = trie;
        this.cur = trie;
    }

    int[] search(char c) {
        if (cur == null)
            return new int[0];
        cur = cur.search(c);
        if (cur == null || cur.q.isEmpty())
            return new int[0];
        int[] r = new int[3];
        int ri = 0;
        for (var itr = cur.q.iterator(); itr.hasNext();)
            r[ri++] = itr.next()[0];
        return r;
    }
}

class Node {
    Node[] next = new Node[256];
    PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> x[1] - y[1]);

    void add(String s, int si, int i, int d, String[] A) {
        if (s.length() == si)
            return;

        merge(s, i, d, A);

        char ch = s.charAt(si);
        if (next[ch] == null)
            next[ch] = new Node();
        next[ch].add(s, si + 1, i, d, A);
    }

    void merge(String s, int i , int d, String[] A) {
        if (q.size() == 3 && (q.peek()[1] < d
                || (q.peek()[1] == d && s.compareTo(A[q.peek()[0]]) < 0)))
            q.poll();
        if (q.size() < 3)
            q.offer(new int[] {i, d});
    }

    Node search(char ch) {
        if (next[ch] == null) {
            next[ch] = new Node();
        }
        return next[ch];
    }
}


/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */