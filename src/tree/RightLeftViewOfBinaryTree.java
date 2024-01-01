package tree;

import patterns.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightLeftViewOfBinaryTree {

    private static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            for(int i = 0; i < levelSize; i++){
                TreeNode currentNode = queue.poll();
                if(i + 1 == levelSize){
                    result.add(currentNode.value);
                }
                if(currentNode.left != null) queue.add(currentNode.left);
                if(currentNode.right != null) queue.add(currentNode.right);
            }
        }
        return result;
    }

    public static List<Integer> rightSideViewOptimized(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public static void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.value);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);

    }

    private static List<Integer> leftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            for(int i = 0; i< levelSize; i++){
                TreeNode currentNode = queue.poll();
                if(i == 0){
                    result.add(currentNode.value);
                }
                if(currentNode.left != null) queue.add(currentNode.left);
                if(currentNode.right != null) queue.add(currentNode.right);
            }
        }
        return result;
    }

    public static List<Integer> leftSideViewOptimized(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        leftView(root, result, 0);
        return result;
    }

    public static void leftView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.value);
        }
        leftView(curr.left, result, currDepth + 1);
        leftView(curr.right, result, currDepth + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(7);
        System.out.println(rightSideViewOptimized(root));
        System.out.println(rightSideView(root));
        System.out.println(leftSideViewOptimized(root));
        System.out.println(leftSideView(root));
    }
}
