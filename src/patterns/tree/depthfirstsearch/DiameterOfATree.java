package patterns.tree.depthfirstsearch;

import patterns.tree.TreeNode;

public class DiameterOfATree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: "+diameterOfBinaryTree(root));
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Tree Diameter: "+diameterOfBinaryTree(root));

    }
    static int ans = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return ans;
    }

    public static int height(TreeNode root){
        if(root == null) return 0;
        int lh = height(root.left);
        int rh = height(root.right);
        ans = Math.max(ans, lh+rh);
        return Math.max(lh,rh)+1;
    }
}
