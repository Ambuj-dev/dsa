package patterns.kwaymerge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCoveringElementsFromKLists {
    public static void main(String[] args) {
        List<List<Integer>> list = Arrays.asList(Arrays.asList(4,10,15,24,26), Arrays.asList(0,9,12,20), Arrays.asList(5,18,22,30));
        System.out.println(Arrays.toString(smallestRange(list)));
        Integer[] l1 = new Integer[] { 1, 5, 8 };
        Integer[] l2 = new Integer[] { 4, 12 };
        Integer[] l3 = new Integer[] { 7, 8, 10 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int[] result = SmallestRangeCoveringElementsFromKLists.findSmallestRange(lists);
        System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        int max = Integer.MIN_VALUE;
        int[] res = {-100000, 100000};
        for(int i = 0; i< nums.size(); i++){
            int value = nums.get(i).get(0);
            max = Math.max(max, value);
            minHeap.add(new int[]{value, 0, i});
        }

        while(true){
            int[] min = minHeap.poll();
            if(res[1] - res[0] > max - min[0]){
                res[0] = min[0];
                res[1] = max;
            }
            min[1]++;
            List<Integer> list = nums.get(min[2]);
            if(min[1] == list.size()) break;
            min[0] = list.get(min[1]);
            minHeap.add(min);
            max = Math.max(max, min[0]);
        }
        return res;
    }

    //TC: O(N*logM) where N is the total number of elements in all the M input arrays
    //SC: O(M)
    public static int[] findSmallestRange(List<Integer[]> lists) {
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(
                (n1, n2) -> lists.get(n1.arrayIndex)[n1.elementIndex] -
                        lists.get(n2.arrayIndex)[n2.elementIndex]);

        int rangeStart = 0, rangeEnd = Integer.MAX_VALUE,
                currentMaxNumber = Integer.MIN_VALUE;
        // put the 1st element of each array in the min heap
        for (int i = 0; i < lists.size(); i++)
            if (lists.get(i) != null) {
                minHeap.add(new Node(0, i));
                currentMaxNumber = Math.max(currentMaxNumber, lists.get(i)[0]);
            }

        // take the smallest (top) element from the min heap, if it gives us smaller range,
        // update the ranges, if the array of the top element has more elements, insert the
        // next element in the heap
        while (minHeap.size() == lists.size()) {
            Node node = minHeap.poll();
            if (rangeEnd - rangeStart >
                    currentMaxNumber - lists.get(node.arrayIndex)[node.elementIndex]) {
                rangeStart = lists.get(node.arrayIndex)[node.elementIndex];
                rangeEnd = currentMaxNumber;
            }
            node.elementIndex++;
            if (lists.get(node.arrayIndex).length > node.elementIndex) {
                minHeap.add(node); // insert the next element in the heap
                currentMaxNumber = Math.max(currentMaxNumber,
                        lists.get(node.arrayIndex)[node.elementIndex]);
            }
        }
        return new int[] { rangeStart, rangeEnd };
    }

}
