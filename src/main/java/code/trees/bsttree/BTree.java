package code.trees.bsttree;

import code.tree.BSTNode;
import code.trees.BTNode;
import code.trees.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

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

    int sumOfAllNodes(BTNode root) {
        if(root == null) return 0;
        return root.data + sumOfAllNodes(root.left) + sumOfAllNodes(root.right);
    }

    BTNode searchNode(BTNode root, int k) {
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

    private static BSTNode previous = null;

    public static boolean isBST (BSTNode root) {
        if (root != null) {
         if (!isBST(root.left)) return false;
         if (previous != null && previous.data >= root.data) return false;
         previous = root;
         if (!isBST(root.right)) return false;
        }
        return true;
    }

    private TreeNode prev = null;
    public int isValidBST(TreeNode A) {
        if (A != null) {
            if (isValidBST(A.left) == 0) return 0;
            if (prev != null && prev.val >= A.val ) return 0;
            prev = A;
            if (isValidBST(A.right) == 0) return 0;
        }
        return 1;
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

    void diagonalTraversalHelper (BTNode root, int hd) {
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

    void diagonalTraversal(BTNode root) {
        diagonalTraversalHelper(root, 0);
        diagonalTraversalCache.forEach(l -> {
            l.forEach(n -> System.out.printf("%d ", n.data));
            System.out.printf("\n");
        });
    }

    private int diameter;

    int diameterHelper(BTNode root) {
        if(root == null) { return 0; }
        int lh = diameterHelper(root.left);
        int rh = diameterHelper(root.right);
        diameter = Math.max(diameter, lh+rh+1);
        return 1 + Math.max(lh, rh);
    }

    int diameter(BTNode root) {
        diameter = -1;
        diameterHelper(root);
        return diameter;
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

    BTNode lcaHelper(BTNode root, int a, int b) {
        if (root == null) return null;
        BTNode x = lca(root.left,  a, b);
        BTNode y = lca(root.right, a, b);
        if(root.data == a) { isAPresent = true; return root; }
        if(root.data == b) { isBPresent = true; return root; }
        if (root.data == a && root.data == b) { isBPresent = true; isCPresent = true; return root; }
        if(x != null && y != null) return root;
        return x != null ? x : y;
    }

    BTNode lca(BTNode root, int a, int b) {
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

    private int lastIdx;
    private BTNode buildTreeWithPostAndInorder(int[] post, int left, int right, Map<Integer, Integer> inOrderMap) {
        if(left > right) return null;
        BTNode xNode = new BTNode(post[lastIdx--]);
        if(left == right) return xNode;
        int pos = inOrderMap.get(xNode.data);
        xNode.right  = buildTreeWithPostAndInorder(post, pos+1, right, inOrderMap);
        xNode.left   = buildTreeWithPostAndInorder(post, left, pos-1, inOrderMap);
        return xNode;
    }

    public Map<Integer, Integer> buildPositionMapFromArray(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i < arr.length; i++) map.put(arr[i], i);
        return map;
    }

    public BTNode buildTreeFromInOrderAndPostOrder(int[] post, int[] in) {
        this.lastIdx = post.length - 1;
        return  buildTreeWithPostAndInorder(post, 0, post.length - 1, buildPositionMapFromArray(in));
    }

    private BTNode buildTreeWithPreAndPostOrder(int[] pre, int left, int right, Map<Integer, Integer> postOrderMap) {
        if(idx >= pre.length || left > right) return null;
        BTNode xNode = new BTNode(pre[idx++]);
        if(left == right || idx >= pre.length) return xNode;
        int pos = postOrderMap.get(xNode.data);
        xNode.left   = buildTreeWithPreAndPostOrder(pre, left, pos, postOrderMap);
        xNode.right  = buildTreeWithPreAndPostOrder(pre, pos+1, pos - 1, postOrderMap);
        return xNode;
    }

    public BTNode buildTreeFromPreOrderAndPostOrder(int[] pre, int[] post) {
        this.idx = post.length - 1;
        return buildTreeWithPreAndPostOrder(post, 0, post.length - 1, buildPositionMapFromArray(post));
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
        return buildTreeWithInOrderAndLevelOrder(levelOrder, 0, 0, levelOrder.length - 1, this.buildPositionMapFromArray(inOrder));
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

//    public static void main(String[] args) {
//        BSTNode tree     = new BSTNode(15);
//        tree.left        = new BSTNode(10);
//        tree.right       = new BSTNode(20);
//        tree.left.left   = new BSTNode(8);
//        tree.left.right  = new BSTNode(12);
//        tree.right.left  = new BSTNode(16);
//        tree.right.right = new BSTNode(25);
//
//        spiralOrderTraversal(tree);
//    }

    public ArrayList<Integer> inorderTraversal (TreeNode A) {
        ArrayList<Integer> a = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        while (true) {
            while (A != null) {
                s.push(A);
                A = A.left;
            }
            if (s.isEmpty()) break;
            A = s.pop();
            a.add(A.val);
            A = A.right;
        }
        return a;
    }

    class Pair {
        int hd;
        TreeNode node;
        Pair(int hd, TreeNode node) {
            this.hd = hd;
            this.node = node;
        }
    }

    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        if (A == null) return a;
        Map<Integer, ArrayList<Integer>> m = new HashMap<>();
        int l = 0;
        int h = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, A));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            l = Math.min(p.hd, l);
            h = Math.max(p.hd, h);
            if (m.containsKey(p.hd)) {
                m.get(p.hd).add(p.node.val);
            } else {
                ArrayList<Integer> aa = new ArrayList<>();
                aa.add(p.node.val);
                m.put(p.hd, aa);
            }
            if (p.node.left  != null) q.add(new Pair(p.hd - 1, p.node.left ));
            if (p.node.right != null) q.add(new Pair(p.hd + 1, p.node.right));
        }
        for (int i = l; i <= h; i++) a.add(m.get(i));
        return a;
    }

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(A);
        TreeNode t;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            ArrayList<Integer> aa = new ArrayList<>();
            while (!s1.isEmpty()) {
                t = s1.pop();
                aa.add(t.val);
                if (t.left  != null) s2.push(t.left);
                if (t.right != null) s2.push(t.right);
            }
            if (aa.size() > 0) a.add(aa);
            aa = new ArrayList<>();
            while (!s2.isEmpty()) {
                t = s2.pop();
                aa.add(t.val);
                if (t.right != null) s1.push(t.right);
                if (t.left  != null) s1.push(t.left);
            }
            if (aa.size() > 0) a.add(aa);
        }
        return a;
    }

    public TreeNode getSuccessor (TreeNode a, int b) {
        if (a == null) return null;
        TreeNode n = getSuccessor(a.left, b);
        if (n != null) return n;
        if (prev != null && prev.val == b) return a;
        prev = a;
        n = getSuccessor(a.right, b);
        if (n != null) return n;
        return null;
    }

    public TreeNode getSuccessor1 (TreeNode a, int b) {
        TreeNode prev = null;
        Stack<TreeNode> s = new Stack<>();
        if (a == null) return null;
        while (true) {
            while (a != null) {
                s.push(a);
                a = a.left;
            }
            if (s.isEmpty()) break;
            a = s.pop();
            if (prev != null && prev.val == b) break;
            prev = a;
            a = a.right;
        }
        return a;
    }

    public int hasPathSum(TreeNode A, int B) {
        if (A == null) return 0;
        if (A.val == B && A.left == null && A.right == null) return 1;
        return Math.max (hasPathSum(A.left, B - A.val),
                         hasPathSum(A.right,B - A.val));
    }

