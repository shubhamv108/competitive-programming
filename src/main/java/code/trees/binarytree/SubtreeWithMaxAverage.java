package code.trees.binarytree;

public class SubtreeWithMaxAverage {
      public class TreeNode {
          public int val;
          public TreeNode left, right;
          public TreeNode(int val) {
              this.val = val;
              this.left = this.right = null;
          }
      }

    public class Solution {
        Double maxAverage = null;
        TreeNode result = null;
        public TreeNode findSubtree2(TreeNode root) {
            recurse(root);
            return result;
        }

        Average recurse(TreeNode node) {
            if (node == null) return new Average(0, 0);
            Average left = recurse(node.left);
            Average right = recurse(node.right);
            Average average = this.add(left, right);
            average.sum += node.val;
            average.count += 1;
            double avg = average.val();
            if (maxAverage == null || avg > maxAverage) {
                maxAverage = avg;
                result = node;
            }
            return average;
        }

        Average add(Average average1, Average average2) {
            return new Average(
                    average1.sum + average2.sum,
                    average1.count + average2.count
            );
        }

        private class Average {
            int sum;
            int count;
            Average(int sum, int count) {
                this.sum = sum;
                this.count = count;
            }
            double val() { return (double)sum / count; }
            public String toString() {
                return String.format("Sum=%s,Count=%s", sum, count);
            }
        }
    }
}
