package tree.bst;

import tree.TreeNode;

public class LowestCommonAncestorInBST {
    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return null;
        int cur = root.val;
        if(cur < p.val && cur < q.val) return lca(root.right, p, q);
        if(cur > p.val && cur > q.val) return  lca(root.left, p , q);
        return root;
    }
}
