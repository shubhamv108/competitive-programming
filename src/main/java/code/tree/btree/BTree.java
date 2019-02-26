package code.tree.btree;

import code.tree.BSTNode;
import code.tree.BTNode;

import java.util.*;

public class BTree {

    BSTNode insert(BSTNode node) {
        return node;
    }

    int countLeafNodes(BSTNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        return countLeafNodes(root.left) + countLeafNodes(root.right);
    }

    int heightOfTree(BSTNode root) {
        if(root == null) return 0;
        return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    }

    int sumOfAllNodes(BTNode root)
    {
        if(root == null) return 0;
        return root.data + sumOfAllNodes(root.left) + sumOfAllNodes(root.right);
    }

    BTNode searchNode(BTNode root, int k)
    {
        if(root == null) return null;
        if(root.data == k) return root;
        BTNode a = searchNode(root.left, k);
        if(a != null) return a;
        else return searchNode(root.right, k);
    }

    void preorder(BTNode root) {
        if(root == null) return;
        System.out.printf("%d ", root.data);
        preorder(root.left);
        preorder(root.right);
    }

    void inorder(BTNode root) {
        if(root == null) return;
        inorder(root.left);
        System.out.printf("%d ", root.data);
        inorder(root.right);
    }

    void postorder(BTNode root) {
        if(root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.printf("%d ", root.data);
    }

    void levelorder(BTNode root) {
        if(root == null) return;
        Queue<BTNode> q = new LinkedList<>();
        q.offer(root);
        BTNode tempNode;
        while(!q.isEmpty()) {
            tempNode = q.poll();
            System.out.printf("%d ", tempNode.data);
            if (tempNode.left != null)  q.offer(tempNode.left);
            if (tempNode.right != null) q.offer(tempNode.right);
        }
    }

    public void preorderIteratively(BTNode root) {
        Stack<BTNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                System.out.printf("%d ", root.data);
                stack.push(root);
                root = root.left;
            }
            if (stack.empty()) break;
            root = stack.pop();
            root = root.right;
        }
    }

    public void inorderIteratively(BTNode root) {
        Stack<BTNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.empty()) break;
            root = stack.pop();
            System.out.printf("%d ", root.data);
            root = root.right;
        }
    }

    public void postorderIteratively(BTNode root) {
        if(root == null) return;
        Stack<BTNode> stack = new Stack<>();
        while(true) {
            while(root != null) {
                if(root.right != null) stack.push(root.right);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.right != null && !stack.empty() && stack.peek() == root.right) {
                stack.pop();
                stack.push(root);
                root = root.right;
            } else {
                System.out.printf("%d ", root.data);
                root = null;
            }
            if(stack.empty()) break;
        }
    }

    public void childrenFirstOrderIteratively2(BTNode root) {
        if(root == null) return;
        Queue<BTNode> q = new LinkedList<>();
        Stack<BTNode> stack = new Stack<>();
        q.offer(root);
        while(!q.isEmpty()) {
            root = q.poll();
            stack.push(root);
            if (root.right != null) q.offer(root.right);
            if (root.left != null)  q.offer(root.left);
        }
        while(!stack.empty()) System.out.printf("%d ", stack.pop().data);
    }

    Map<Integer, BTNode> topView;

