package tree;

import java.util.*;

public class TopViwOfBinaryTree {
    static List<Integer> topView(Node root)
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
            if(map.get(hd) == null) map.put(hd, temp.data);
            if(temp.left != null) {
                q.add(new Pair(temp.left, hd - 1));
            }
            if(temp.right != null) {
                q.add(new Pair(temp.right, hd + 1));
            }
        }
        ans.addAll(map.values());
        /*for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }*/
        return ans;

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
        System.out.println(topView(root));//[4,2,1,3,7]
    }
}

