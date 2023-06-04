package patterns.topkelement;

import java.util.Collections;
import java.util.PriorityQueue;

public class SumOfElements {
    //NlogK2
    static long sumBetweenTwoKth(long arr[], long n, long k1, long k2) {
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(
                Collections.reverseOrder());
        for (int i = 0; i < n; i++) {

            maxHeap.add(arr[i]);

            if (maxHeap.size() >= k2) {
                maxHeap.poll();
            }
        }
        long ans = 0;
        while (maxHeap.size() > k1) {
            ans += maxHeap.poll();
        }

        return ans;
    }
//NlogN
    public static int findSumOfElements(int[] nums, int k1, int k2) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // insert all numbers to the min heap
        for (int i = 0; i < nums.length; i++)
            minHeap.add(nums[i]);

        // remove k1 small numbers from the min heap
        for (int i = 0; i < k1; i++)
            minHeap.poll();

        int elementSum = 0;
        // sum next k2-k1-1 numbers
        for (int i = 0; i < k2 - k1 - 1; i++)
            elementSum += minHeap.poll();

        return elementSum;
    }

    public static void main(String[] args) {
        long arr[] = {20, 8, 22, 4, 12, 10, 14};
        long k1 = 3, k2 = 6;
        long n = arr.length;
        System.out.println(
                sumBetweenTwoKth(arr, n, k1, k2));
        long result1 =
                SumOfElements.sumBetweenTwoKth(new long[] { 1, 3, 12, 5, 15, 11 }, 6l, 3l, 6l);
        System.out.println(
                "Sum of all numbers between k1 and k2 smallest numbers: " + result1);


         int result =
                SumOfElements.findSumOfElements(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6);
        System.out.println(
                "Sum of all numbers between k1 and k2 smallest numbers: " + result);

        result = SumOfElements.findSumOfElements(new int[] { 3, 5, 8, 7 }, 1, 4);
        System.out.println(
                "Sum of all numbers between k1 and k2 smallest numbers: " + result);
    }
}
