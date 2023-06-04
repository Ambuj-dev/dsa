package tree.bst;

import tree.TreeNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TwoSumBST {
    //TC: O(N) SC:O(H)*2
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        V1BSTIterator l = new V1BSTIterator(root, false);
        V1BSTIterator r = new V1BSTIterator(root, true);

        int i = l.next();
        int j = r.next();
        while(i<j) {
            if(i + j == k) return true;
            else if(i + j < k) i = l.next();
            else j = r.next();
        }
        return false;
    }

    public boolean findTargetOptimised(TreeNode root, int k) {
        Set<Integer> set=new HashSet<>();
        return helper(root,k,set);
    }
    private boolean helper(TreeNode root,int k,Set<Integer> set)
    {
        if(root==null)
            return false;
        if(set.contains(k-root.val))
            return true;
        set.add(root.val);
        return helper(root.left,k,set) || helper(root.right,k,set);

    }
}
class V1BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    boolean reverse = true;

    public V1BSTIterator(TreeNode root, boolean isReverse) {
        reverse = isReverse;
        pushAll(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        TreeNode tmpNode = stack.pop();
        if(!reverse) pushAll(tmpNode.right);
        else pushAll(tmpNode.left);
        return tmpNode.val;
    }

    private void pushAll(TreeNode node) {
        while(node != null) {
            stack.push(node);
            if(reverse) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
    }
}
