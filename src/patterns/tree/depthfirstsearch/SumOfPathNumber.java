package patterns.tree.depthfirstsearch;

import patterns.tree.TreeNode;

public class SumOfPathNumber {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println(findSumOfPathNumber(root));

    }
    private static int findSumOfPathNumber(TreeNode root){
        return findSumOfPathNumber(root, 0);
    }
    private static int findSumOfPathNumber(TreeNode root, int pathSum){
        if(root == null) return 0;
        pathSum = 10 * pathSum + root.value;

        if(root.left == null && root.right == null) return pathSum;

        return findSumOfPathNumber(root.left, pathSum)+findSumOfPathNumber(root.right, pathSum);
    }

}
