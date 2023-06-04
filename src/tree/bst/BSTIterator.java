package tree.bst;

import tree.TreeNode;

import java.util.Stack;

public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root){
        pushAll(root);
    }
    public boolean hasNext(){
        return !stack.isEmpty();
    }
    public int next(){
        TreeNode treeNode = stack.pop();
        pushAll(treeNode.right);
        return treeNode.val;
    }

    private void pushAll(TreeNode root) {
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }
}
