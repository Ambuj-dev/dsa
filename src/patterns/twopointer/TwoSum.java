package patterns.twopointer;

import java.util.*;
public class TwoSum {

    //Works when array is not sorted also
    public int[] twoSumHasMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i],i);
        }
        return null;
    }
    public List<List<Integer>> allTwoSumHasMap(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                res.add(List.of(map.get(complement), i));
            }
            map.put(nums[i],i);
        }
        return res;
    }
    // Array should be sorted
    public int[] twoSum(int[] nums, int target){
        int start = 0;
        int end  = nums.length - 1;
        while(start < end){
            int curSum = nums[start] + nums[end];
            if(curSum == target){
                return new int[]{start, end};
            }else if(curSum < target){
                start++;
            }else{
                end--;
            }
        }
        return null;
    }

    public List<List<Integer>> allTwoSum(int[] nums, int target){
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int end  = nums.length - 1;
        while(start < end){
            int curSum = nums[start] + nums[end];
            if(curSum == target){
                res.add(List.of(start, end));
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

    public List<List<Integer>> allTwoSumDuplicate(int[] nums, int target){
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int end  = nums.length - 1;
        while(start < end){
            int curSum = nums[start] + nums[end];
            if(curSum == target){
                res.add(List.of(start, end));
                start++;
                end--;
                while(start < end && nums[start] == nums[start + 1]){
                    start++;
                }
                while(start < end && nums[end] == nums[end - 1]){
                    end--;
                }
            }else if(curSum < target){
                start++;
            }else{
                end--;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        TwoSum tp = new TwoSum();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        int[] twoSumHashMap = tp.twoSumHasMap(nums, 5);
        Arrays.stream(twoSumHashMap).forEach(System.out::println);

        List<List<Integer>> allTwoSumHashMap = tp.allTwoSumHasMap(nums, 5);
        allTwoSumHashMap.stream().forEach(System.out::println);

        int[] twoSum = tp.twoSum(nums, 5);
        Arrays.stream(twoSum).forEach(System.out::println);

        List<List<Integer>> allTwoSum = tp.allTwoSum(nums, 5);
        allTwoSum.stream().forEach(System.out::println);

        nums = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 6};
        System.out.println(tp.allTwoSumDuplicate(nums, 5));

    }
}
