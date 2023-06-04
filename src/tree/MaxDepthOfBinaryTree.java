package tree;

import java.util.LinkedList;

public class MaxDepthOfBinaryTree {

    public static int maxDepth(TreeNode root) {
        if(root == null) return 0;

        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);

        return 1 + Math.max(lh, rh);
    }

    private static int MaxDepthByLevelOrder( TreeNode root ){
        if( root == null ){
            return 0;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);

        int level = 0;

        while( queue.size() > 0 ){
            int size = queue.size();

            while( size-- > 0 ){
                TreeNode remNode = queue.removeFirst();
                if( remNode.left != null ){
                    queue.addLast( remNode.left );
                }
                if( remNode.right != null ){
                    queue.addLast( remNode.right );
                }
            }

            level++;
        }

        return level;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(12);
        node.left = new TreeNode(2);
        node.left.right = new TreeNode(10);
        node.right = new TreeNode(5);
        node.right.left = new TreeNode(3);
        node.right.left.left = new TreeNode(8);
        node.right.left.right = new TreeNode(11);
        node.right.right = new TreeNode(4);
        System.out.println(maxDepth(node));
        System.out.println(MaxDepthByLevelOrder(node));
    }

}
