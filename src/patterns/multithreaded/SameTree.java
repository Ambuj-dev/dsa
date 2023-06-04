package patterns.multithreaded;

public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // p and q are both null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        // one of p and q has different value
        if (p.val != q.val) return false;

        // check left and right subtree recursively
        return isSameTree(p.right, q.right) &&
                isSameTree(p.left, q.left);
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(10);
        p.left = new TreeNode(4);
        p.left.left = new TreeNode(1);
        p.right = new TreeNode(15);
        p.right.left = new TreeNode(14);

        TreeNode q = new TreeNode(10);
        q.left = new TreeNode(4);
        q.left.left = new TreeNode(1);
        q.right = new TreeNode(15);
        q.right.left = new TreeNode(14);

        SameTree sameTree = new SameTree();
        System.out.println(sameTree.isSameTree(p, q));

        q.right.right = new TreeNode(20);
        System.out.println(sameTree.isSameTree(p, q));

        q.right.right = new TreeNode(20);
        p.left.val = 9;
        System.out.println(sameTree.isSameTree(p, q));
    }
}
