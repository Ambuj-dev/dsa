package tree;

import patterns.tree.TreeNode;

public class MaximumPathSum {
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Maximum Path Sum: "+maxPathSum(root));
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Maximum Path Sum: "+maxPathSum(root));
        TreeNode root1 = new TreeNode(20);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(-20);
        root1.left.left = new TreeNode(5);
        root1.right.left = new TreeNode(5);
        root1.right.right = new TreeNode(16);
        System.out.println("Maximum Path Sum: "+maxPathSum(root1));

    }
    public static int maxPathSum(TreeNode root) {
        int[] maxvalue = new int[1];
        maxPathSumRecursive(root, maxvalue);
        return maxvalue[0];
    }

    public static int maxPathSumRecursive(TreeNode root, int[] maxValue){
        if(root == null) return 0;
        int leftSum = Math.max(maxPathSumRecursive(root.left, maxValue), 0);
        int rightSum = Math.max(maxPathSumRecursive(root.right, maxValue), 0);
        maxValue[0] = Math.max(maxValue[0], leftSum+rightSum+root.value);
        return Math.max(leftSum, rightSum)+root.value;
    }
}
