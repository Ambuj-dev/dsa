package tree.bst;

import tree.SerializeDeserializeABinaryTree;
import tree.TreeNode;


public class MaxSumBST {
int max = 0;

    private NodeValue largestBSTSubtreeHelper(TreeNode root) {
        // An empty tree is a BST of size 0.
        if (root == null) {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        // Get values from left and right subtree of current tree.
        NodeValue left = largestBSTSubtreeHelper(root.left);
        NodeValue right = largestBSTSubtreeHelper(root.right);

        // Current node is greater than max in left AND smaller than min in right, it is a BST.
        if (left.maxNode < root.val && root.val < right.minNode) {
            // It is a BST.
            max = Math.max(max,root.val + left.maxSize + right.maxSize);
            return new NodeValue(Math.min(root.val, left.minNode), Math.max(root.val, right.maxNode),
                    left.maxSize + right.maxSize+ root.val);
        }

        // Otherwise, return [-inf, inf] so that parent can't be valid BST
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE,
                Math.max(left.maxSize,right.maxSize));
    }

    public int largestBSTSubtree(TreeNode root) {
         largestBSTSubtreeHelper(root);
         return max;
    }

    public static void main(String[] args) {
        String str = "1 4 3 2 4 2 5 n n n n n n 4 6";
        TreeNode node = SerializeDeserializeABinaryTree.deserialize(str);
        System.out.println(node);
        MaxSumBST maxSumBST = new MaxSumBST();
        System.out.println(maxSumBST.largestBSTSubtree(node));
    }



}

