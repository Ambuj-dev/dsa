package patterns.mergeInterval;

import java.util.*;
import java.util.stream.IntStream;

public class MergeInterval {

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 5));
        intervals.add(new Interval(3, 6));
        intervals.add(new Interval(7, 9));
        System.out.println(merge(intervals));
        System.out.println(merge1(intervals));
        System.out.println(merge2(intervals));

        Arrays.stream(mergeOptimized(new Interval[]{new Interval(1, 4), new Interval(2, 5), new Interval(3, 6), new Interval(7, 9)})).forEach(System.out::println);

        int[][] arr = new int[4][];
        arr[0] = new int[]{1, 4};
        arr[1] = new int[]{2, 5};
        arr[2] = new int[]{3, 6};
        arr[3] = new int[]{7, 9};

        int[][] results = merge(arr);
        for (int[] result : results) {
            System.out.println("(" + result[0] + " " + result[1]+")");
        }
        System.out.println(anyInterval(intervals));
        System.out.println(anyInterval(Arrays.asList(new Interval(1,2), new Interval(3,4))));
        System.out.println(anyInterval(Arrays.asList(new Interval(1,2))));

    }

    public static List<Interval> mergeInterval(List<Interval> intervals) {
        if (intervals.size() < 2)
            return intervals;

        // sort the intervals by start time
        Collections.sort(intervals, Comparator.comparingInt(Interval::getStart));

        List<Interval> mergedIntervals = new LinkedList<Interval>();
        Iterator<Interval> intervalItr = intervals.iterator();
        Interval interval = intervalItr.next();
        int start = interval.getStart();
        int end = interval.getEnd();

        while (intervalItr.hasNext()) {
            interval = intervalItr.next();
            if (interval.getStart() <= end) { // overlapping intervals, adjust the 'end'
                end = Math.max(interval.getEnd(), end);
            } else { // non-overlapping interval, add the previous interval and reset
                mergedIntervals.add(new Interval(start, end));
                start = interval.getStart();
                end = interval.getEnd();
            }
        }
        // add the last interval
        mergedIntervals.add(new Interval(start, end));

        return mergedIntervals;
    }


    private static List<Interval> merge(List<Interval> intervals) {
        if (Objects.nonNull(intervals) && intervals.size() < 2) {
            return intervals;
        }
        intervals.sort(Comparator.comparingInt(Interval::getStart));
        List<Interval> mergedIntervals = new ArrayList<>();
        int start = intervals.get(0).getStart();
        int end = intervals.get(0).getEnd();
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.getStart() < end) {
                end = Math.max(end, interval.getEnd());
            } else {
                mergedIntervals.add(new Interval(start, end));
                start = interval.getStart();
                end = interval.getEnd();
            }
        }
        mergedIntervals.add(new Interval(start, end));
        return mergedIntervals;
    }

    private static List<Interval> merge1(List<Interval> intervals) {
        if (Objects.nonNull(intervals) && intervals.size() < 2) {
            return intervals;
        }
        intervals.sort(Comparator.comparingInt(Interval::getStart));
        List<Interval> mergedIntervals = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (mergedIntervals.isEmpty() || mergedIntervals.get(mergedIntervals.size() - 1).getEnd() < interval.getStart()) {
                mergedIntervals.add(interval);
            } else {
                mergedIntervals.get(mergedIntervals.size() - 1).setEnd(Math.max(mergedIntervals.get(mergedIntervals.size() - 1).getEnd(), interval.getEnd()));
            }

        }
        return mergedIntervals;
    }

    private static List<Interval> merge2(List<Interval> intervals) {
        if (Objects.nonNull(intervals) && intervals.size() < 2) {
            return intervals;
        }
        intervals.sort(Comparator.comparingInt(Interval::getStart));
        LinkedList<Interval> mergedIntervals = new LinkedList<>();
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (mergedIntervals.isEmpty() || mergedIntervals.getLast().getEnd() < interval.getStart()) {
                mergedIntervals.add(interval);
            } else {
                mergedIntervals.getLast().setEnd(Math.max(mergedIntervals.getLast().getEnd(), interval.getEnd()));
            }

        }
        return mergedIntervals;
    }
    private static Interval[] mergeOptimized(Interval[] intervals) {
        if (Objects.nonNull(intervals) && intervals.length < 2) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(Interval::getStart));
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[index].getEnd() >= intervals[i].getStart()) {
                intervals[index].setEnd(Math.max(intervals[index].getEnd(), intervals[i].getEnd()));
            } else {
                intervals[++index] = intervals[i];
            }
        }
        return Arrays.copyOfRange(intervals, 0, index + 1);
    }

    private static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    private static boolean anyInterval(List<Interval> intervals){
        if (Objects.nonNull(intervals) && intervals.size() < 2) {
            return false;
        }
        Collections.sort(intervals, Comparator.comparingInt(Interval::getStart));
        for(int i = 1; i< intervals.size(); i++){
            Interval current = intervals.get(i);
            Interval previous = intervals.get(i-1);
            if(previous.getEnd() >= current.getStart()){
                return true;
            }
        }

        return false;
    }


}
