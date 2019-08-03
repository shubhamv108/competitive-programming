package code.tree;

import static java.util.Objects.isNull;

class RBTNode {
    int data;
    RBTNode left, right, parent;
    boolean color; //true for red, false for black //black by default
    RBTNode (int d) {
        this.data = d;
        this.left = this.right = this.parent = null;
    }
    RBTNode (int d, RBTNode parent) {
        this.data = d;
        this.left = this.right = null;
        this.parent = parent;
    }
}

interface RBTree {

    RBTNode add(int data);
    RBTNode remove(int data);

    default RBTNode sibling (RBTNode node) {
        if (node.parent.left == node) return node.parent.right;
        return node.parent.left;
    }
    default RBTNode parent (RBTNode node) {
        return node.parent;
    }
    default RBTNode uncle (RBTNode node) {
        if (grandParent(node).left == node.parent) return grandParent(node).right;
        return grandParent(node).left;
    }
    default RBTNode grandParent (RBTNode node) {
        return node.parent.parent;
    }
    default boolean isRoot (RBTNode node) {
        return node.parent == null;
    }
    default boolean isLeftChild  (RBTNode node) {
        return node.parent.left == node;
    }
    default boolean isRightChild (RBTNode node) {
        return node.parent.right == node;
    }
    default boolean isParentLeftChild (RBTNode node) {
        return grandParent(node).left == parent(node);
    }
    default boolean isParentRightChild (RBTNode node) {
        return grandParent(node).right == parent(node);
    }
    default boolean isRed (RBTNode node) {
        return node.color;
    }
    default boolean isBlack (RBTNode node) {
        return isNull(node) || !isRed(node);
    }
    default boolean isParentRed (RBTNode node) {
        return isRed(node.parent);
    }
    default boolean isParentBlack (RBTNode node) {
        return isBlack(node.parent);
    }
    default boolean isUncleRed (RBTNode node) {
        return isRed(uncle(node));
    }
    default boolean isUncleBlack (RBTNode node) {
        return isBlack(uncle(node));
    }
    default boolean isUncleNull (RBTNode node) {
        return isNull(uncle(node));
    }
    default boolean color (RBTNode node) {
        return node.color;
    }
    default void color (RBTNode node, boolean color) {
        node.color = color;
    }
    default void colorRed (RBTNode node) {
        color(node, true);
    }
    default void colorBlack (RBTNode node) {
        color(node, false);
    }
    default void swapColors (RBTNode a, RBTNode b) {
        boolean temp = color(a);
        a.color = b.color;
        b.color = temp;
    }
    default boolean isLLCase(RBTNode node) {
        return isParentLeftChild(node)  && isLeftChild(node);
    }
    default boolean isLRCase(RBTNode node) {
        return isParentLeftChild(node)  && isRightChild(node);
    }
    default boolean isRRCase(RBTNode node) {
        return isParentRightChild(node) && isRightChild(node);
    }
    default boolean isRLCase(RBTNode node) {
        return isParentRightChild(node) && isLeftChild(node);
    }

    default void rightRotate (RBTNode node) {
        RBTNode left = node.left;
        left.parent = node.parent;
        node.left = left.right;
        left.right = node;
        node.parent = left;
    }
    default void leftRotate (RBTNode node) {
        RBTNode right = node.right;
        right.parent = node.parent;
        node.right = right.left;
        right.left = node;
        node.parent = right;
    }
    default void llCase (RBTNode node) {
        rightRotate (grandParent(node));
        swapColors(parent(node), sibling(node));
    }
    default void lrCase(RBTNode node) {
        leftRotate (parent(node));
        llCase (node.left);
    }
    default void rrCase(RBTNode node) {
        leftRotate (grandParent(node));
        swapColors(parent(node), sibling(node));
    }
    default void rlCase(RBTNode node) {
        rightRotate(parent(node));
        rrCase(node.right);
    }

}

public class RBTreeImpl implements RBTree {

    private RBTNode root;
    private long blackHeight;

    @Override
    public RBTNode add(int data) {
        return insert(data);
    }

    @Override
    public RBTNode remove(int data) {
        return delete(data);
    }

    private RBTNode bstInsert (RBTNode root, int d) {
        if (null == root)  return new RBTNode(d);
        if (d < root.data) {
            RBTNode x = bstInsert(root.left, d);
            x.parent = root;
            return x;
        }
        if (d > root.data) {
            RBTNode x = bstInsert(root.right, d);
            x.parent = root;
            return x;
        }
        return root;
    }

    private RBTNode insert (int d) {
        RBTNode x = bstInsert(root, d);

        while (isRoot(x) || (!isParentBlack(x) || !isRoot(x))) {
            if (isRoot(x)) {
                colorRed(x);
                break;
            } else {
                if (isUncleRed(x)) {
                    colorBlack(parent(x));
                    colorBlack(uncle(x));
                    colorRed(grandParent(x));
                    x = grandParent(x);
                }
            }
        }

        if (isUncleBlack(x)) {
            if (isLLCase(x)) {
                llCase(x);
            } else if (isLRCase(x)) {
                lrCase(x);
            } else if (isRRCase(x)) {
                rrCase(x);
            } else if (isRLCase(x)) {
                rlCase(x);
            }
        }
        return x;
    }

    private RBTNode delete (int data) {
        return null;
    }

}
