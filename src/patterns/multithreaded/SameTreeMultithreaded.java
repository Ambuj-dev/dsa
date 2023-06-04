package patterns.multithreaded;

public class SameTreeMultithreaded {

    private volatile boolean isSame;

    public boolean isSameTree(TreeNode p, TreeNode q) {
        isSame = true;
        int numThreads = Runtime.getRuntime().availableProcessors();
        return isSameTreeMultiThreaded(p, q, numThreads);
    }

    private boolean isSameTreeMultiThreaded(TreeNode p, TreeNode q, int numThreads) {
        // p and q are both null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        // one of p and q has different value
        if (p.val != q.val) return false;

        // if we can start more threads, we will spawn a new thread to check the
        // right subtree, otherwise we will do everything in the current thread
        if (numThreads > 0) {
            // spawn a separate thread for checking the right sub-tree
            Thread t1 = new Thread(() ->
                    isSame &= isSameTreeMultiThreaded(p.right, q.right, numThreads / 2)
            );
            t1.start();

            // check the left sub-tree in the current thread
            isSame &= isSameTreeMultiThreaded(p.left, q.left, numThreads / 2);

            try {
                t1.join(); // wait for the thread checking the right sub-tree
            } catch (InterruptedException e) {
                System.out.println(e);
            }

        } else { // do everything in the current thread
            isSame &= isSameTreeMultiThreaded(p.right, q.right, 0) &&
                    isSameTreeMultiThreaded(p.left, q.left, 0);
        }

        return isSame;
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

        SameTreeMultithreaded sameTree = new SameTreeMultithreaded();
        System.out.println(sameTree.isSameTree(p, q));

        q.right.right = new TreeNode(20);
        System.out.println(sameTree.isSameTree(p, q));

        q.right.right = new TreeNode(20);
        p.left.val = 9;
        System.out.println(sameTree.isSameTree(p, q));
    }
}

