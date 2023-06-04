package patterns.twopointer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SubarraysWithProductLessThanTarget {

    public int numSubarrayProductLessThanK(int[] nums, int  k){
        if(k <= 1) return 0;
        int res = 0;
        int product = 1;
        int left = 0;
        for(int right = 0; right < nums.length; right++){
            product *= nums[right];
            while(product >= k){
                product /= nums[left++];
            }
            res += right - left + 1;
        }
        return  res;
    }

    public List<List<Integer>> subarrayProductLessThanK(int[] nums, int  k){
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <= 1 || k < 0) {
            return res;
        }
        int product = 1;
        int left = 0;
        for(int right = 0; right < nums.length; right++){
            product *= nums[right];
            while(product >= k && left < nums.length){
                product /= nums[left++];
            }
            ArrayList<Integer> subList = new ArrayList<>();
                for (int i = right; i >= left; i--) {
                    subList.add(0, nums[i]);
                    res.add(new ArrayList<>(subList));
                }
        }
        return  res;
    }

    public static void main(String[] args) {
        SubarraysWithProductLessThanTarget subarraysWithProductLessThanTarget = new SubarraysWithProductLessThanTarget();
        int[] nums = new int[]{10,5,2,6};
        System.out.println(subarraysWithProductLessThanTarget.numSubarrayProductLessThanK(nums, 100));//8
        System.out.println(subarraysWithProductLessThanTarget.subarrayProductLessThanK(nums, 100));//[[10], [5], [10, 5], [2], [5, 2], [6], [2, 6], [5, 2, 6]]
    }
}
