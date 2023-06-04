package patterns.tree.depthfirstsearch;

import patterns.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CountPathForASum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has "+countPaths(root, 11)+" paths");
        System.out.println("Tree has "+countPaths1(root, 11)+" paths");
        //[1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000]
        TreeNode root1 = new TreeNode(1000000000);
        root.left = new TreeNode(1000000000);
        //root.right = new TreeNode(1);
        root.left.left = new TreeNode(294967296);
        root.left.left.left = new TreeNode(1000000000);
        root.left.left.left.left = new TreeNode(1000000000);
        root.left.left.left.left.left = new TreeNode(1000000000);
        //root.right.right = new TreeNode(5);
        System.out.println("Tree has "+countPaths(root1, 0)+" paths");
        System.out.println("Tree has "+countPaths1(root1, 0)+" paths");

    }
    // worst case: N^2 best case:N*logN
    private static int countPaths(TreeNode root, int sum){
        List<Integer> currentPath = new ArrayList<>();
        return countPathsRecursive(root, sum, currentPath);
    }
    private static int countPathsRecursive(TreeNode currentNode, int sum, List<Integer> currentPath){
        if(currentNode == null) return 0;
        currentPath.add(currentNode.value);
        int pathCount = 0;
        long pathSum = 0;
        for(int i = currentPath.size() - 1; i>=0; i--){
            pathSum +=currentPath.get(i);
            if(pathSum == sum) pathCount++;
        }
        pathCount += countPathsRecursive(currentNode.left, sum, currentPath);
        pathCount += countPathsRecursive(currentNode.right, sum, currentPath);

        currentPath.remove(currentPath.size() - 1);

        return pathCount;
    }

    public static int countPaths1(TreeNode root, int S) {
        List<Integer> currentPath = new LinkedList<>();
        return countPathsRecursive1(root, S, currentPath);
    }

    private static int countPathsRecursive1(TreeNode currentNode, int S,
                                           List<Integer> currentPath) {
        if (currentNode == null)
            return 0;

        // add the current node to the path
        currentPath.add(currentNode.value);
        int pathCount = 0;
        double pathSum = 0; // let's use a double to handle integer overflow for large sums
        // find the sums of all sub-paths in the current path list
        ListIterator<Integer> pathIterator = currentPath.listIterator(currentPath.size());
        while (pathIterator.hasPrevious()) {
            pathSum += pathIterator.previous();
            // if the sum of any sub-path is equal to 'S' we increment our path count.
            if (pathSum == S) {
                pathCount++;
            }
        }

        // traverse the left sub-tree
        pathCount += countPathsRecursive1(currentNode.left, S, currentPath);
        // traverse the right sub-tree
        pathCount += countPathsRecursive1(currentNode.right, S, currentPath);

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);

        return pathCount;
    }

}
