package patterns.cyclicsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCorruptPair {
    public static void main(String[] args) {
        System.out.println(findCorruptPair(Arrays.asList(1, 4, 4, 3, 2)));
        System.out.println(findCorruptPair(Arrays.asList(2, 1, 3, 3, 5, 4)));
        System.out.println(findCorruptPair(Arrays.asList(2, 4, 1, 4, 4)));

        int[] nums = FindCorruptPair.findNumbers(new int[] { 3, 1, 2, 5, 2 });
        System.out.println(nums[0] + ", " + nums[1]);
        nums = FindCorruptPair.findNumbers(new int[] { 3, 1, 2, 3, 6, 4 });
        System.out.println(nums[0] + ", " + nums[1]);

    }

    private static List<Pair> findCorruptPair(List<Integer> nums){
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
        List<Pair> result = new ArrayList<>();
        for(i =0; i< nums.size(); i++){
            if(nums.get(i) != i+1){
                result.add(new Pair(nums.get(i), i+1));
            }
        }
        return result;
    }

    public static int[] findNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }

        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                return new int[] { nums[i], i + 1 };

        return new int[] { -1, -1 };
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
