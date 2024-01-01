package tree.bst;

import tree.TreeNode;

public class InOrderSuccessor {
    public static TreeNode inOrderSuccessor(TreeNode root, TreeNode p){
        TreeNode successor = null;
        while(root != null){
            if(root.val > p.val){
                successor = root;
                root = root.left;
            }else{
                root = root.right;
            }
        }
        return successor;
    }

    public static TreeNode inOrderPredecessor(TreeNode root, TreeNode p){
        TreeNode predecessor = null;
        while(root != null){
            if(root.val < p.val){
                predecessor = root;
                root = root.right;
            }else{
                root = root.left;
            }
        }
        return predecessor;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(8);
        root.right.right.right = new TreeNode(10);
        System.out.println(inOrderSuccessor(root, root.left.right.left));
        System.out.println(inOrderSuccessor(root, root.right.right.right));
        System.out.println(inOrderPredecessor(root, root.left.right.left));
    }
}