//    public static void main(String[] args) {
//        TreeNode t   = new TreeNode(5);
//        t.left       = new TreeNode(1000);
//        t.left.left  = new TreeNode(-1);
//        t.left.right = new TreeNode(-1);
//        t.right      = new TreeNode(200);
//        t.right.left = new TreeNode(-1);
//        System.out.println(new BTree().hasPathSum(t, 1004));
//    }

    void pathSum (TreeNode A, int B, ArrayList<Integer> a, ArrayList<ArrayList<Integer>> aa) {
        if (A == null) return;
        a.add(A.val);
        if(A.left == null && A.right == null) {
            if (A.val == B) aa.add(new ArrayList<>(a));
        }
        pathSum (A.left,  B - A.val, a, aa);
        pathSum (A.right, B - A.val, a, aa);
        a.remove(a.size() - 1);
    }

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode A, int B) {
        ArrayList<ArrayList<Integer>> aa = new ArrayList<>();
        if (A == null) return null;
        pathSum(A, B, new ArrayList<>(), aa);
        return aa;
    }

    public int isSameTree(TreeNode A, TreeNode B) {
        if (A == B) return 1;
        if (A == null && B == null) return 1;
        if (A == null || B == null) return 0;
        if (A.val != B.val) return 0;
        return Math.min(isSameTree(A.left,  B.left),
                        isSameTree(A.right, B.right));
    }

    public int isSymmetric (TreeNode A, TreeNode B) {
        if (A == null && B == null) return 1;
        if (A == null || B == null) return 0;
        if (A.val != B.val)         return 0;
        return Math.min(isSymmetric (A.left,  B.right),
                isSymmetric (A.right, B.left));
    }
    public int isSymmetric(TreeNode A) {
        if (A == null) return 1;
        return isSymmetric(A.left, A.right);
    }

    private int isBalanced (TreeNode A, int h) {
        if (A == null) return h;
        int lh = isBalanced(A.left,  h+1);
        if (lh == -1) return 0;
        int rh = isBalanced(A.right, h+1);
        if (rh == -1) return 0;
        if (Math.abs(lh - rh) > 1) return 0;
        return Math.max(lh, rh);
    }

    public int isBalanced(TreeNode A) {
        if (A == null) return 1;
        return isBalanced(A, 1) == 0 ? 0 : 1;
    }

    private TreeNode sortedArrayToBST (final List<Integer> a, int b, int c) {
        if (b > c) return null;
        int mid = (b + c) / 2;
        TreeNode A = new TreeNode (a.get(mid));
        A.left  = sortedArrayToBST (a, b, mid - 1);
        A.right = sortedArrayToBST (a, mid + 1, c);
        return A;
    }

    public TreeNode sortedArrayToBST(final List<Integer> a) {
        if (a.size() == 0)  return null;
        return sortedArrayToBST (a, 0, a.size() -1);
    }

    private boolean isCPresent;

    private TreeNode lcaUtil (TreeNode A, int B, int C) {
        if (A == null) return null;
        TreeNode l = lcaUtil (A.left,  B, C);
        TreeNode r = lcaUtil (A.right, B, C);
        if (A.val == B && A.val == C) { isBPresent = true; isCPresent = true; return A; }
        if (A.val == B) { isBPresent = true; return A; }
        if (A.val == C) { isCPresent = true; return A; }
        if(l != null && r != null) return A;
        return l != null ? l : r;
    }

    public int lca (TreeNode A, int B, int C) {
        TreeNode lca = lcaUtil (A, B, C);
        if (lca != null && isBPresent && isCPresent) return lca.val;
        return -1;
    }

    public int maxDepth(TreeNode A) {
        if (A == null) return 0;
        return 1 + Math.max(maxDepth(A.left), maxDepth(A.right));
    }

    int min = Integer.MAX_VALUE;
    private void minDepth(TreeNode A, int h) {
        if (A == null) return;
        if (A.left == null && A.right == null) { min = Math.min(h, min); };
        minDepth(A.left, h + 1);
        minDepth(A.right,h + 1);
    }

    public int minDepth(TreeNode A) {
        minDepth (A, 1);
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private int sum;

    private void sumNumbers (TreeNode A, int s) {
        if (A == null) return;
        s = (((s * 10) % 1003) + A.val) % 1003;
        if (A.left == null && A.right == null) {
            sum = (sum + s) % 1003;
            return;
        }
        sumNumbers (A.left,  s);
        sumNumbers (A.right, s);
    }

    public int sumNumbers(TreeNode A) {
        sumNumbers (A, 0);
        return sum % 1003;
    }

    public TreeNode invertTree(TreeNode A) {
        if (A == null) return null;
        if (A.right == null && A.left == null) return A;
        TreeNode temp = A.right;
        A.right = A.left;
        A.left = temp;
        invertTree(A.left);
        invertTree(A.right);
        return A;
    }

    public int kthsmallest(TreeNode A, int B) {
        int c = 0;
        Stack<TreeNode> s = new Stack<>();
        while (true) {
            while (A != null) {
                s.push(A);
                A = A.left;
            }
            if (s.isEmpty()) break;
            A = s.pop();
            if (++c == B) return A.val;
            A = A.right;
        }
        return -1;
    }

    public TreeNode flatten (TreeNode a) {
        TreeNode head = a;
        Stack<TreeNode> s = new Stack<>();
        while (a != null || !s.isEmpty()) {
            if (a.right != null) s.push(a.right);
            a.right = a.left;
            a.left = null;
            if (a.right == null && !s.isEmpty()) a.right = s.pop();
            a = a.right;
        }
        return head;
    }

    public TreeNode flatten2 (TreeNode a) {
        Stack<TreeNode> s = new Stack<>();
        s.push(a);
        while(!s.isEmpty()){
            TreeNode node  = s.pop();
            if(node.right != null) s.push(node.right);
            if(node.left  != null) s.push(node.left);
            node.left  = null;
            node.right = s.isEmpty() ? null : s.peek();
        }
        return a;
    }

    private Map<Integer, Integer> m = new HashMap<>();

    public TreeNode buildTree1 (ArrayList<Integer> B, int l , int r) {
        if (l > r) return null;
        TreeNode x = new TreeNode(B.get(idx--));
        if (l == r) return x;
        int p = m.get(x.val);
        x.right = buildTree1 (B, p + 1, r);
        x.left  = buildTree1 (B, l, p - 1);
        return x;
    }

    public TreeNode buildTreeFromInOrderAndPostOrder (ArrayList<Integer> A, ArrayList<Integer> B) {
        for ( int i = 0; i < A.size(); i++ ) m.put(A.get(i), i);
        idx = A.size() - 1;
        return buildTree1(B, 0, A.size() - 1);
    }

    public TreeNode buildTreePreOrderAndInOrder (ArrayList<Integer> A, ArrayList<Integer> B) {
        for (int i=0; i<B.size();i++) m.put(B.get(i), i);
        return buildTree2(A, 0, B.size() - 1);
    }

    private TreeNode buildTree2 (ArrayList<Integer> A, int l, int r) {
        if (l > r) return null;
        TreeNode x = new TreeNode(A.get(idx++));
        if (l == r) return x;
        int p = m.get(x.val);
        x.left  = buildTree2 (A, l, p - 1);
        x.right = buildTree2 (A, p + 1, r);
        return x;
    }

    public void connect(code.tree.bsttree.TreeLinkNode root) {
        if (root == null) return;
        code.tree.bsttree.TreeLinkNode a;
        Queue<code.tree.bsttree.TreeLinkNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            a = q.poll();
            int n = q.size();
            if (a.left  != null) q.add(a.left);
            if (a.right != null) q.add(a.right);
            for (int i = 0; i < n; i++) {
                a.next = q.poll();
                a = a.next;
                if (a.left  != null) q.add(a.left);
                if (a.right != null) q.add(a.right);
            }
        }
    }


    private ArrayList<TreeNode> constructTrees (int start, int end) {
        ArrayList<TreeNode> list = new ArrayList<>();
        if  (start > end) {
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            ArrayList<TreeNode> l = constructTrees(start, i-1);
            ArrayList<TreeNode> r = constructTrees(i+1,   end);
            for (int j = 0; j < l.size(); j++) {
                TreeNode left = l.get(j);
                for (int k=0; k < r.size(); k++) {
                    TreeNode right = r.get(k);
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    list.add(node);
                }
            }
        }
        return list;
    }

    /**
     * Genetrate All Unique BSTs
     *
     * @param a
     * @return
     */
    public ArrayList<TreeNode> generateTrees(int a) {
        return constructTrees(1, a);
    }


    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList();
        q.offer(A);
        TreeNode t;
        while (!q.isEmpty()) {
            int n = q.size();
            ArrayList<Integer> aa = new ArrayList<>();
            for (int i=0;i<n;i++) {
                t = q.poll();
                aa.add(t.val);
                if (t.left  != null) q.offer(t.left);
                if (t.right != null) q.offer(t.right);
            }
            a.add(aa);
        }
        return a;
    }

}


