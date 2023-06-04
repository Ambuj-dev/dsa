package patterns.tree.depthfirstsearch;

import patterns.tree.TreeNode;

public class PathWithGivenSequence {
    public static void main(String[] args) {
        int arr[] = {5, 8, 6, 7};
        int n = arr.length;
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);

        if(isPathExist(root, arr, n))
            System.out.println("Path Exists");
        else
            System.out.println("Path does not Exist");

        if(isPathExist(root, new int[]{5,3,2,5}, n))
            System.out.println("Path Exists");
        else
            System.out.println("Path does not Exist");

    }

    static boolean isPathExist(TreeNode root, int[] arr, int n){
        if(root == null){
            return n==0;
        }
        return isPathExistUtil(root, arr, n, 0);
    }

    private static boolean isPathExistUtil(TreeNode root, int[] arr, int n, int index) {
        if(root == null || index == n) return false;
        if(root.left == null && root.right == null){
            if(root.value == arr[index] && index == n-1) return true;
            return false;
        }
        return index < n && root.value == arr[index] && isPathExistUtil(root.left, arr, n, index+1) || isPathExistUtil(root.right, arr, n, index+1);
    }
}
