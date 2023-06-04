package patterns.modifiedbinarysearch;

import java.util.Arrays;

public class SearchRange {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{1,2,2,2,5,7}, 2)));
        System.out.println(Arrays.toString(searchRange(new int[]{1,2,2,2,5,7}, 5)));
        System.out.println(Arrays.toString(searchRange(new int[]{1,2,2,2,5,7}, 3)));
    }
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if(nums == null || nums.length == 0) return result;
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private static int findFirst(int[] nums, int target){
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] == target) {
                idx = mid;
                end = mid - 1;
            } else if(nums[mid] > target){
                end = mid - 1;
            }else{
                start = mid + 1;
            }

        }
        return idx;
    }

    private static int findLast(int[] nums, int target){
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] == target){
                start = mid + 1;
                idx = mid;
            } else if(nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }

        }
        return idx;
    }

}
