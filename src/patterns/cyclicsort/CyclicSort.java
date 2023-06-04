package patterns.cyclicsort;

import java.util.Arrays;
import java.util.List;

public class CyclicSort {
    public static void main(String[] args) {
        System.out.println(cyclicSort(Arrays.asList(3, 1, 5, 4, 2)));
        int[] arr = new int[] { 3, 1, 5, 4, 2 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 1, 5, 6, 4, 3, 2 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void sort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] != nums[j])
                swap(nums, i, j);
            else
                i++;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static List<Integer> cyclicSort(List<Integer> nums){
        int i=0;
        while(i< nums.size()){
            int j = nums.get(i) - 1;
            if(nums.get(j) != nums.get(i)){
                int temp = nums.get(i);
                nums.set(i, nums.get(j));
                nums.set(j, temp);
            }else{
                i++;
            }

        }
        return nums;
    }
}
