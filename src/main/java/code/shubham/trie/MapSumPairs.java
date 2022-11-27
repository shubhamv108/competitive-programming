package code.shubham.trie;

import java.util.HashMap;

/**
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 */
public class MapSumPairs {

    /** Initialize your data structure here. */
    HashMap<String, Integer> m = new HashMap<>();
    TrieNode root = new TrieNode();

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int value;
    }

    public void insert(String key, int val) {
        TrieNode n = root;
        if (m.containsKey(key)) {
            n.value -= m.get(key);
        }
        n.value += val;
        for (char c : key.toCharArray()) {
            TrieNode child = n.children[c-'a'];
            if (child == null) {
                child = new TrieNode();
            }
            n = n.children[c-'a'] = child;
            if (m.containsKey(key)) {
                n.value -= m.get(key);
            }
            n.value += val;
        }
        m.put(key, val);
    }

    public int sum(String prefix) {
        TrieNode n = root;
        int i = -1;
        char[] c = prefix.toCharArray();
        while (n != null && ++i < prefix.length()) {
            n = n.children[c[i] - 'a'];
        }
        if (i == prefix.length()) {
            return n.value;
        }
        return 0;
    }

    public static void main(String[] args) {
        MapSumPairs m = new MapSumPairs();
        m.insert("apple", 1);
        m.insert("appl", 2);
        System.out.println(m.sum("appl"));
    }

}