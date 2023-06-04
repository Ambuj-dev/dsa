package tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthOfBinaryTree {
    static class Pair {
        TreeNode node;
        int num;
        Pair(TreeNode _node, int _num) {
            num = _num;
            node = _node;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while(!q.isEmpty()){
            int size = q.size();
            int mmin = q.peek().num;    //to make the id starting from zero
            int first = 0,last = 0;
            for(int i=0; i<size; i++){
                Pair pair = q.poll();
                int cur_id = pair.num-mmin;
                TreeNode node = pair.node;
                if(i==0) first = cur_id;
                if(i==size-1) last = cur_id;
                if(node.left != null)
                    q.offer(new Pair(node.left, cur_id*2+1));
                if(node.right != null)
                    q.offer(new Pair(node.right, cur_id*2+2));
            }
            ans = Math.max(ans, last-first+1);
        }
        return ans;
    }


    public static void main(String args[]) {

        TreeNode  root = new TreeNode(1);
        root . left = new TreeNode(3);
        root . left . left = new TreeNode(5);
        root . left . left . left = new TreeNode(7);
        root . right = new TreeNode(2);
        root . right . right = new TreeNode(4);
        root . right . right . right = new TreeNode(6);

        int maxWidth = widthOfBinaryTree(root);
        System.out.println("The maximum width of the Binary Tree is "+maxWidth);


    }
}
