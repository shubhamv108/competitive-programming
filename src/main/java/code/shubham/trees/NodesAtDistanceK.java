package code.shubham.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NodesAtDistanceK {

    public class Solution {

        private List<Integer> result;
        private TreeNode TARGET;
        private int DISTANCE = 0;

        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            result = new ArrayList<>();
            this.TARGET = target;
            this.DISTANCE = K;
            if (root == null ||
                    (root.left == null && root.right == null)) return result;

            this.generateAllNodesAtDistanceK(root);

            return result;

        }

        private int generateAllNodesAtDistanceK(TreeNode node) {
            if (node == null)
                return -1;
            else if (node == TARGET) {
                generateAllChildNodesAtDistanceK(node, 0);
                return 1;
            } else {
                int l = generateAllNodesAtDistanceK(node.left);
                int r = generateAllNodesAtDistanceK(node.right);
                if (l != -1) {
                    if (l == DISTANCE) result.add(node.val);
                    else generateAllChildNodesAtDistanceK(node.right, l + 1);
                    return l + 1;
                } else if (r != -1) {
                    if (r == DISTANCE) result.add(node.val);
                    else generateAllChildNodesAtDistanceK(node.left, r + 1);
                    return r + 1;
                }
            }
            return -1;
        }

        private void generateAllChildNodesAtDistanceK(TreeNode node, int K) {
            if (node == null) return;
            if (K == DISTANCE) result.add(node.val);
            else {
                generateAllChildNodesAtDistanceK(node.left,  K+1);
                generateAllChildNodesAtDistanceK(node.right, K+1);
            }
        }

    }

    public class Solution2 {

        private Map<TreeNode, TreeNode> parents = new HashMap<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            if (target == null) return null;
            if (K == 0) return Arrays.asList(target.val);
            mapParents(root, null);
            return getNodesAtDistanceK(target, K);
        }

        private void mapParents(final TreeNode child, TreeNode parent) {
            if (child == null) return;
            parents.put(child, parent);
            mapParents(child.left, child);
            mapParents(child.right, child);
        }

        private List<Integer> getNodesAtDistanceK(TreeNode target, int distance) {
            List<Integer> result = new ArrayList<>();
            Set<TreeNode> s = new HashSet<>();
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(target);
            int level = 0;
            int size;
            while (!q.isEmpty()) {
                level++;
                size = q.size();
                for (int i = 0; i < size; i++) {
                    resolveNode(q.peek().left, q, result, distance, level, s);
                    resolveNode(q.peek().right, q, result, distance, level, s);
                    resolveNode(parents.get(q.peek()), q, result, distance, level, s);
                    s.add(q.poll());
                }
                if (level == distance)
                    break;
            }
            return result;
        }

        private void resolveNode(TreeNode node,
                                 Queue<TreeNode> q,
                                 List<Integer> result,
                                 int K,
                                 int level,
                                 Set<TreeNode> s) {
            if (node != null && !s.contains(node)) {
                if (level == K)
                    result.add(node.val);
                else
                    q.offer(node);
            }
        }

    }

    class Solution3 {
        Map<TreeNode, Integer> map = new HashMap<>();
        List<Integer> result = new LinkedList<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            find(root, target);
            dfs(root, K, map.get(root));
            return result;
        }

        private int find(TreeNode root, TreeNode target) {
            if (root == null) return -1;
            if (root == target) {
                map.put(root, 0);
                return 0;
            }
            int left = find(root.left, target);
            if (left >= 0) {
                map.put(root, left + 1);
                return left + 1;
            }
            int right = find(root.right, target);
            if (right >= 0) {
                map.put(root, right + 1);
                return right + 1;
            }
            return -1;
        }

        private void dfs(TreeNode root, int K, int length) {
            if (root == null) return;
            if (map.containsKey(root)) length = map.get(root);
            if (length == K) result.add(root.val);
            dfs(root.left, K, length + 1);
            dfs(root.right, K, length + 1);
        }
    }

//    public static void main(String[] args) {
//        TreeNode root   = TreeUtils.getTreeFromArray(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 });
//        TreeNode target = TreeUtils.getNodeWithValue(root, 5);
//        new NodesAtDistanceK()
//                .new Solution2()
//                .distanceK(root, target, 2)
//                .forEach(System.out::println);
//    }

    public static void main(String[] args) {
        TreeNode root   = TreeUtils.getTreeFromArray(new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 });
        TreeNode target = TreeUtils.getNodeWithValue(root, 5);
        new NodesAtDistanceK()
                .new Solution()
                .distanceK(root, target, 2)
                .forEach(System.out::println);
    }
}