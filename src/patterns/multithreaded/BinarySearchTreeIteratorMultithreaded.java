package patterns.multithreaded;

import java.util.Stack;

public class BinarySearchTreeIteratorMultithreaded {
        private Stack<TreeNode> stack = new Stack<TreeNode>();
        private Thread t1 = null;

        public BinarySearchTreeIteratorMultithreaded(TreeNode root) {
            traverseLeft(root);
        }

        /** @return whether we have a next smallest number */
        synchronized public boolean hasNext() {
            checkThread();
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        synchronized public int next() {
            checkThread();
            TreeNode tmpNode = stack.pop();
            traverseLeft(tmpNode.right);
            return tmpNode.val;
        }

        /** traverse the left sub-tree to push all nodes on the stack */
        private void traverseLeft(TreeNode node) {
            // spawn a new thread to process the left sub-tree
            t1 = new Thread(() -> {
                    TreeNode tmp = node;
                    while(tmp != null){
                        stack.push(tmp);
                        tmp = tmp.left;
                    }
                });

            t1.start();
        }

        /** if the previous thread is active, wait before it finishes */
        private void checkThread() {
            if( t1 != null && t1.isAlive()) {
                try
                {
                    t1.join(); // wait for the thread traversing the left subtree
                } catch(InterruptedException e){ System.out.println(e); }
            }
        }

        public static void main(String[] args) {
            TreeNode root = new TreeNode(10);
            root.left = new TreeNode(4);
            root.left.left = new TreeNode(1);
            root.right = new TreeNode(15);
            root.right.left = new TreeNode(14);
            root.right.right = new TreeNode(19);
            root.right.right.right = new TreeNode(20);

            BinarySearchTreeIteratorMultithreaded itr = new BinarySearchTreeIteratorMultithreaded(root);
            System.out.println("hasNext() -> " + itr.hasNext());
            System.out.println("next() -> " + itr.next());
            System.out.println("next() -> " + itr.next());
            System.out.println("hasNext() -> " + itr.hasNext());
            System.out.println("next() -> " + itr.next());
            System.out.println("next() -> " + itr.next());
            System.out.println("next() -> " + itr.next());
            System.out.println("next() -> " + itr.next());
            System.out.println("next() -> " + itr.next());
            System.out.println("hasNext() -> " + itr.hasNext());
        }
    }

