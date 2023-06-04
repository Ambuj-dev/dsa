package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public List<Integer> levelOrder(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> wrapList = new ArrayList<Integer>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){

            if(queue.peek().left != null)
                queue.offer(queue.peek().left);

            if(queue.peek().right != null)
                queue.offer(queue.peek().right);

            wrapList.add(queue.poll().val);
        }
        return wrapList;
    }

}
