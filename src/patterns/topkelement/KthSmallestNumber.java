package patterns.topkelement;

import java.util.PriorityQueue;
import java.util.Random;

public class KthSmallestNumber {
    public static int findKthSmallestNumber(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> n2 - n1);
        // put first k numbers in the max heap
        for (int i = 0; i < k; i++)
            maxHeap.add(nums[i]);

        // go through the remaining numbers of the array, if the number from the array is
        // smaller than the top (biggest) number of the heap, remove the top number from
        // heap and add the number from array
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(nums[i]);
            }
        }

        // the root of the heap has the Kth smallest number
        return maxHeap.peek();
    }

    public static int findKthSmallestNumberQuickSelect(int[] nums, int k) {
        return findKthSmallestNumberRec(nums, k, 0, nums.length - 1);
    }

    public static int findKthSmallestNumberRec(int[] nums, int k, int start, int end) {
        int p = partition(nums, start, end);

        if (p == k - 1)
            return nums[p];

        if (p > k - 1) // search lower part
            return findKthSmallestNumberRec(nums, k, start, p - 1);

        // search higher part
        return findKthSmallestNumberRec(nums, k, p + 1, end);
    }

    private static int partition(int[] nums, int low, int high) {
        if (low == high)
            return low;

        Random randomNum = new Random();
        int pivotIndex = low + randomNum.nextInt(high - low);
        swap(nums, pivotIndex, high);

        int pivot = nums[high];

        for (int i = low; i < high; i++) {
            // all elements less than 'pivot' will be before the index 'low'
            if (nums[i] <= pivot)
                swap(nums, low++, i);
        }
        // put the pivot in its correct place
        //swap(nums, low, high);
        return low;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int result =
                KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
        System.out.println("Kth smallest number is: " + result);

        // since there are two 5s in the input array, our 3rd and 4th smallest numbers
        // should be a '5'
        result =
                KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        System.out.println("Kth smallest number is: " + result);

        result =
                KthSmallestNumber.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Kth smallest number is: " + result);

        result =
                KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
        System.out.println("Kth smallest number is: " + result);

        // as there're two 5s in input array, our 3rd and 4th smallest numbers should be '5'
        result = KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        System.out.println("Kth smallest number is: " + result);

        result = KthSmallestNumber.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Kth smallest number is: " + result);

    }

}
