package tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInOrderAndPostOrderTraversal {
    static TreeNode buildTree(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree(postorder, 0, postorder.length - 1, 0,
                inorder.length - 1, inMap);
        return root;
    }

    static TreeNode buildTree(int[] postorder, int postStart, int postEnd, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(postorder, postStart, postStart + numsLeft-1,
                inStart, inRoot - 1, inMap);
        root.right = buildTree(postorder, postStart + numsLeft, postEnd-1,
                inRoot + 1, inEnd, inMap);

        return root;
    }

    public static void main(String args[]) {

        int postorder[] = {40, 50, 20, 60, 30, 10};
        int inorder[] = {40, 20, 50, 10, 60, 30};
        System.out.println(buildTree(postorder, inorder));
    }

}
