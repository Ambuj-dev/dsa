package patterns.mergeInterval;

import java.util.*;

public class MaxCPULoad {
    public static void main(String[] args) {
        int[][] input = {{1,4,3}, {2,5,4}, {7,9,6}};
        int[][] input1 = {{6,7,10}, {2,4,11}, {8,12,15}};
        int[][] input2 = {{1,4,2}, {2,4,1}, {3,6,5}};
        System.out.println(maxCPULoad(input));
        System.out.println(maxCPULoad(input1));
        System.out.println(maxCPULoad(input2));

    }

    private static int maxCPULoad(int[][] intervals){
        int maxCPULoad = 0;
        Arrays.sort(intervals, Comparator.comparingInt(start -> start[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.set(merged.size()-1,new int[]{merged.getLast()[0], interval[1], merged.getLast()[2] + interval[2]});
            }
        }
        for(int[] job : merged){
            maxCPULoad = Math.max(maxCPULoad, job[2]);
        }

        return maxCPULoad;
    }
}
