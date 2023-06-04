package patterns.topkelement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TopKElements {
    public static void main(String[] args) {
        int arr[] = {3,2,1,5,6,4};
        System.out.println(Arrays.toString(topKElements(arr, 4)));
        List<Integer> result = TopKElements.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);

        result = TopKElements.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);

    }
    public static int[] topKElements(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num: nums){
            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        int[] res = new int[k];
        int index = 0;
        while(!minHeap.isEmpty()){
            res[index++] = minHeap.poll();
        }
        return res;
    }

    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // put first 'K' numbers in the min heap
        for (int i = 0; i < k; i++)
            minHeap.add(nums[i]);

        // go through the remaining numbers of the array, if the number from the array is
        // bigger than the top (smallest) number of the min-heap, remove the top number from
        // heap and add the number from array
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }

        // the heap has the top 'K' numbers, return them in a list
        return new ArrayList<>(minHeap);
    }

}
