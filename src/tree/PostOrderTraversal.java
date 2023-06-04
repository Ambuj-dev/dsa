package tree;

import java.util.ArrayList;
import java.util.Stack;

public class PostOrderTraversal {

    //Using 2 stacks
    static ArrayList<Integer> postOrderTravTwoStack(Node curr) {

        ArrayList<Integer> postOrder = new ArrayList<>();
        if (curr == null) return postOrder;

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(curr);
        while (!s1.isEmpty()) {
            curr = s1.pop();
            s2.push(curr);
            if (curr.left != null)
                s1.push(curr.left);
            if (curr.right != null)
                s1.push(curr.right);
        }
        while (!s2.isEmpty()) {
            postOrder.add(s2.pop().data);
        }
        return postOrder;
    }

    //Using One stack
    static ArrayList<Integer> postOrderTravOneStack(Node cur) {

        ArrayList<Integer> postOrder = new ArrayList<>();
        if (cur == null) return postOrder;

        Stack<Node> st = new Stack<>();
        while (cur != null || !st.isEmpty()) {

            if (cur != null) {
                st.push(cur);
                cur = cur.left;
            } else {
                Node temp = st.peek().right;
                if (temp == null) {
                    temp = st.pop();
                    postOrder.add(temp.data);
                    while (!st.isEmpty() && temp == st.peek().right) {
                        temp = st.pop();
                        postOrder.add(temp.data);
                    }
                } else cur = temp;
            }
        }
        return postOrder;
    }

    static void postOrderTrav(Node curr, ArrayList<Integer> postOrder) {
        if (curr == null)
            return;

        postOrderTrav(curr.left, postOrder);
        postOrderTrav(curr.right, postOrder);
        postOrder.add(curr.data);
    }


    public static void main(String args[]) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(8);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(10);

        ArrayList<Integer> postOrder = new ArrayList<>();
        postOrder = postOrderTravTwoStack(root);

        System.out.println("The postOrder Traversal is : ");
        for (int i = 0; i < postOrder.size(); i++) {
            System.out.print(postOrder.get(i) + " ");
        }

        postOrder = postOrderTravOneStack(root);

        System.out.println("\nThe postOrder Traversal is : ");
        for (int i = 0; i < postOrder.size(); i++) {
            System.out.print(postOrder.get(i) + " ");
        }

        postOrder = new ArrayList<>();
        postOrderTrav(root, postOrder);

        System.out.println("\nThe postOrder Traversal is : ");
        for (int i = 0; i < postOrder.size(); i++) {
            System.out.print(postOrder.get(i) + " ");
        }
    }
}
