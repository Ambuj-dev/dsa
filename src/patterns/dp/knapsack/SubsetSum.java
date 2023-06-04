package patterns.dp.knapsack;

public class SubsetSum {
    public static void main(String[] args) {
        System.out.println(subsetSum(new int[]{1,2,3,4}, 5));

        System.out.println(subsetSum(new int[]{1,2,3,4}, 11));
    }
    private static boolean subsetSum(int[] nums, int sum){
        return subsetSumRecursive(nums, sum, 0);
    }
    private static boolean subsetSumRecursive(int[] nums, int sum, int currentIndex){
        int n = nums.length;
        if(sum == 0) return true;
        if(n == 0 || currentIndex >= n) return false;

        if(nums[currentIndex] <= sum){
            if(subsetSumRecursive(nums, sum-nums[currentIndex], currentIndex+1)){
                return true;
            }
        }
        return subsetSumRecursive(nums, sum, currentIndex+1);
    }
}