//    void topViewHelper(BTNode root, int horzDist) {
//        topView.putIfAbsent(horzDist, root);
//        if(root.left != null)  topViewHelper(root.left,  horzDist-1);
//        if(root.right != null) topViewHelper(root.right, horzDist+1);
//    }
//
//    int topView(BTNode root)
//    {
//        topView = new TreeMap<>();
//        topViewHelper(root, 0);
//        for(BTNode node : topView.values()) System.out.printf("%d ", node.data);
//        return 0;
//    }

    class NodeHd {
        BTNode node;
        int hd;
        NodeHd (BTNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    void topView (BTNode root) {
        Queue<NodeHd> q = new LinkedList();
        q.offer(new NodeHd(root, 0));
        Map<Integer, BTNode> topView = new TreeMap<>();
        while (!q.isEmpty()) {
            NodeHd node = q.poll();
            if (!topView.containsKey(node.hd)) topView.put(node.hd, node.node);
            if (node.node.left  != null) q.offer(new NodeHd(node.node.left, node.hd-1));
            if (node.node.right != null) q.offer(new NodeHd(node.node.right,node.hd+1));
        }
        topView.values().forEach(node -> System.out.println(node.data + " "));
    }

    int max_level = 0;

    void leftView (BTNode root, int level) {
        if (null == root) return;
        if (max_level < level) {
            System.out.println(root.data + " ");
            max_level = level;
        }
        if (null != root.left)  { leftView (root.left, level + 1); }
        if (null != root.right) { leftView (root.right,level + 1); }
    }

    void rightView (BTNode root) {
        Queue<BTNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            BTNode node = q.poll();
            int n = q.size();
            for (int i=1; i<=n; i++) {
                if (i == n) System.out.println(node.data);
                node = q.poll();
                if (Objects.nonNull(node.left))  q.offer(node.left);
                if (Objects.nonNull(node.right)) q.offer(node.right);
            }
        }
    }

    List<List<BTNode>> diagonalTraversalCache = new ArrayList<>();

    void diagonalTraversalHelper (BTNode root, int hd)
    {
        if(root == null) return;
        List<BTNode> l = null;
        try {
            l = diagonalTraversalCache.get(hd);
        } catch(IndexOutOfBoundsException e) {
            l = new ArrayList<>();
            diagonalTraversalCache.add(hd, l);
        } finally {
            l.add(root);
        }
        if(root.left  != null) diagonalTraversalHelper(root.left,hd+1);
        if(root.right != null) diagonalTraversalHelper(root.right, hd);
    }

    void diagonalTraversal(BTNode root)
    {
        diagonalTraversalHelper(root, 0);
        diagonalTraversalCache.forEach(l -> {
            l.forEach(n -> System.out.printf("%d ", n.data));
            System.out.printf("\n");
        });
    }

    int ans;

    int diameterHelper(BTNode root) {
        if(root == null) { return 0; }
        int lh = diameterHelper(root.left);
        int rh = diameterHelper(root.right);
        ans = Math.max(ans, lh+rh+1);
        return 1 + Math.max(lh, rh);
    }

    int diameter(BTNode root)
    {
        ans = -1;
        diameterHelper(root);
        return ans;
    }

    void printEachLevelOnNewLine(BTNode root) {
        if(root == null) return;
        Queue<BTNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        while(!q.isEmpty()) {
            root = q.poll();
            if(root != null) {
                System.out.printf("%d ", root.data);
                if(root.left  != null) q.offer(root.left);
                if(root.right != null) q.offer(root.right);
            } else {
                if(!q.isEmpty()) q.offer(null);
                System.out.printf("\n");
            }
        }
    }

    boolean isMirrorSymmetricalHelper(BTNode root1, BTNode root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        if(root1.data != root2.data) return false;
        return isMirrorSymmetricalHelper(root1.left, root2.right)
                && isMirrorSymmetricalHelper(root1.right, root2.left);
    }

    boolean isMirrorSymmetrical(BTNode root)
    {
        if(root == null) return true;
        return isMirrorSymmetricalHelper(root.left, root.right);
    }

    BTNode lca(BTNode root, int a, int b, Boolean isAPresent, Boolean isBPresent)
    {
        if(root == null) return null;
        if(!isAPresent && root.data == a) isAPresent = true;
        if(!isBPresent && root.data == b) isBPresent = true;
        if(!isAPresent || !isBPresent) {
            BTNode temp = lca(root.left, a, b, isAPresent, isBPresent);
            if(temp == null) return lca(root.right, a, b, isAPresent, isBPresent);
            else return temp;
        }
        return root;
    }

    boolean isAPresent, isBPresent;

    BTNode lcaHelper(BTNode root,int a, int b)
    {
        BTNode x = lca(root.left,  a, b);
        BTNode y = lca(root.right, a, b);
        if(root.data == a) { isAPresent = true; return root; }
        if(root.data == b) { isBPresent = true; return root; }
        if(x != null && y != null) return root;
        if(x != null) return x;
        if(y != null) return y;
        return null;
    }

    BTNode lca(BTNode root, int a, int b)
    {
        isAPresent = false;
        isBPresent = false;
        root = lcaHelper(root, a, b);
        if(isAPresent && isBPresent) return root;
        return null;
    }

    public static void spiralOrderTraversal(BSTNode root) {
        if(root == null) return;
        Stack<BSTNode> stack1 = new Stack<>();
        Stack<BSTNode> stack2 = new Stack<>();
        stack1.push(root);
        boolean flag = false;
        while(!stack1.empty() || !stack2.empty()) {
            while(!stack1.empty()) {
                root = stack1.pop();
                System.out.printf("%d ", root.data);
                if(root.left  != null) stack2.push(root.left);
                if(root.right != null) stack2.push(root.right);
            }
            while(!stack2.empty()) {
                root = stack2.pop();
                System.out.printf("%d ", root.data);
                if(root.right != null) stack1.push(root.right);
                if(root.left  != null) stack1.push(root.left);
            }
        }
    }

    int idx = 0;
    public BTNode buildTree(int[] pre, int left, int right, Map<Integer, Integer> inOrderMap) {
        if(left > right) return null;
        BTNode xNode = new BTNode(pre[idx++]);
        if(left == right) return xNode;
        int pos = inOrderMap.get(xNode.data);
        xNode.left  = buildTree(pre, left, pos-1, inOrderMap);
        xNode.right = buildTree(pre, pos+1, right, inOrderMap);
        return xNode;
    }

    public BTNode buildTreeWrap(int[] in, int[] pre) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for(int i=0;i<in.length;i++) inOrderMap.put(in[i], i);
        return buildTree(pre, 0, in.length - 1, inOrderMap);
    }
    int lastIdx;
    private BTNode buildTreeWithPostAndInorder(int[] post, int left, int right, Map<Integer, Integer> inOrderMap) {
        if(left > right) return null;
        BTNode xNode = new BTNode(post[lastIdx--]);
        if(left == right) return xNode;
        int pos = inOrderMap.get(xNode.data);
        xNode.right  = buildTreeWithPostAndInorder(post, pos+1, right, inOrderMap);
        xNode.left   = buildTreeWithPostAndInorder(post, left, pos-1, inOrderMap);
        return xNode;
    }

    public Map<Integer, Integer> buildPositonMapFromArray(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i < arr.length; i++) map.put(arr[i], i);
        return map;
    }

    public BTNode buildTreeFromInOrderAndPostOrder(int[] post, int[] in) {
        this.lastIdx = post.length - 1;
        return  buildTreeWithPostAndInorder(post, 0, post.length - 1, buildPositonMapFromArray(in));
    }

    private BTNode buildTreeWithPreAndPostOrder(int[] pre, int left, int right, Map<Integer, Integer> postOrderMap) {
        if(idx >= pre.length || left > right) return null;
        BTNode xNode = new BTNode(pre[idx++]);
        if(left == right || idx >= pre.length) return xNode;
        int pos = postOrderMap.get(xNode.data);
        xNode.left  = buildTreeWithPreAndPostOrder(pre, left, pos, postOrderMap);
        xNode.right  = buildTreeWithPreAndPostOrder(pre, pos+1, pos - 1, postOrderMap);
        return xNode;
    }

    public BTNode buildTreeFromPreOrderAndPostOrder(int[] pre, int[] post) {
        this.idx = post.length - 1;
        return buildTreeWithPreAndPostOrder(post, 0, post.length - 1, buildPositonMapFromArray(post));
    }

    public BTNode buildTreeWithInOrderAndLevelOrder(int[] levelOrder, int pos, int left, int right, Map<Integer, Integer> inOrderMap) {
        if(left > right) return null;
        BTNode xNode = new BTNode(levelOrder[idx++]);
        if(left == right) return xNode;
        pos = inOrderMap.get(xNode.data);
        xNode.left  = buildTreeWithInOrderAndLevelOrder(levelOrder, pos, left, pos - 1, inOrderMap);
        xNode.right = buildTreeWithInOrderAndLevelOrder(levelOrder, pos, pos + 1, right, inOrderMap);
        return xNode;
    }

    public BTNode buildTreeFromInorderAndLevelOrder(int[] inOrder, int[] levelOrder) {
        return buildTreeWithInOrderAndLevelOrder(levelOrder, 0, 0, levelOrder.length - 1, this.buildPositonMapFromArray(inOrder));
    }

    private int searchInArray(int[] arr, int left, int right, int x) {
        for (int i=left;i<=right;i++)
            if(arr[i] == x) return i;
        return 0;
    }

    private static void getLeftMostRightMostForEachLevel(BTNode root) {
        if (root == null) return;
        Queue<BTNode> q = new LinkedList();
        q.add(root);
        q.add(null);
        int level = 0;
        int a = 1;
        System.out.print("Level " + level + ": ");
        BTNode prev = null;
        while (!q.isEmpty()) {
            BTNode node = q.poll();
            if (node == null) {
                System.out.print(", RightMost: " + prev.data);
                System.out.println();
                if (!q.isEmpty()) {
                    System.out.print("Level: " + (++level));
                    q.add(null);
                }
                a = 1;
            } else {
                if (null != node.left)  q.add(node.left);
                if (null != node.right) q.add(node.right);
                if (a == 1) {
                    System.out.print(" LeftMost: " + node.data);
                    a = 0;
                }
                prev = node;
            }
        }
    }

    public static void main(String[] args) {
        BSTNode tree = new BSTNode(15);
        tree.left = new BSTNode(10);
        tree.right = new BSTNode(20);
        tree.left.left = new BSTNode(8);
        tree.left.right = new BSTNode(12);
        tree.right.left = new BSTNode(16);
        tree.right.right = new BSTNode(25);

        spiralOrderTraversal(tree);
    }




}


