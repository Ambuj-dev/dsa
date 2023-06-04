package patterns.dp.knapsack;

public class EqualSubsetSumPartitionTabulation {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 2, 3, 4}));
        System.out.println(canPartition(new int[]{1, 2, 3, 5}));


        System.out.println(canPartitionOptimised(new int[]{1, 2, 3, 4}));
        System.out.println(canPartitionOptimised(new int[]{1, 2, 3, 5}));
        System.out.println(canPartitionOptimised1(new int[]{1, 2, 3, 4}));
        System.out.println(canPartitionOptimised1(new int[]{1, 2, 3, 5}));
    }


    public static boolean canPartition(int[] num) {
        int n = num.length;
        // find the total sum
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with same total
        if(sum % 2 != 0)
            return false;

        // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
        sum /= 2;

        boolean[][] dp = new boolean[n][sum + 1];

        // populate the sum=0 columns, as we can always for '0' sum with an empty set
        for(int i=0; i < n; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is equal to
        // its value
        for(int s=1; s <= sum ; s++) {
            dp[0][s] = num[0] == s;
        }

        // process all subsets for all sums
        for(int i=1; i < n; i++) {
            for(int s=1; s <= sum; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if(dp[i-1][s]) {
                    dp[i][s] = dp[i-1][s];
                } else if (s >= num[i]) { // else we can find a subset to get the remaining sum
                    dp[i][s] = dp[i-1][s-num[i]];
                }
            }
        }

        // the bottom-right corner will have our answer.
        return dp[n-1][sum];
    }

    private static boolean canPartitionOptimised(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i=0; i< n; i++) sum += nums[i];
        if(sum %2 != 0) return false;
        sum /= 2;
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
            if(dp[sum]) return true;
        }
        return false;
    }

    private static boolean canPartitionOptimised1(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i=0; i< n; i++) sum += nums[i];
        if(sum %2 != 0) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

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
    public boolean canPartition2(int[] num) {
        int n = num.length;
        // find the total sum
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with same total
        if(sum % 2 != 0)
            return false;

        // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
        sum /= 2;

        boolean[][] dp = new boolean[n][sum + 1];

        // populate the sum=0 column, as we can always have '0' sum without including any element
        for(int i=0; i < n; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is equal to its value
        for(int s=1; s <= sum ; s++) {
            dp[0][s] = (num[0] == s ? true : false);
        }

        // process all subsets for all sums
        for(int i=1; i < n; i++) {
            for(int s=1; s <= sum; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if(dp[i-1][s]) {
                    dp[i][s] = dp[i-1][s];
                } else if (s >= num[i]) { // else if we can find a subset to get the remaining sum
                    dp[i][s] = dp[i-1][s-num[i]];
                }
            }
        }

        // the bottom-right corner will have our answer.
        return dp[n-1][sum];
    }
    public boolean canPartition3(int[] num) {
        int n = num.length;
        // find the total sum
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with same total
        if(sum % 2 != 0)
            return false;

        // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
        sum /= 2;

        boolean[] dp = new boolean[sum + 1];

        // populate the sum=0 column, as we can always have '0' sum without including any element
        for(int i=0; i < n; i++)
            dp[0] = true;

        // with only one number, we can form a subset only when the required sum is equal to its value
        for(int s=1; s <= sum ; s++) {
            dp[s] = (num[0] == s ? true : false);
        }

        // process all subsets for all sums
        for(int i=1; i < n; i++) {
            for(int s=1; s <= sum; s++) {
                // if we can get the sum 's' without the number at index 'i'
               if (!dp[s] && s >= num[i]) { // if we can find a subset to get the remaining sum
                    dp[s] = dp[s-num[i]];
                }
            }
        }

        // the bottom-right corner will have our answer.
        return dp[sum];
    }
}
