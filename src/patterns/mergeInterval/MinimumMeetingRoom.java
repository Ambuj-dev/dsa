package patterns.mergeInterval;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MinimumMeetingRoom {

    public static void main(String[] args) {
        int lectures[][]
                = { { 0, 5 }, { 1, 2 }, { 1, 10 } };
        int n = lectures.length;
        int lectures1[][]
                = { { 1, 4 }, { 2, 5 }, { 7, 9 } };


        System.out.println(minHalls(lectures, n));
        System.out.println(minHalls(lectures1, lectures1.length));

    }

    private static int minMeetingRoom(List<Interval> meetings){
        return 0;
    }
    static int minHalls(int lectures[][], int n)
    {

        // Initialize a vector of pair, times, first value
        // indicates the times of entry or exit of a lecture
        // second value denotes whether the lecture starts
        // or ends

        ArrayList<Pair> times = new ArrayList<>();

        // Store the lecture times
        for (int i = 0; i < n; i++) {
            times.add(new Pair(lectures[i][0], 1));
            times.add(new Pair(lectures[i][1], -1));
        }
        times.sort(Comparator.comparingInt(pair -> pair.first));


        int answer = 0, sum = 0;

        // Traverse the times vector and Update sum and
        // answer variables
        for (int i = 0; i < times.size(); i++) {
            sum += times.get(i).second;
            answer = Math.max(answer, sum);
        }

        // Return the answer
        return answer;
    }


    static class Pair {
        int first, second;
        Pair(int x, int y)
        {
            first = x;
            second = y;
        }
    }



}
