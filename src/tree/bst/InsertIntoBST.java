package tree.bst;

import tree.TreeNode;

public class InsertIntoBST {
    public static TreeNode insertIntoBST(TreeNode root, int val){
        if(root == null) return new TreeNode(val);
        TreeNode cur = root;
        while(true){
            if(cur.val <= val){
                if(cur.right != null) cur = cur.right;
                else{
                    cur.right = new TreeNode(val);
                    break;
                }
            }else{
                if(cur.left != null) cur = cur.left;
                else{
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
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
        System.out.println(insertIntoBST(root, 9));
    }
}
