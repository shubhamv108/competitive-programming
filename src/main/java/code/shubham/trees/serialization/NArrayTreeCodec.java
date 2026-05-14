package code.shubham.trees.serialization;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NArrayTreeCodec {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }

        @Override
        public String toString() {
            return new Codec().serialize(this);
        }
    }

    class Codec {
        // Encodes a tree to a single string.
        public String serialize(Node root) {
            if (root == null)
                return "";
            StringBuilder result = new StringBuilder();
            result.append(root.val).append('-').append('$');
            LinkedList<Node> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; ++i) {
                    Node p = q.poll();
                    if (p.children == null)
                        continue;
                    for (Node c : p.children) {
                        result.append(c.val).append('-');
                        q.offer(c);
                    }
                    result.append("$");
                }
            }

            return result.toString();
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if (data == null || data.isEmpty())
                return null;
            int n = data.length();
            int i = 0;
            Node result = new Node(-1, new ArrayList<>());
            LinkedList<Node> q = new LinkedList<>();
            StringBuilder val = new StringBuilder();
            q.offer(result);
            while (!q.isEmpty()) {
                Node p = q.poll();
                while (i < n) {
                    char c = data.charAt(i++);
                    if (c == '-') {
                        if (p.children == null)
                            p.children = new ArrayList<>();
                        Node child = new Node(Integer.parseInt(val.toString()));
                        p.children.add(child);
                        q.offer(child);
                        val.setLength(0);
                    } else if (c == '$')
                        break;
                    else
                        val.append(c);
                }
            }
            return result.children.isEmpty() ? new Node() : result.children.get(0);
        }
    }

    class Codec2 {
        // Encodes a tree to a single string.
        public String serialize(Node root) {
            if (root == null)
                return "";

            StringBuilder result = new StringBuilder();
            serialize(root, result);
            return result.toString();
        }

        /**
         * The (char) trick in that tree serializer is only safe when:
         *
         * Node values are in 0–65535
         * Child counts are in 0–65535
         *
         * For competitive programming constraints (node values typically ≤ 50000), it's fine.
         * For production code you'd use a proper binary encoding or delimiters instead.
         *
         * @param n
         * @param result
         */
        void serialize(Node n, StringBuilder result) {
            result.append((char) n.val);
            result.append((char) n.children.size());
            for (int i = 0; i < n.children.size(); ++i)
                serialize(n.children.get(i), result);
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if (data == null || data.isEmpty())
                return null;

            return deserialize(data, new int[1]);
        }

        private Node deserialize(String data, int[] index) {
            int value = (int) data.charAt(index[0]++);
            int childCount = (int) data.charAt(index[0]++);
            Node n = new Node(value, new ArrayList<>(childCount));
            for (int i = 0; i < childCount; ++i)
                n.children.add(deserialize(data, index));

            return n;
        }
    }

    void main() {
        Node root = new Node(1, new ArrayList<>());
        root.children.add(new Node(13, new ArrayList<>()));
        root.children.add(new Node(12, new ArrayList<>()));
        root.children.add(new Node(14, new ArrayList<>()));
        root.children.get(0).children.add(new Node(15, new ArrayList<>()));
        root.children.get(0).children.add(new Node(16, new ArrayList<>()));
        var codec = new Codec2();
        System.out.println(codec.deserialize(codec.serialize(root)));
//        System.out.println(codec.deserialize(codec.serialize(new Node())));
        System.out.println(codec.deserialize(codec.serialize(null)));
    }
}
