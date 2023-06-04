package patterns.topkelement;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosest {
    public static void main(String[] args) {
        int[][] input = {{1,3},{-2,2}};
        for(int[] res: kClosest(input, 1)){
            System.out.println(Arrays.toString(res));
        }
        int[][] input1 = {{3,3},{5,-1},{-2,4}};
        for(int[] res: kClosest(input1, 2)){
            System.out.println(Arrays.toString(res));
        }

    }
    public static int[][] kClosest(int[][] p, int k) {

        PriorityQueue<Node> maxHeap = new PriorityQueue<>((a,b) -> b.distance - a.distance);
        for(int[] point : p){
            int distance = point[0]*point[0] + point[1] * point[1];
            maxHeap.add(new Node(distance, point[0], point[1]));
            if(maxHeap.size() >k){
                maxHeap.poll();
            }
        }
        int[][] res = new int[k][2];
        int index = 0;
        while(!maxHeap.isEmpty()){
            Node node = maxHeap.poll();
            res[index][0] = node.x;
            res[index][1] = node.y;
            index++;
        }
        return res;

    }
    static class Node{
        int distance;
        int x;
        int y;

        public Node(int distance, int x, int y) {
            this.distance = distance;
            this.x = x;
            this.y = y;
        }
    }
}
