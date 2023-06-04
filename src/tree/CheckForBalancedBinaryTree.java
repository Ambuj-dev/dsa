package tree;

public class CheckForBalancedBinaryTree {

    public static boolean isBalanced(TreeNode root) {
        return dfsHeight (root) != -1;
    }
    static int dfsHeight (TreeNode root) {
        if (root == null) return 0;

        int leftHeight = dfsHeight (root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = dfsHeight (root.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1)  return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(12);
        node.left = new TreeNode(2);
        node.left.right = new TreeNode(10);
        node.right = new TreeNode(5);
        node.right.left = new TreeNode(3);
        node.right.left.left = new TreeNode(8);
        node.right.left.right = new TreeNode(11);
        node.right.right = new TreeNode(4);
        System.out.println(isBalanced(node));
    }
}
