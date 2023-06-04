package patterns.twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    private static PriorityQueue<Integer> minHeap;
    private static PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public static void addNum(int num) {
        if (maxHeap.size() == 0 || maxHeap.peek() >= num) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balance();
    }

    public static double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (maxHeap.size() < minHeap.size()) {
            return minHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }

    private static void balance() {
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        addNum(5);
        System.out.println(findMedian());
        addNum(3);
        System.out.println(findMedian());
        addNum(6);
        System.out.println(findMedian());
        addNum(9);
        System.out.println(findMedian());
        addNum(2);
        System.out.println(findMedian());
    }
}
