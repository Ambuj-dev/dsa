package patterns.mergeInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    public static void main(String[] args) {
        System.out.println(insert(Arrays.asList(new Interval(1,3), new Interval(4,6), new Interval(8,10)), new Interval(4,7)));
        System.out.println(insert(Arrays.asList(new Interval(1,3), new Interval(4,6), new Interval(8,10)), new Interval(4,6)));
        System.out.println(insert(Arrays.asList(new Interval(1,3), new Interval(4,6), new Interval(8,10)), new Interval(9,12)));
        System.out.println(insert(Arrays.asList(new Interval(1,3), new Interval(4,6), new Interval(8,10)), new Interval(11,12)));

        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insertInterval(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insertInterval(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insertInterval(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

    }
    private static List<Interval> insert(List<Interval> intervals, Interval newInterval){
        List<Interval> mergedInterval = new ArrayList<>();
        int index = 0;
        while(index < intervals.size() && intervals.get(index).getEnd() < newInterval.getStart()){
            mergedInterval.add(intervals.get(index++));
        }
        while(index < intervals.size() && intervals.get(index).getStart() <= newInterval.getEnd()){
            newInterval.setStart(Math.min(newInterval.getStart(), intervals.get(index).getStart()));
            newInterval.setEnd(Math.max(newInterval.getEnd(), intervals.get(index).getEnd()));
            index++;
        }
        mergedInterval.add(newInterval);
        while(index < intervals.size()){
            mergedInterval.add(intervals.get(index++));
        }

        return mergedInterval;
    }

    public static List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.isEmpty())
            return Arrays.asList(newInterval);

        List<Interval> mergedIntervals = new ArrayList<>();

        int i = 0;
        // skip (and add to output) all intervals that come before the 'newInterval'
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            mergedIntervals.add(intervals.get(i++));

        // merge all intervals that overlap with 'newInterval'
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            i++;
        }

        // insert the newInterval
        mergedIntervals.add(newInterval);

        // add all the remaining intervals to the output
        while (i < intervals.size())
            mergedIntervals.add(intervals.get(i++));

        return mergedIntervals;
    }


}
