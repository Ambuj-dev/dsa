package patterns.multithreaded;

import java.util.Stack;

public class BinarySearchTreeIterator {
    private Stack<TreeNode> stack = new Stack<>();

    public BinarySearchTreeIterator(TreeNode root) {
        traverseLeft(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tmpNode = stack.pop();
        traverseLeft(tmpNode.right);
        return tmpNode.val;
    }

    /** traverse the left sub-tree to push all nodes on the stack */
    private void traverseLeft(TreeNode node) {
        while(node != null){
            stack.push(node);
            node = node.left;
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

        BinarySearchTreeIterator itr = new BinarySearchTreeIterator(root);
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
