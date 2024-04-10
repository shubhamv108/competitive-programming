package code.shubham.trees.binarytree;

public class InorderSuccessorInBSTII {
      class ParentTreeNode {
          public int val;
          public ParentTreeNode parent, left, right;
      }

    public class Solution {
        /**
         * @param node: random node in binary search tree
         * @return: the inorder successor of current node
         */
        public ParentTreeNode inorderSuccessor(ParentTreeNode node) {
            if (node.right != null) {
                ParentTreeNode temp = node.right;
                while (temp.left != null)
                    temp = temp.left;
                return temp;
            } else {
                ParentTreeNode par = node.parent;
                while(par != null && par.left != node){
                    node = par;
                    par = par.parent;
                }
                return par;
            }
        }
    }
}
