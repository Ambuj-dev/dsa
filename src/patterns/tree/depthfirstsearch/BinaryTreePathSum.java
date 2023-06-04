package patterns.tree.depthfirstsearch;

import patterns.tree.TreeNode;

public class BinaryTreePathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path:" + hasPath(root, 23));
        System.out.println("Tree has path:" + hasPath(root, 16));
        System.out.println("Tree has path:" + hasPath(root, 18));
    }

    private static boolean hasPath(TreeNode root, int sum){
        if(root == null) return false;
        // if the current node is leaf and its value is equal to the sum, we've found a path
        if(root.value == sum && root.left == null && root.right == null) return true;

        // recursively call to traverse the left and right sub-tree
        // return true if any of the two recursive call return true
        return hasPath(root.left, sum - root.value) || hasPath(root.right, sum - root.value);
    }
}
