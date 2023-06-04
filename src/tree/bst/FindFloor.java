package tree.bst;

import tree.TreeNode;

public class FindFloor {
    public static int findFloor(TreeNode root, int key){
        int ceil = -1;
        while(root != null){
            if(root.val == key){
                return root.val;
            }
            if(key > root.val){
                ceil = root.val;
                root = root.right;
            }else{
                root = root.left;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(8);
        root.left.right.left = new TreeNode(6);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(17);
        System.out.println(findFloor(root, 9));
    }
}
