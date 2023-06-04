package patterns.cyclicsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindDuplicateNumber {
    public static void main(String[] args) {
        System.out.println(findDuplicate(Arrays.asList(1, 4, 4, 3, 2)));
        System.out.println(findDuplicate(Arrays.asList(2, 1, 3, 3, 5, 4)));
        System.out.println(findDuplicate(Arrays.asList(2, 4, 1, 4, 4)));

        System.out.println(FindDuplicateNumber.findNumber(new int[] { 1, 4, 4, 3, 2 }));
        System.out.println(FindDuplicateNumber.findNumber(new int[] { 2, 1, 3, 3, 5, 4 }));
        System.out.println(FindDuplicateNumber.findNumber(new int[] { 2, 4, 1, 4, 4 }));


        System.out.println(findAllDuplicates(Arrays.asList(1, 4, 4, 3, 2)));
        System.out.println(findAllDuplicates(Arrays.asList(2, 1, 3, 3, 5, 4)));
        System.out.println(findAllDuplicates(Arrays.asList(2, 4, 1, 4, 4)));


    }
    private static int findDuplicate(List<Integer> nums){
        int i = 0;
        while(i < nums.size()){
            if(nums.get(i) != i+1) {
                int j = nums.get(i) - 1;
                if (nums.get(i) != nums.get(j)) {
                    int temp = nums.get(i);
                    nums.set(i, nums.get(j));
                    nums.set(j, temp);
                } else {
                    return nums.get(i);
                }
            }else{
                i++;
            }
        }
        return -1;
    }

    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1) {
                if (nums[i] != nums[nums[i] - 1])
                    swap(nums, i, nums[i] - 1);
                else // we have found the duplicate
                    return nums[i];
            } else {
                i++;
            }
        }

        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static List<Integer> findAllDuplicates(List<Integer> nums){
        int i = 0;
        while(i < nums.size()){
                int j = nums.get(i) - 1;
                if (nums.get(i) != nums.get(j)) {
                    int temp = nums.get(i);
                    nums.set(i, nums.get(j));
                    nums.set(j, temp);
                } else {
                    i++;
                }
        }
        List<Integer> result = new ArrayList<>();
        for(i =0; i< nums.size(); i++){
            if(nums.get(i) != i+1){
                result.add(nums.get(i));
            }
        }
        return result;
    }

}
