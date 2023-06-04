package patterns.topkelement;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestElement {

    public static void main(String[] args) {
        int arr[] = {3,2,1,5,6,4};
        int[] arr1 = {3,2,3,1,2,4,5,5,6};
       // System.out.println(findKthLargest(arr, 2));
       // System.out.println(findKthLargest(arr1, 4));
        System.out.println(findKthLargestMinHeap(arr, 2));
        System.out.println(findKthLargestMinHeap(arr1, 4));
        System.out.println(findKthLargestMaxHeap(arr, 2));
        System.out.println(findKthLargestMaxHeap(arr1, 4));
        //System.out.println(findKthLargestQuickSelect(arr, 2));
        System.out.println(findKthLargestQuickSelect(arr1, 4));
    }

    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    public static int findKthLargestMinHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num: nums){
            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    public static int findKthLargestMaxHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int num: nums){
            maxHeap.offer(num);
        }
        int i = 0;
        while(i< k-1){
            maxHeap.poll();
            i++;
        }
        return maxHeap.peek();
    }
    public static int findKthLargestQuickSelect(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length-1, nums.length-k);
    }

    private static int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];

        int pIndex = new Random().nextInt(right - left + 1) + left;
        pIndex = partition(nums, left, right, pIndex);

        if (pIndex == k) return nums[k];
        else if (pIndex < k) return quickSelect(nums, pIndex+1, right, k);
        return quickSelect(nums, left, pIndex-1, k);
    }

    private static int partition(int[] nums, int left, int right, int pIndex) {
        int pivot = nums[pIndex];
        swap(nums, pIndex, right);
        pIndex = left;

        for (int i=left; i<=right; i++)
            if (nums[i] <= pivot)
                swap(nums, i, pIndex++);

        return pIndex - 1;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

}
