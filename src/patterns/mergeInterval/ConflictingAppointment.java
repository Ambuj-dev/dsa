package patterns.mergeInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ConflictingAppointment {
    public static void main(String[] args) {

        System.out.println(canAttendAllAppointments(Arrays.asList(new Interval(1,4), new Interval(2,5), new Interval(7,9))));//false, Since [1,4] and [2,5] overlap, a person cannot attend both of these appointments.
        System.out.println(canAttendAllAppointments(Arrays.asList(new Interval(6,7), new Interval(2,4), new Interval(8,12))));//true, None of the appointments overlap, therefore a person can attend all of them.
        System.out.println(canAttendAllAppointments(Arrays.asList(new Interval(4,5), new Interval(2,3), new Interval(3,6))));//false, Since [4,5] and [3,6] overlap, a person cannot attend both of these appointments.
        System.out.println(whatAreTheConflicts(Arrays.asList(new Interval(4,5), new Interval(2,3), new Interval(3,6), new Interval(5,7), new Interval(7,8))));

        Interval[] intervals = { new Interval(1, 4), new Interval(2, 5),
                new Interval(7, 9) };
        boolean result = ConflictingAppointment.canAttendAllAppointments(intervals);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals1 = { new Interval(6, 7), new Interval(2, 4),
                new Interval(8, 12) };
        result = ConflictingAppointment.canAttendAllAppointments(intervals1);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3),
                new Interval(3, 6) };
        result = ConflictingAppointment.canAttendAllAppointments(intervals2);
        System.out.println("Can attend all appointments: " + result);
    }

    private static boolean canAttendAllAppointments(List<Interval> intervals){
        intervals.sort(Comparator.comparingInt(Interval::getStart));
        for(int i = 1; i< intervals.size(); i++){
            if(intervals.get(i).getStart() < intervals.get(i-1).getEnd()){
                return false;
            }
        }
        return true;
    }


    public static boolean canAttendAllAppointments(Interval[] intervals) {
        // sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        // find any overlapping appointment
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                // please note the comparison above, it is "<" and not "<="
                // while merging we needed "<=" comparison, as we will be merging the two
                // intervals having condition "intervals[i].start == intervals[i - 1].end" but
                // such intervals don't represent conflicting appointments as one starts right
                // after the other
                return false;
            }
        }
        return true;
    }

    private static List<List<Interval>> whatAreTheConflicts(List<Interval> intervals){
        intervals.sort(Comparator.comparingInt(Interval::getStart));
        List<List<Interval>> results = new ArrayList<>();
        for(int i = 0; i < intervals.size() - 1; i++){
            for(int j = i+1; j < intervals.size(); j++){
                if(intervals.get(i).getEnd() > intervals.get(j).getStart()){
                    results.add(List.of(intervals.get(i), intervals.get(j)));
                }
            }
        }
        return results;
    }
}
