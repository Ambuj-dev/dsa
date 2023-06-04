package patterns.topkelement;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {
    public static void main(String[] args) {
        KthLargestElementInAStream kthLargestElementInAStream = new KthLargestElementInAStream(3, new int[]{4,5,8,2});
        System.out.println(kthLargestElementInAStream.add1(3));//4
        System.out.println(kthLargestElementInAStream.add1(5));//5
        System.out.println(kthLargestElementInAStream.add1(10));//5
        System.out.println(kthLargestElementInAStream.add1(9));//8
        System.out.println(kthLargestElementInAStream.add1(4));//8
    }

    private PriorityQueue<Integer> minHeap;
    private int k;
    private int[] nums;
    public KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        this.nums = nums;
        minHeap = new PriorityQueue<>();
        for(int num : nums){
            add1(num);
        }
    }

    public int add(int val) {
        if(minHeap.size() == k) {
            int topElement = minHeap.peek();
            if (val < topElement) return topElement;
        }

        minHeap.add(val);
        if(minHeap.size() > k){
            minHeap.poll();
        }
        return minHeap.peek();
    }



    public int add1(int num) {
        // add the new number in the min heap
            minHeap.add(num);

        // if heap has more than 'k' numbers, remove one number
        if (minHeap.size() > this.k)
            minHeap.poll();

        // return the 'Kth largest number
        return minHeap.peek();
    }
}
