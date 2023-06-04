package patterns.mergeInterval;

import java.util.ArrayList;
import java.util.List;

public class IntervalsIntersection {

    public static void main(String[] args) {
        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
        int[][]  secondList = {{1,5},{8,12},{15,24},{25,26}};
        int[][]results = intervalIntersection(firstList, secondList);
        for (int[] result : results) {
            System.out.println("(" + result[0] + " " + result[1]+")");
        }

        Interval[] input1 = new Interval[] { new Interval(1, 3), new Interval(5, 6),
                new Interval(7, 9) };
        Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
        Interval[] result = IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input1 = new Interval[] { new Interval(1, 3), new Interval(5, 7),
                new Interval(9, 12) };
        input2 = new Interval[] { new Interval(5, 10) };
        result = IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i=0, j=0;

        List<int[]> res = new ArrayList<>();
        while( i< firstList.length && j < secondList.length){
            boolean firstListInSecond = firstList[i][0] >= secondList[j][0] && firstList[i][0] <= secondList[j][1];
            boolean secondListInFirst = secondList[j][0] >= firstList[i][0] && secondList[j][0] <= firstList[i][1];
            if(firstListInSecond || secondListInFirst){
                res.add(new int[]{
                        Math.max(firstList[i][0], secondList[j][0]),
                        Math.min(firstList[i][1], secondList[j][1])
                });
            }
            if(firstList[i][1] < secondList[j][1]) i++;
            else j++;
        }
        return res.toArray(new int[res.size()][]);
    }

    public static Interval[] merge(Interval[] arr1, Interval[] arr2) {
        List<Interval> result = new ArrayList<Interval>();
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            // check if the interval arr[i] intersects with arr2[j]
            // check if one of the interval's start time lies within the other interval
            if ((arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end)
                    || (arr2[j].start >= arr1[i].start && arr2[j].start <= arr1[i].end)) {
                // store the intersection part
                result.add(new Interval(Math.max(arr1[i].start, arr2[j].start),
                        Math.min(arr1[i].end, arr2[j].end)));
            }

            // move next from the interval which is finishing first
            if (arr1[i].end < arr2[j].end)
                i++;
            else
                j++;
        }

        return result.toArray(new Interval[result.size()]);
    }


}
