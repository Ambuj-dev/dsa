package patterns.mergeInterval;

import java.util.*;

public class MinimumMeetingRoom1 {
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        if (meetings == null || meetings.size() == 0)
            return 0;

        // sort the meetings by start time
        Collections.sort(meetings, Comparator.comparingInt(a -> a.start));

        int minRooms = 0;
        PriorityQueue<Meeting> minHeap =
                new PriorityQueue<>(meetings.size(), Comparator.comparingInt(a -> a.end));
        for (Meeting meeting : meetings) {
            // remove all meetings that have ended
            while (!minHeap.isEmpty() && meeting.start >= minHeap.peek().end)
                minHeap.poll();
            // add the current meeting into the minHeap
            minHeap.offer(meeting);
            // all active meeting are in the minHeap, so we need rooms for all of them.
            minRooms = Math.max(minRooms, minHeap.size());
        }
        return minRooms;
    }

    public static void main(String[] args) {
        List<Meeting> input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        int result = MinimumMeetingRoom1.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = MinimumMeetingRoom1.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = MinimumMeetingRoom1.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        result = MinimumMeetingRoom1.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);
    }
}

class Meeting {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
};
