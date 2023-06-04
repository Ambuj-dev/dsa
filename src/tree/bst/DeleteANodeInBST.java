package tree.bst;

import tree.TreeNode;

public class DeleteANodeInBST {
    public static TreeNode deleteNode(TreeNode root, int key){
        if(root == null) return null;
        if(root.val == key) return helper(root);
        TreeNode cur = root;
        while(cur != null){
            if(cur.val > key){
                if(cur.left != null && cur.left.val == key){
                    cur.left =  helper(cur.left);
                }else{
                    cur = cur.left;
                }
            }else{
                if(cur.right != null && cur.right.val == key){
                    cur.right = helper(cur.right);
                }else{
                    cur = cur.right;
                }
            }
        }
        return root;
    }

    private static TreeNode helper(TreeNode root){
        if(root.left == null) return root.right;
        else if(root.right == null) return root.left;
        else{
            TreeNode rightChild = root.right;
            TreeNode lastRight = findLastRight(root.left);
            lastRight.right = rightChild;
            return  root.left;
        }
    }

    private static TreeNode findLastRight(TreeNode root) {
        while(root.right != null) {
            root = root.right;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(5);
        root.left.left.left = new TreeNode(3);
        root.left.left.left.left = new TreeNode(2);
        root.left.left.left.left.left = new TreeNode(1);
        root.left.left.left.right = new TreeNode(4);
        root.left.left.right = new TreeNode(7);
        root.left.left.right.left = new TreeNode(6);
        root.right = new TreeNode(12);
        root.right.left = new TreeNode(10);
        root.right.left.right = new TreeNode(11);
        root.right.right = new TreeNode(13);
        System.out.println(deleteNode(root, 5));
    }
}
