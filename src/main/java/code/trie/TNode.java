package code.trie;

class TNode {
    static final int ALPHABET_SIZE = 26;
    boolean isEOW;
    TNode[] next;

    TNode() {
        next = new TNode[ALPHABET_SIZE]; // All array elements null by default
    }
}
