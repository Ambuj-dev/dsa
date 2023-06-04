package patterns.tree.depthfirstsearch;

import patterns.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllRootToLeafPath {
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println(allRootToLeafPath(root));
        System.out.println(allRootToLeafPathLeetCode(root));
        System.out.println(maximumPathSum(root));
    }
    public static List<List<Integer>> allRootToLeafPath(TreeNode root) {
        List<Integer> currentPath = new ArrayList<>();
        List<List<Integer>> allPath = new ArrayList<>();
        dfs(root, currentPath, allPath);
        return allPath;
    }

    public static void dfs(TreeNode root, List<Integer> currentPath, List<List<Integer>> allPath){
        if(root == null) return;
        currentPath.add(root.value);
        if(root.left == null && root.right == null) allPath.add(new ArrayList<>(currentPath));
        else{
            dfs(root.left, currentPath, allPath);
            dfs(root.right, currentPath, allPath);
        }
        currentPath.remove(currentPath.size() - 1);
    }

    public static int maximumPathSum(TreeNode root) {
        List<Integer> currentPath = new ArrayList<>();
        maximumPathSum(root, currentPath);
        return max;
    }

    public static void maximumPathSum(TreeNode root, List<Integer> currentPath){
        if(root == null) return;
        currentPath.add(root.value);
        int sum = 0;
        if(root.left == null && root.right == null) {
            for(Integer path: currentPath){
                sum += path;
            }
            max = Math.max(sum, max);
        }
        else{
            maximumPathSum(root.left, currentPath);
            maximumPathSum(root.right, currentPath);
        }
        currentPath.remove(currentPath.size() - 1);
    }

    public static List<String> allRootToLeafPathLeetCode(TreeNode root) {
        List<String> allPath = new ArrayList<>();
        dfsLeetCode(root, "", allPath);
        return allPath;
    }

    public static void dfsLeetCode(TreeNode root, String currentPath, List<String> allPath){
        if(root == null) return;
        currentPath+=root.value;
        if(root.left == null && root.right == null) allPath.add(currentPath);
        else{
            dfsLeetCode(root.left, currentPath+"->", allPath);
            dfsLeetCode(root.right, currentPath+"->", allPath);
        }
    }
}
