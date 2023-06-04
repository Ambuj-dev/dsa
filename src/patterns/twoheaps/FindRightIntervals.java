package patterns.twoheaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindRightIntervals {

    public static void main(String[] args) {
        int[][] input = new int[][]{{1,4}, {2,3}, {3,4}};
        System.out.println(Arrays.toString(findRightInterval(input)));
    }

    public static int[] findRightInterval(int[][] intervals) {
        PriorityQueue<int[]> startMinHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> endMinHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < intervals.length; i++) {
            startMinHeap.add(new int[]{intervals[i][0], i});
            endMinHeap.add(new int[]{intervals[i][1], i});
        }
        int[] results = new int[intervals.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = -1;
        }

        while (!endMinHeap.isEmpty()) {
            int[] curEnd = endMinHeap.poll();
            int curEndVal = curEnd[0];
            int curEndIndex = curEnd[1];
            while (!startMinHeap.isEmpty() && curEndVal > startMinHeap.peek()[0])
                startMinHeap.poll();

            if (startMinHeap.isEmpty()) return results;
            results[curEndIndex] = startMinHeap.peek()[1];
        }
        return results;
    }
}
