package patterns.mergeInterval;

import java.util.*;

public class EmployeeFreeTime {
    public static void main(String[] args) {
        System.out.println(employeeFreeTime(Arrays.asList(Arrays.asList(new Interval(1, 3), new Interval(5, 6)), Arrays.asList(new Interval(2, 3), new Interval(6, 8)))));
        System.out.println(employeeFreeTime(Arrays.asList(Arrays.asList(new Interval(1, 3), new Interval(9, 12)), Arrays.asList(new Interval(2, 4)), Arrays.asList(new Interval(6, 8)))));
        System.out.println(employeeFreeTime(Arrays.asList(Arrays.asList(new Interval(1, 3), new Interval(2, 4)), Arrays.asList(new Interval(3, 5), new Interval(7, 9)))));

        List<List<Interval>> input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
                new Interval(5, 6))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3),
                new Interval(6, 8))));
        List<Interval> result = EmployeeFreeTime.findEmployeeFreeTime(input);//[3,5]
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3),
                new Interval(9, 12))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);//[4,6][8,9]
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5),
                new Interval(7, 9))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);//[5,7]
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
    }
    //N*logN
    private static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparingInt(Interval::getStart));
        for (List<Interval> period : schedule) {
            for (Interval interval : period) {
                pq.add(interval);
            }
        }

        Interval previous = pq.poll();
        while (!pq.isEmpty()) {
            Interval current = pq.poll();
            if (previous.getEnd() >= current.getStart()) {
                previous.setEnd(Math.max(previous.getEnd(), current.getEnd()));
            } else {
                res.add(new Interval(previous.getEnd(), current.getStart()));
                previous = current;
            }
        }

        return res;

    }
    //N*logK
    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        // PriorityQueue to store one interval from each employee
        PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.interval.start, b.interval.start));

        // insert the first interval of each employee to the queue
        for (int i = 0; i < schedule.size(); i++)
            minHeap.offer(new EmployeeInterval(schedule.get(i).get(0), i, 0));

        Interval previousInterval = minHeap.peek().interval;
        while (!minHeap.isEmpty()) {
            EmployeeInterval queueTop = minHeap.poll();
            // if previousInterval is not overlapping with the next interval, insert a free
            // interval
            if (previousInterval.end < queueTop.interval.start) {
                result.add(new Interval(previousInterval.end, queueTop.interval.start));
                previousInterval = queueTop.interval;
            } else { // overlapping intervals, update the previousInterval if needed
                if (previousInterval.end < queueTop.interval.end)
                    previousInterval = queueTop.interval;
            }

            // if there are more intervals available for the same employee, add their next
            // interval
            List<Interval> employeeSchedule = schedule.get(queueTop.employeeIndex);
            if (employeeSchedule.size() > queueTop.intervalIndex + 1) {
                minHeap.offer(new EmployeeInterval(
                        employeeSchedule.get(queueTop.intervalIndex + 1), queueTop.employeeIndex,
                        queueTop.intervalIndex + 1));
            }
        }

        return result;
    }
}
class EmployeeInterval {
    Interval interval; // interval representing employee's working hours
    int employeeIndex; // index of the list containing working hours of this employee
    int intervalIndex; // index of the interval in the employee list

    public EmployeeInterval(Interval interval, int employeeIndex, int intervalIndex) {
        this.interval = interval;
        this.employeeIndex = employeeIndex;
        this.intervalIndex = intervalIndex;
    }
};


