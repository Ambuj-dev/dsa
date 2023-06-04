package patterns.cyclicsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMissingNumbers {
    public static void main(String[] args) {
        System.out.println(findMissingNumber(Arrays.asList(3, 1, 5, 4, 0)));
        System.out.println(findMissingNumber(Arrays.asList(4, 0, 1, 3)));
        System.out.println(findMissingNumber(Arrays.asList(8, 3, 5, 2, 4, 6, 0, 1)));
        System.out.println(findAllMissingNumber(Arrays.asList(2,3,1,8,2,3,5,1)));
        System.out.println(findAllMissingNumber(Arrays.asList(2,4,1,2)));
        System.out.println(findAllMissingNumber(Arrays.asList(2,3,2,1)));

        System.out.println(FindMissingNumbers.findMissingNumber(new int[] { 4, 0, 3, 1 }));
        System.out.println(FindMissingNumbers.findMissingNumber(
                new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));

        List<Integer> missing = FindMissingNumbers.findNumbers(
                new int[] { 2, 3, 1, 8, 2, 3, 5, 1 });
        System.out.println("Missing numbers: " + missing);

        missing = FindMissingNumbers.findNumbers(new int[] { 2, 4, 1, 2 });
        System.out.println("Missing numbers: " + missing);

        missing = FindMissingNumbers.findNumbers(new int[] { 2, 3, 2, 1 });
        System.out.println("Missing numbers: " + missing);

    }

    public static int findMissingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] < nums.length && nums[i] != nums[nums[i]])
                swap(nums, i, nums[i]);
            else
                i++;
        }

        // find the first number missing from its index, that will be our required number
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i)
                return i;

        return nums.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static  int findMissingNumber(List<Integer> nums){
        int n = nums.size();
        cyclicSort(nums, n);
        for(int i =0; i< n;i++){
            if(nums.get(i) != i){
                return i;
            }
        }
        return n;
    }
    public static List<Integer> cyclicSort(List<Integer> nums, int n){
        int i=0;
        while(i< n){
            int j = nums.get(i);
            if( nums.get(i) < n && nums.get(j) != nums.get(i)){
                int temp = nums.get(i);
                nums.set(i, nums.get(j));
                nums.set(j, temp);
            }else{
                i++;
            }

        }
        return nums;
    }

    private static List<Integer> findAllMissingNumber(List<Integer> nums){
        int n = nums.size();
        CyclicSort.cyclicSort(nums);
        List<Integer> result = new ArrayList<>();
        for(int i =0; i< n;i++){
            if(nums.get(i) != (i+1)){
                result.add(i+1);
            }
        }
        return result;
    }

    public static List<Integer> findNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }

        List<Integer> missingNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                missingNumbers.add(i + 1);

        return missingNumbers;
    }


}
