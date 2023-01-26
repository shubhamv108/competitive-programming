package code.shubham.trees.bsttree.bst;

public class BinarySearchTreeValidator {

    private BinarySearchTree.Node prev = null;
    public boolean isValid(BinarySearchTree.Node node) {
        if (node == null)
            return true;

        if (!isValid(node.left))
            return false;

        if (prev != null && prev.val > node.val)
            return false;

        prev =  node;
        return isValid(node.right);
    }
}
