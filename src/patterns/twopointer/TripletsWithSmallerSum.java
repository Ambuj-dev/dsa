package patterns.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* https://leetcode.com/problems/3sum-smaller/

Given an array arr of unsorted numbers and a target sum, count all triplets in it such that arr[i] + arr[j] + arr[k] < target
* where i, j, and k are three different indices. Write a function to return the count of such triplets.

This problem follows the Two Pointers pattern and shares similarities with Triplet Sum to Zero.
* The only difference is that, in this problem, we need to find the triplets whose sum is less than the given target.
* To meet the condition i != j != k we need to make sure that each number is not used more than once.

Following a similar approach, first, we can sort the array and then iterate through it, taking one number at a time.
* Let’s say during our iteration we are at number X, so we need to find Y and Z such that X + Y + Z < target.
* At this stage, our problem translates into finding a pair whose sum is less than “target - X” (as from the above equation Y + Z == target - X).
* We can use a similar approach as discussed in Triplet Sum to Zero.
* */
public class TripletsWithSmallerSum {

    public int threeSumCount(int[] nums, int target){
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0; i < nums.length - 2; i++){
            count += allTwoSumCount(nums, i, target-nums[i]);
        }
        return count;
    }
    public int allTwoSumCount(int[] nums, int first, int target){
        int count = 0;
        int end  = nums.length - 1;
        int start = first + 1;
        while(start < end){
            if(nums[start] + nums[end] < target){
                //we found the a triplet
                //since arr[end] >= arr[start], therefore, we can replace arr[end]
                //by any number between start and end to get a sum less than the targetSum
                count += end - start;
                start++;
            }else{
                end--;
            }
        }
        return count;
    }

    public List<List<Integer>> threeSum(int[] nums, int target){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i< nums.length - 2; i++){
            allTwoSum(nums, i, target-nums[i], res);
        }
        return res;
    }
    public int allTwoSum(int[] nums, int first, int target, List<List<Integer>> res){
        int count = 0;
        int end  = nums.length - 1;
        int start = first + 1;
        while(start < end){
            if(nums[start] + nums[end] < target){
                //we found the triplet
                //since arr[end] >= arr[start], therefore, we can replace arr[end]
                //by any number between start and end to get a sum less than the targetSum
                count += end - start;
                for(int i = end; i>start; i--){
                    res.add(List.of(nums[first], nums[start], nums[i]));
                }
                start++;
            }else{
                end--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        TripletsWithSmallerSum tripletsWithSmallerSum = new TripletsWithSmallerSum();
        int[] nums = new int[]{-1, 0, 2, 3};
        System.out.println(tripletsWithSmallerSum.threeSumCount(nums, 3));

        int[] nums1 = new int[]{-1, 4, 2, 1, 3};
        System.out.println(tripletsWithSmallerSum.threeSumCount(nums1, 5));

        System.out.println(tripletsWithSmallerSum.threeSum(nums, 3));

        System.out.println(tripletsWithSmallerSum.threeSum(nums1, 5));
    }
}
