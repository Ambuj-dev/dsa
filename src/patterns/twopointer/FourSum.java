package patterns.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.



Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
* */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 3; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            for(int j = i+1; j < nums.length - 2; j++){
                if(j > i+1 && nums[j] == nums[j-1]){
                    continue;
                }
                twoSum(nums, i, j, target, res);
            }
        }
        return res;
    }

    public void twoSum(int[] nums, int first, int second, int target, List<List<Integer>> res){
        int left = second + 1;
        int right = nums.length - 1;

        while(left < right) {
            long sum = (long)nums[first] + (long)nums[second] + (long)nums[left] + (long)nums[right];
            if(sum == target){
                List<Integer> list = List.of(nums[first], nums[second], nums[left], nums[right]);
                res.add(list);
                while(left < right && nums[left] == list.get(2)){
                    left++;
                }
                while ((left < right && nums[right] == list.get(3))){
                    right--;
                }
            }else if(sum < target){
                left++;
            }else{
                right--;
            }
        }
    }
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                long target2 = (long) target - (long) nums[i] - (long) nums[j];
                int lo = j + 1, hi = n - 1;

                while (lo < hi) {
                    int twoSum = nums[lo] + nums[hi];

                    if (twoSum < target2) lo++;
                    else if (twoSum > target2) hi--;
                    else {
                        List<Integer> quad = Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]);
                        ans.add(quad);

                        while (lo < hi && nums[lo] == quad.get(2)) lo++;
                        while (lo < hi && nums[hi] == quad.get(3)) hi--;
                    }
                }

                while (j + 1 < n && nums[j] == nums[j + 1]) j++;
            }

            while (i + 1 < n && nums[i] == nums[i + 1]) i++;
        }

        return ans;
    }

    public static void main(String[] args) {

        FourSum fourSum = new FourSum();
        int[] nums = {1,0,-1,0,-2,2};
        //System.out.println(fourSum.fourSum(nums, 0));
        int[] nums1 = {-2,-1,-1,1,1,2,2};
       // System.out.println(fourSum.fourSum(nums1, 0));
        int[] nums2 = {-1,0,-5,-2,-2,-4,0,1,-2};
        System.out.println(fourSum.fourSum1(nums2, -9));
        System.out.println(fourSum.fourSum(nums2, -9));

    }
}
