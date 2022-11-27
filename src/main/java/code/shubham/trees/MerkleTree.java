package code.shubham.trees;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * ToDo: Add branching, Add tests
 */
public class MerkleTree<Data> {

    private Node<Data> root;

    private MerkleTree() throws NoSuchAlgorithmException {
        this.root = new Node<Data>((Data) "GenesisNodeData", "");
    }

    void commit(Node<Data> parent, Data data) throws NoSuchAlgorithmException {
        Node<Data> node = new Node<>(data, parent.hash);
        node.children.add(node);
    }

    private class Node<Data> {
        String timestamp;
        Data data;
        String parentHash;
        String hash;
        LinkedList<Node<Data>> children = new LinkedList<>();
        Node(Data data, String parentHash) throws NoSuchAlgorithmException {
            this.timestamp = String.valueOf(System.nanoTime());
            this.data = data;
            this.parentHash = parentHash;
            this.hash = SHA.generate(this.timestamp + this.data + this.parentHash);
        }
    }

    private static class SHA {
        public static String generate(String message) throws NoSuchAlgorithmException {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                messageDigest.update(message.getBytes());
                byte[] b = messageDigest.digest();
                return HexUtil.convert(b);
            } catch (Exception ex) {
                throw ex;
            }
        }
    }
    private static class HexUtil {
        public static String convert(byte[] data) {
            StringBuilder hexadecimalBuilder = new StringBuilder();
            for (int byteIndex = 0; byteIndex < data.length; byteIndex++) {
                hexadecimalBuilder.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));
            }
            return hexadecimalBuilder.toString();
        }
    }

}
