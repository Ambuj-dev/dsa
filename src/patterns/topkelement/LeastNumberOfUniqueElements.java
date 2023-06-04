package patterns.topkelement;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeastNumberOfUniqueElements {

    public static void main(String[] args) {
        System.out.println(findLeastNumOfUniqueInts(new int[]{4,5,1,1,3,3,2}, 4));
        System.out.println(findLeastNumOfUniqueIntsHeap(new int[]{4,5,1,1,3,3,2}, 3));
    }

    public static int findLeastNumOfUniqueIntsHeap(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : arr)
            count.put(a, 1 + count.getOrDefault(a, 0));
        PriorityQueue<Integer> pq = new PriorityQueue<>(count.values());
        while (k > 0)
            k -= pq.poll();
        return k < 0 ? pq.size() + 1 : pq.size();
    }

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : arr)
            count.put(a, 1 + count.getOrDefault(a, 0));
        int remaining = count.size(), occur = 1;
        int[] occurrenceCount = new int[arr.length + 1];
        for (int v : count.values())
            ++occurrenceCount[v];
        while (k > 0) {
            if (k - occur * occurrenceCount[occur] >= 0) {
                k -= occur * occurrenceCount[occur];
                remaining -= occurrenceCount[occur++];
            }else {
                return remaining - k / occur;
            }
        }
        return remaining;
    }
}
