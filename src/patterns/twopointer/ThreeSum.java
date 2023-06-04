package patterns.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
* Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.



Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
* */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i< nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            allTwoSumDuplicateSorted(nums, i+1, -nums[i], res);
        }
        return res;
    }

    public List<List<Integer>> allTwoSumSorted(int[] nums, int start, int target, List<List<Integer>> res){

        int end  = nums.length - 1;
        while(start < end){
            int curSum = nums[start] + nums[end];
            if(curSum == target){
                res.add(List.of(-target, nums[start], nums[end]));
                start++;
                end--;
            }else if(curSum < target){
                start++;
            }else{
                end--;
            }
        }
        return res;
    }
    public List<List<Integer>> allTwoSumDuplicateSorted(int[] nums, int start, int target, List<List<Integer>> res){

        int end  = nums.length - 1;
        while(start < end){
            int curSum = nums[start] + nums[end];
            if(curSum == target){
                res.add(List.of(-target, nums[start], nums[end]));
                while(start < end && nums[start] == nums[start + 1]){
                    start++;
                }
                while(start < end && nums[end] == nums[end - 1]){
                    end--;
                }
                start++;
                end--;
            }else if(curSum < target){
                start++;
            }else{
                end--;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for(int  i = 0 ; i < nums.length - 2; i++)
        {
            if( i==0 || (i > 0 && nums[i] != nums[i - 1]))
            {
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
                while(lo < hi)
                {
                    if(nums[lo] + nums[hi] == sum)
                    {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

                        while(lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while(lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    }
                    else if(nums[lo] + nums[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        int[] nums  = new int[]{-5, -3,-3, -3, 0, 1, 2, 2, 2, 4};
        System.out.println(ts.threeSum(nums));
        int[] nums1 = new int[]{-1,0,1,2,-1,-4};
        System.out.println(ts.threeSum(nums1));
        int[] nums2 = new int[]{-2,0,1,1,2};
        System.out.println(ts.threeSum(nums2));
        System.out.println(ts.threeSum1(nums2));
    }
}
