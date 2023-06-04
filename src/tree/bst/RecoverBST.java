package tree.bst;

import tree.TreeNode;

public class RecoverBST {

    private TreeNode first;
    private TreeNode prev;
    private TreeNode middle;
    private TreeNode last;
    //Inorder of BST gives  the values in increasing order. So it  will help us find violations
    //TC: O(N) space O(1)
    private void inorder(TreeNode root) {
        if(root == null) return;

        inorder(root.left);

        if (prev != null && (root.val < prev.val))
        {

            // If this is first violation, mark these two nodes as
            // 'first' and 'middle'
            if ( first == null )
            {
                first = prev;
                middle = root;
            }

            // If this is second violation, mark this node as last
            else
                last = root;
        }

        // Mark this node as previous
        prev = root;
        inorder(root.right);
    }
    public void recoverTree(TreeNode root) {
        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inorder(root);
        if(first!=null && last!=null) {
            int t = first.val;
            first.val = last.val;
            last.val = t;
        }
        else if(first!=null && middle!=null)  {
            int t = first.val;
            first.val = middle.val;
            middle.val = t;
        }
    }

    public static void main(String[] args) {
        RecoverBST recoverBST = new RecoverBST();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        recoverBST.recoverTree(root);
        System.out.println(root);
    }
}
