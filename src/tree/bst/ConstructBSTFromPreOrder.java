package tree.bst;

import tree.TreeNode;

public class ConstructBSTFromPreOrder {

    public static TreeNode bstFromPreorder(int[] arr) {
        return bstFromPreorder(arr, Integer.MAX_VALUE, new int[]{0});
    }

    public static TreeNode bstFromPreorder(int[] arr, int bound, int[] i) {
        if (i[0] == arr.length || arr[i[0]] > bound) return null;
        TreeNode root = new TreeNode(arr[i[0]++]);
        root.left = bstFromPreorder(arr, root.val, i);
        root.right = bstFromPreorder(arr, bound, i);
        return root;
    }


    public static TreeNode bstFromPreorderIterative(int[] preorder) {
         TreeNode root = null;
        for(int n:preorder)
            root=add(root,n);
        return root;
    }

    public static TreeNode add(TreeNode root,int n){
        if(root==null)
            return new TreeNode(n);
        else if(n<root.val)
            root.left=add(root.left,n);
        else if(n>root.val)
            root.right=add(root.right,n);
        return root;
    }

    public static void main(String[] args) {
        System.out.println(bstFromPreorder(new int[]{8,5,1,7,10,12}));
        System.out.println(bstFromPreorderIterative(new int[]{8,5,1,7,10,12}));
    }
}
