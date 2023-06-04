package patterns.dp.knapsack;

public class SubsetSumTabulation {
    public static void main(String[] args) {
        System.out.println(subsetSum(new int[]{1, 2, 3, 4}, 5));

        System.out.println(subsetSum(new int[]{1, 2, 3, 4}, 11));
        System.out.println(subsetSum(new int[]{1, 2, 3, 4}, 10));

        System.out.println(subsetSumOptimised(new int[]{1, 2, 3, 4}, 5));

        System.out.println(subsetSumOptimised(new int[]{1, 2, 3, 4}, 11));
        System.out.println(subsetSumOptimised(new int[]{1, 2, 3, 4}, 10));

        System.out.println(subsetSumOptimised1(new int[]{1, 2, 3, 4}, 5));

        System.out.println(subsetSumOptimised1(new int[]{1, 2, 3, 4}, 11));
        System.out.println(subsetSumOptimised1(new int[]{1, 2, 3, 4}, 10));
        int[] num = { 1, 2, 3, 7 };
        System.out.println(canPartition(num, 6));
        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(canPartition(num, 10));
        num = new int[] { 1, 3, 4, 8 };
        System.out.println(canPartition(num, 6));

    }


    private static boolean subsetSum(int[] nums, int sum) {
        int n = nums.length;
        boolean[][] dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        for (int s = 1; s <= sum; s++) {
            dp[0][s] = nums[0] == s;
        }
        for (int i = 1; i < n; i++) {
            for (int s = 1; s <= sum; s++) {
                if (dp[i - 1][s])
                    dp[i][s] = dp[i - 1][s];
                else if (nums[i] <= s)
                    dp[i][s] = dp[i - 1][s - nums[i]];
            }
        }
        return dp[n - 1][sum];
    }

    private static boolean subsetSumOptimised(int[] nums, int sum) {
        int n = nums.length;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int s = 1; s <= sum; s++) {
            dp[s] = nums[0] == s;
        }
        for (int i = 1; i < n; i++) {
            for (int s = sum; s >= 0; s--) {
                // if dp[s]==true, this means we can get the sum s without
                //num[i], then move on to the next number else we can include num[i]
                //and see if we can find a subset to get the remaining sum

                if (!dp[s] && s >= nums[i]) {
                    //else include the number and see if we can find a subset to get the remaining sum
                    dp[s] = dp[s - nums[i]];

                }
            }

        }
        return dp[sum];
    }

    private static boolean subsetSumOptimised1(int[] nums, int sum) {
        int n = nums.length;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int s = 1; s <= sum; s++) {
            dp[s] = nums[0] == s;
        }

        for (int i = 1; i < n; i++) {
            for (int s = sum; s >= nums[i]; s--) {

                if (dp[s - nums[i]]) {
                    dp[s] = true;
                }
            }
            if(dp[sum]) return true;

        }
        return false;
    }

    public static boolean canPartition(int[] num, int sum) {
        int n = num.length;
        boolean[][] dp = new boolean[n][sum + 1];

        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is
        // equal to its value
        for (int s = 1; s <= sum; s++) {
            dp[0][s] = (num[0] == s ? true : false);
        }

        // process all subsets for all sums
        for (int i = 1; i < num.length; i++) {
            for (int s = 1; s <= sum; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if (dp[i - 1][s]) {
                    dp[i][s] = dp[i - 1][s];
                } else if (s >= num[i]) {
                    // else include the number and see if we can find a subset to get the remaining
                    // sum
                    dp[i][s] = dp[i - 1][s - num[i]];
                }
            }
        }

        // the bottom-right corner will have our answer.
        return dp[num.length - 1][sum];
    }


}
