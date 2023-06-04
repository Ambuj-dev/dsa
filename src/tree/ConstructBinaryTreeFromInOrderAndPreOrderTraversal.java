package tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInOrderAndPreOrderTraversal {
    static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree(preorder, 0, preorder.length - 1, 0,
                inorder.length - 1, inMap);
        return root;
    }

    static TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                inRoot + 1, inEnd, inMap);

        return root;
    }

    public static void main(String args[]) {

        int preorder[] = {10, 20, 40, 50, 30, 60};
        int inorder[] = {40, 20, 50, 10, 60, 30};
        System.out.println(buildTree(preorder, inorder));
    }

}
