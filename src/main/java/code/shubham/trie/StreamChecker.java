package code.shubham.trie;

import java.util.LinkedList;
import java.util.Queue;

public class StreamChecker {
    Node root = new Node();
    Queue<Node> q = new LinkedList<>();
    public StreamChecker(String[] W) {
        for (String w : W)
            root.add(w, 0);
        q.add(root);
    }

    public boolean query(char ch) {
        boolean result = false;
        for (int size = q.size(); size > 0; --size) {
            Node p = q.poll();
            Node child = p.getChild(ch);
            if (child == null)
                continue;
            if (child.isEOW)
                result = true;
            q.offer(child);
        }
        q.offer(root);
        return result;
    }

    class Node {
        boolean isEOW;
        Node[] next = new Node[26];

        Node getChild(int ch) {
            return next[ch -'a'];
        }

        void add(String w, int idx) {
            if (w.length() == idx) {
                isEOW = true;
                return;
            }

            int ch = w.charAt(idx) - 'a';
            if (next[ch] == null)
                next[ch] = new Node();
            next[ch].add(w, idx + 1);;
        }
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
