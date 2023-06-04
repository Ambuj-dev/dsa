package tree;

import java.util.ArrayList;
import java.util.List;

public class MorrisPreOrderTraversal {
    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                preorder.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = cur;
                    preorder.add(cur.val);
                    cur = cur.left;
                } else {
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return preorder;
    }

    public static void main(String args[]) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);

        List<Integer> preorder = new ArrayList<>();
        preorder = preOrderTraversal(root);

        System.out.println("The Preorder Traversal is: ");
        for (int i = 0; i < preorder.size(); i++) {
            System.out.print(preorder.get(i) + " ");
        }

    }
}
