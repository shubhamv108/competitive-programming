package code.tree;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.DoubleStream;

public class SuffixTree {

    private static final transient String WORD_TERMINATION = "$";

    Node root;

    SuffixTree() {
        this.root = new Node(null);
        this.root.alphabets = new Node[26];
    }

    static class Node {
        char[] sequence;
        Node[] alphabets;
        Integer position;

        Node(char[] sequence) {
            this.sequence = sequence;
            this.position = sequence.length - 1;
        }

        @Override
        public String toString() {
            return String.format("value=%s, alphabets=%s", sequence, Arrays.stream(alphabets).filter(Objects::nonNull).count());
        }
    }

    void add(char[] sequence) {
        if (root.sequence == null) {
            char[] newSequence = new char[sequence.length + 1];
            System.arraycopy(sequence, 0, newSequence, 0, sequence.length);
            root.position = sequence.length - 1;
        } else {
            put(root, sequence);
        }
    }

    void put(Node node, char[] sequence) {
        if (node.sequence == null) {
            root.position = sequence.length - 1;
        } else {
            int index = Character.toLowerCase(sequence[0]) - 'a';
            if (node.alphabets[index] == null)
                node.alphabets[index] = new Node(Arrays.copyOfRange(sequence, 1, sequence.length - 1));

        }
    }

    public static void main(String[] args) {
        SuffixTree tree = new SuffixTree();
        tree.add(new char[] { 'a' });
    }

}
