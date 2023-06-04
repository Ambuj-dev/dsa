package tree;

import java.util.*;

public class BottomViwOfBinaryTree {
    static List<Integer> bottomView(Node root)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while(!q.isEmpty()) {
            Pair it = q.remove();
            int hd = it.num;
            Node temp = it.node;
            map.put(hd, temp.data);
            if(temp.left != null) {
                q.add(new Pair(temp.left, hd - 1));
            }
            if(temp.right != null) {
                q.add(new Pair(temp.right, hd + 1));
            }
        }
        return new ArrayList<>(map.values());
        /*for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;*/

    }
    static class Pair {
        Node node;
        int num;

        Pair(Node _node, int _num) {
            num = _num;
            node = _node;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(6);
        root.right = new Node(3);
        root.right.right = new Node(7);
        System.out.println(bottomView(root));
    }
}

