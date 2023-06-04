package patterns.tree.depthfirstsearch;

import patterns.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllPathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println(pathSum(root, 23));

    }
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> currentPath = new ArrayList<>();
        List<List<Integer>> allPath = new ArrayList<>();
        dfs(root, currentPath, allPath, targetSum);
        return allPath;
    }

    public static void dfs(TreeNode root, List<Integer> currentPath, List<List<Integer>> allPath, int sum){
        if(root == null) return;
        currentPath.add(root.value);
        if(root.value == sum && root.left == null && root.right == null) allPath.add(new ArrayList<>(currentPath));
        else{
            dfs(root.left, currentPath, allPath, sum-root.value);
            dfs(root.right, currentPath, allPath, sum-root.value);
        }
        currentPath.remove(currentPath.size() - 1);
    }
}
