package patterns.subsets;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        List<Integer> subset = new ArrayList();
        boolean map[] = new boolean[nums.length];
        helper(nums,subset,ans,map);
        return ans;
    }
    public static void helper(int nums[], List<Integer> subset, List<List<Integer>> ans, boolean map[]){
        if(subset.size()==nums.length){
            ans.add(new ArrayList(subset));
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(!map[i]){
                subset.add(nums[i]);
                map[i]=true;
                helper(nums,subset,ans,map);
                subset.remove(subset.size()-1);
                map[i]=false;
            }
        }
    }

    public static List<List<Integer>> permuteWithSwap(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        helperSwap(0, nums,ans);
        return ans;
    }
    public static void helperSwap(int index, int nums[], List<List<Integer>> ans){
        if(index==nums.length){
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i< nums.length; i++){
                list.add(nums[i]);
            }
            ans.add(list);
            return;
        }
        for(int i=index; i<nums.length; i++){
            swap(index, i, nums);
            helperSwap(index+1, nums, ans);
            swap(index, i , nums);
        }
    }

    public static void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{0,1,2}));
        System.out.println(permuteWithSwap(new int[]{0,1,2}));
    }
}
