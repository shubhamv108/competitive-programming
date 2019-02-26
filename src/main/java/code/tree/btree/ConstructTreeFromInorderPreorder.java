package code.tree.btree;

import code.tree.BTNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConstructTreeFromInorderPreorder {
    /**
     * Build tree using Preorder and Inorder
     * @param args
     * @throws IOException
     */
//    public static void main(String[] args) {
//        BTree tree = new BTree();
//        char in[] = new char[] { 'D', 'B', 'E', 'A', 'F', 'C' };
//        char pre[] = new char[] { 'A', 'B', 'D', 'E', 'C', 'F' };
//        int len = in.length;
//        BTNode root = tree.buildTree(in, pre, 0, len - 1);
//
//        // building the tree by printing inorder traversal
//        System.out.println("Inorder traversal of constructed tree is : ");
//        tree.printEachLevelOnNewLine(root);
//    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.charAt(0) - '0';
        String[] line = br.readLine().split(" ");
        int[] in = new int[n];
        for(int i=0;i<n;i++)  in[i] = Integer.parseInt(line[i]);
        line = br.readLine().split(" ");
        int[] level = new int[n];
        for(int i=0;i<n;i++) level[i] = Integer.parseInt(line[i]);
        BTree tree = new BTree();
        BTNode root = tree.buildTreeFromInorderAndLevelOrder(in, level);

        // building the tree by printing inorder traversal
        System.out.print("PRE: ");    tree.preorderIteratively(root);
        System.out.print("\nIN: ");   tree.inorderIteratively(root);
        System.out.print("\nPOST: "); tree.postorderIteratively(root);
    }
}
