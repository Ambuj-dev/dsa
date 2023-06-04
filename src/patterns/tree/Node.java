package patterns.tree;

public class Node {
    public int value;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                ", next=" + next +
                '}';
    }
}
