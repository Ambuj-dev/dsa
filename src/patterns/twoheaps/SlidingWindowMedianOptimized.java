package patterns.twoheaps;

import java.util.*;

public class SlidingWindowMedianOptimized {
    public static void main(String[] args) {
        SlidingWindowMedianOptimized slidingWindowMedian = new SlidingWindowMedianOptimized();
        // System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(new int[]{1,2,-1,3,5},2)));
        //slidingWindowMedian = new SlidingWindowMedianOptimized();
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(new int[]{1,2,-1,3,5},3)));
        slidingWindowMedian = new SlidingWindowMedianOptimized();
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(new int[]{2147483647,2147483647},2)));
        slidingWindowMedian = new SlidingWindowMedianOptimized();
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(new int[]{2147483647, 1, 2, 3, 4, 5, 6, 7, 2147483647}, 2)));
    }

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    Map<Integer, Integer> invalid;

    public double[] medianSlidingWindow(int[] nums, int k) {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        invalid = new HashMap<>();

        boolean isKEven = (k % 2 == 0);

        for (int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }

        for (int i = 0; i < k / 2; i++) {
            minHeap.add(maxHeap.poll());
        }

        double[] res = new double[nums.length - k + 1];
        res[0] = isKEven ? 0.5 * maxHeap.peek() + 0.5 * minHeap.peek() : maxHeap.peek();


        for (int i = k; i < nums.length; i++) {
            int inComing = nums[i];
            int outGoing = nums[i - k];
            balance(inComing, outGoing);
            res[i - k + 1] = isKEven ? 0.5 * maxHeap.peek() + 0.5 * minHeap.peek() : maxHeap.peek();
        }

        return res;

    }


    private void balance(int in, int out) {
        int balance = 0;
        if (in <= maxHeap.peek()) {
            balance += 1;
            maxHeap.offer(in);
        } else {
            balance -= 1;
            minHeap.offer(in);
        }

        if (out <= maxHeap.peek()) {
            invalid.put(out, invalid.getOrDefault(out, 0) + 1);
            balance -= 1;
        } else {
            invalid.put(out, invalid.getOrDefault(out, 0) + 1);
            balance += 1;
        }

        while (balance > 0) {
            minHeap.offer(maxHeap.poll());
            balance -= 2;
        }

        while (balance < 0) {
            maxHeap.offer(minHeap.poll());
            balance += 2;
        }

        while (invalid.getOrDefault(maxHeap.peek(), 0) > 0) {
            int cur = maxHeap.poll();
            invalid.put(cur, invalid.get(cur) - 1);
        }

        while (invalid.getOrDefault(minHeap.peek(), 0) > 0) {
            int cur = minHeap.poll();
            invalid.put(cur, invalid.get(cur) - 1);
        }
    }


}
