package patterns.subsets;

import patterns.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GenerateUniqueBinarySearchTrees {
    public static void main(String[] args) {
        System.out.println(generateTrees(3));
    }
    public static List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();
        return generateTrees(1, n);
    }

    public static List<TreeNode> generateTrees(int start, int end){
        List<TreeNode> res = new ArrayList<>();
        if(start>end){
            res.add(null);
            return res;
        }
        for(int i=start; i<end+1;i++){
            List<TreeNode> leftTree = generateTrees(start, i-1);
            List<TreeNode> rightTree = generateTrees(i+1, end);
            for(TreeNode left: leftTree){
                for(TreeNode right: rightTree){
                    res.add(new TreeNode( i, left, right));
                }
            }
        }
        return res;
    }
}
