package dp.oned;

import java.util.ArrayList;
import java.util.List;

public class MaximumNonAdjacentSum {
    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        // Write your code here.
        Integer[] dp = new Integer[nums.size() + 1];
        return adjacentSum(nums.size()-1, nums, dp);
    }
    public static int adjacentSum(int index, ArrayList<Integer> nums, Integer[] dp){
        if(index == 0) return nums.get(0);
        if(index < 0) return 0;
        if(dp[index] != null) return dp[index];
        int pickCur = nums.get(index) + adjacentSum(index -2, nums, dp);
        int skipCur = adjacentSum(index-1, nums, dp);
        return dp[index] = Math.max(pickCur, skipCur);
    }
    public static int maximumNonAdjacentSumTabulation(ArrayList<Integer> nums) {
        // Write your code here.
        int n = nums.size();
        Integer[] dp = new Integer[n + 1];
        dp[0] = nums.get(0);
        for(int i = 1; i< n;i++){
            int pickCur = nums.get(i); if(i>1) pickCur += dp[i -2];
            int skipCur = dp[i-1];
            dp[i] = Math.max(pickCur, skipCur);
        }
        return dp[n-1];
    }
    public static int maximumNonAdjacentSumTabulationOptimized(ArrayList<Integer> nums) {
        // Write your code here.
        int n = nums.size();
        int prev1 = 0;
        int prev = nums.get(0);
        for(int i = 1; i< n;i++){
            int pickCur = nums.get(i); if(i>1) pickCur += prev1;
            int skipCur = prev;
            int cur = Math.max(pickCur, skipCur);
            prev1 = prev;
            prev = cur;
        }
        return prev;
    }

    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        ArrayList al1 = new ArrayList();
        al.add(1);
        al.add(9);
        al1.add(10);
        System.out.println(maximumNonAdjacentSum(al));
        System.out.println(maximumNonAdjacentSum(al1));
        System.out.println(maximumNonAdjacentSumTabulation(al));
        System.out.println(maximumNonAdjacentSumTabulation(al1));
        System.out.println(maximumNonAdjacentSumTabulationOptimized(al));
        System.out.println(maximumNonAdjacentSumTabulationOptimized(al1));
    }
}
