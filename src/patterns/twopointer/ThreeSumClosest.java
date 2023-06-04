package patterns.twopointer;

import java.util.Arrays;
/*
* Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.



Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Example 2:

Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
* */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target){
        Arrays.sort(nums);
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < n - 2; i++){
            int start = i+1;
            int end = n -1;
            while(start < end){
                int curSum = nums[i] + nums[start] + nums[end];
                if(Math.abs(target - curSum) < minDiff){
                    minDiff = Math.abs(target - curSum);
                    res = curSum;
                }
                if(curSum == target){
                    return curSum;
                }else if(curSum > target){
                    end--;
                }else{
                    start++;
                }
            }
        }

        return res;
    }
    public static void main(String[] args) {
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        int[] nums = new int[]{-1,2,1,-4};
        System.out.println(threeSumClosest.threeSumClosest(nums, 1));
        System.out.println(
                threeSumClosest.threeSumClosest(new int[]{0, 0, 1, 1, 2, 6}, 5));
    }
}
