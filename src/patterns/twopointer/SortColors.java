package patterns.twopointer;

import java.util.Arrays;
import java.util.List;

/*
* Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
* */
public class SortColors {

    public int[] sortColors(int[] nums){

        int i = 0;
        int low = 0;
        int high = nums.length - 1;

        while(i < high){
            if(nums[i] == 0){
                swap(nums, i, low);
                i++;
                low++;
            }else if(nums[i] == 1){
                i++;
            }else{
                swap(nums, i, high);
                high--;
            }
        }

        return nums;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        int[] nums = {2,0,2,1,1,0};
        Arrays.stream(sortColors.sortColors(nums)).forEach(System.out::print);
    }
}
