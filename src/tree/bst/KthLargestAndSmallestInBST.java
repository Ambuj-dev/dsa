package tree.bst;

import tree.TreeNode;

public class KthLargestAndSmallestInBST {

    static TreeNode insertBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertBST(root.left, val);
        } else {
            root.right = insertBST(root.right, val);
        }
        return root;
    }
    //reverse inorder of BST is always sorted in descending order
    //O(min(K,N)
    static TreeNode kthLargest(TreeNode root, int k[]) {
        if (root == null)
            return null;

        TreeNode right = kthLargest(root.right, k);
        if (right != null)
            return right;
        k[0]--;

        if (k[0] == 0)
            return root;

        return kthLargest(root.left, k);
    }
    //inorder of BST is always sorted in ascending order
    // O(min(K,N)
    static TreeNode kthSmallest(TreeNode root, int k[]) {
        if (root == null)
            return null;

        TreeNode left = kthSmallest(root.left, k);
        if (left != null)
            return left;
        k[0]--;
        if (k[0] == 0)
            return root;

        return kthSmallest(root.right, k);
    }

    public static void main(String args[]) {

        int arr[] = {10, 40, 45, 20, 25, 30, 50}, i;

        int k = 4;
        TreeNode root = null;
        for (i = 0; i < 7; i++) {
            root = insertBST(root, arr[i]);
        }
        System.out.println();

        TreeNode large = kthLargest(root, new int[]{k});
        TreeNode small = kthSmallest(root, new int[]{k});
        if (large == null) {
            System.out.println("Invalid input");
        } else
            System.out.println("kth largest element is  " + large.val);

        if (small == null) {
            System.out.println("Invalid input");
        } else {
            System.out.println("kth smallest element is  " + small.val);
        }
    }
}

