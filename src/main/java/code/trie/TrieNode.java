package code.trie;

public class TrieNode {
    static final int ALPHABET_SIZE = 26;
    boolean isEOW;
    String meaning;
    TrieNode[] next;

    TrieNode() {
        next = new TrieNode[ALPHABET_SIZE]; // All array elements null by default
    }
}
