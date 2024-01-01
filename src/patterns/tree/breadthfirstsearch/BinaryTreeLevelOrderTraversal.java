package patterns.tree.breadthfirstsearch;

import patterns.tree.Node;
import patterns.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode();
        System.out.println(levelOrder(node));
        System.out.println(levelOrder(root));
        System.out.println(levelOrder(root1));
        System.out.println(levelOrderReversal(node));
        System.out.println(zigzagLevelOrder(node));
        System.out.println(levelAverage(node));
        System.out.println(levelHavingMaximumSum(node));
        System.out.println(eachLevelMaximum(node));
        System.out.println(findMinimumDepth(node));
        System.out.println("Min depth "+minDepth(node));
        System.out.println(findMaximumDepth(node));
        System.out.println(maxDepth(node));
        System.out.println(findSuccessor(node, 9));
        System.out.println(findSuccessor(node, 20));
        Node node1 = new Node(1);
        node1.left = new Node(2);
        node1.right = new Node(3);
        node1.left.left = new Node(4);
        node1.left.right = new Node(5);
        node1.right.left = new Node(6);
        node1.right.right = new Node(7);
        //System.out.println("connect-->"+connect(node1));
        //System.out.println("connectO-->"+connectOptimized(node1));
        //System.out.println("connectO-->"+connectOptimized2(node1));
        System.out.println("connect All Sibling-->"+connectAllSibling(node1));
        System.out.println(rightSideView(node));
        System.out.println(rightSideViewOptimized(node));
        System.out.println(leftSideView(node));
        System.out.println(leftSideViewOptimized(node));
        TreeNode root2 = new TreeNode(12);
        root2.left = new TreeNode(7);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(9);
        root2.right.left = new TreeNode(10);
        root2.right.right = new TreeNode(5);
        connect(root2);
        System.out.println("Level order traversal using 'next' pointer: ");
        root2.printLevelOrder();
    }
    private static List<List<Integer>> levelOrder(TreeNode root){
        if(root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for(int i= 0; i< size;i++){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                list.add(node.value);
            }
            result.add(list);
        }
        return result;
    }
    private static List<List<Integer>> levelOrderReversal(TreeNode root){
        if(root == null) return new ArrayList<>();
        LinkedList<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for(int i= 0; i< size;i++){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                list.add(node.value);
            }
            result.addFirst( list);
        }
        return result;
    }
    private static List<List<Integer>> zigzagLevelOrder(TreeNode root){
        if(root == null) return new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        while(!queue.isEmpty()){
            LinkedList<Integer> list = new LinkedList<>();
            int size = queue.size();
            for(int i= 0; i< size;i++){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                if(leftToRight)
                    list.addLast(node.value);
                else
                    list.addFirst(node.value);
            }
            result.add(list);
            leftToRight = !leftToRight;
        }
        return result;
    }

    private static List<Double> levelAverage(TreeNode root){
        if(root == null) return new LinkedList<>();
        List<Double> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            double sum = 0;
            for(int i= 0; i< size;i++){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                sum+=node.value;
            }
            result.add(sum/size);
        }
        return result;
    }

    private static int levelHavingMaximumSum(TreeNode root){
        if(root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = -1;
        int max = Integer.MIN_VALUE;
        int level = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            int sum = 0;
            for(int i= 0; i< size;i++){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                sum+=node.value;
            }
            if(sum > max){
                max = sum;
                result = level;
            }
            level++;
        }
        return result;
    }
    private static List<Integer> eachLevelMaximum(TreeNode root){
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i= 0; i< size;i++){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                max = Math.max(max, node.value);
            }
            result.add(max);

        }
        return result;
    }

    private static int findMinimumDepth(TreeNode root){
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int minimumDepth = 0;
        while(!queue.isEmpty()){
            minimumDepth++;
            int size = queue.size();
            for(int i= 0; i< size;i++){
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null) return minimumDepth;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }
        return minimumDepth;
    }
    private static int minDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }


    private static int findMaximumDepth(TreeNode root){
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maximumDepth = 0;
        while(!queue.isEmpty()){
            maximumDepth++;
            int size = queue.size();
            for(int i= 0; i< size;i++){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }
        return maximumDepth;
    }

    private static int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    private static TreeNode findSuccessor(TreeNode root, int key){

        if(root == null) return  null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
            if(node.value == key) break;
        }
        if(queue.size() > 0) return queue.poll();
        return null;
    }

    private static Node connect(Node root){

        if(root == null) return  null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            if(curNode ==null && queue.isEmpty()){
                return root;
            }
            if(curNode == null){
                queue.add(null);
            }
            else {
                curNode.next = queue.peek();
                if (curNode.left != null) queue.add(curNode.left);
                if (curNode.right != null) queue.add(curNode.right);
            }
        }
        return root;
    }

    public static Node connectOptimized(Node root) {
        Node answ = root;
        Node tmpLeft;
        while (root != null) {
            tmpLeft = root.left;
            while (root != null && root.left != null) {
                root.left.next = root.right;
                if (root.next != null)
                    root.right.next = root.next.left;
                root = root.next;
            }
            root = tmpLeft;
        }
        return answ;
    }

    public Node connectOptimized2(Node root) {
        if(root == null) return null;
        if(root.left != null) root.left.next = root.right;
        if(root.right != null && root.next != null) root.right.next = root.next.left;
        connectOptimized2(root.left);
        connectOptimized2(root.right);
        return root;
    }


    public static void connect(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode previousNode = null;
            int levelSize = queue.size();
            // connect all nodes of this level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (previousNode != null)
                    previousNode.next = currentNode;
                previousNode = currentNode;

                // insert the children of current node in the queue
                if (currentNode.left != null)
                    queue.offer(currentNode.left);
                if (currentNode.right != null)
                    queue.offer(currentNode.right);
            }
        }
    }

    private static Node connectAllSibling(Node root){

        if(root == null) return  null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node previousNode = null;
        Node currentNode;
        while(!queue.isEmpty()){
            currentNode = queue.poll();
            if(previousNode != null){
                previousNode.next = currentNode;
            }
            previousNode = currentNode;
            if(currentNode.left != null) queue.add(currentNode.left);
            if(currentNode.right != null) queue.add(currentNode.right);
        }
        return root;
    }
    public static void connectAllSiblings(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode currentNode = null, previousNode = null;
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            if (previousNode != null)
                previousNode.next = currentNode;
            previousNode = currentNode;

            // insert the children of current node in the queue
            if (currentNode.left != null)
                queue.offer(currentNode.left);
            if (currentNode.right != null)
                queue.offer(currentNode.right);
        }
    }

    private static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            for(int i = 0; i< levelSize; i++){
                TreeNode currentNode = queue.poll();
                if(i +1 == levelSize){
                    result.add(currentNode.value);
                }
                if(currentNode.left != null) queue.add(currentNode.left);
                if(currentNode.right != null) queue.add(currentNode.right);
            }
        }
        return result;
    }

    public static List<Integer> rightSideViewOptimized(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public static void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.value);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);

    }

    private static List<Integer> leftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            for(int i = 0; i< levelSize; i++){
                TreeNode currentNode = queue.poll();
                if(i == 0){
                    result.add(currentNode.value);
                }
                if(currentNode.left != null) queue.add(currentNode.left);
                if(currentNode.right != null) queue.add(currentNode.right);
            }
        }
        return result;
    }

    public static List<Integer> leftSideViewOptimized(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        leftView(root, result, 0);
        return result;
    }

    public static void leftView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.value);
        }
        leftView(curr.left, result, currDepth + 1);
        leftView(curr.right, result, currDepth + 1);
    }
}
