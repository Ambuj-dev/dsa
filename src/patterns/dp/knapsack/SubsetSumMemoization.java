package patterns.dp.knapsack;

public class SubsetSumMemoization {
    public static void main(String[] args) {
        System.out.println(subsetSum(new int[]{1,2,3,4}, 5));

        System.out.println(subsetSum(new int[]{1,2,3,4}, 11));
    }
    private static boolean subsetSum(int[] nums, int sum){
        Boolean[][] dp = new Boolean[nums.length][sum+1];
        return subsetSumRecursive(nums, sum, 0, dp);
    }

    private static boolean subsetSumRecursive(int[] nums, int sum, int currentIndex, Boolean[][] dp){
        int n = nums.length;
        if(sum == 0) return true;
        if(n == 0 || currentIndex >= n) return false;
        if(dp[currentIndex][sum] != null) return dp[currentIndex][sum];

        if(nums[currentIndex] <= sum){
            if(subsetSumRecursive(nums, sum-nums[currentIndex], currentIndex+1, dp)){
                dp[currentIndex][sum] = true;
                return true;
            }
        }
        return dp[currentIndex][sum] = subsetSumRecursive(nums, sum, currentIndex+1, dp);
    }
}
