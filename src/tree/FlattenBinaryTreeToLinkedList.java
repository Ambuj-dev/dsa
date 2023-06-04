package tree;

import java.util.Stack;

public class FlattenBinaryTreeToLinkedList {

    static Node prev = null;

    //TC and SC:O(N)
    static void flatten2(Node root) {
        if (root == null) return;

        flatten2(root.right);
        flatten2(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    //TC and SC:O(N)
    static void flatten1(Node root) {
        if (root == null) return;
        Stack<Node> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            Node cur = st.pop();
            if (cur.right != null) {
                st.push(cur.right);
            }
            if (cur.left != null) {
                st.push(cur.left);
            }
            if (!st.isEmpty()) {
                cur.right = st.peek();
            }
            cur.left = null;
        }

    }

    //TC: O(N) and SC:O(1)
    static void flatten(Node root) {
        Node cur = root;
        while (cur != null) {
            if (cur.left != null) {
                Node pre = cur.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }


    public static void main(String args[]) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right = new Node(5);
        root.right.right = new Node(6);
        root.right.right.left = new Node(7);
        flatten(root);
        while (root.right != null) {
            System.out.print(root.data + "->");
            root = root.right;
        }
        System.out.print(root.data);
    }

}
