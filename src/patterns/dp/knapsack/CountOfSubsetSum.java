package patterns.dp.knapsack;

public class CountOfSubsetSum {
    public static void main(String[] args) {
        System.out.println(countSubsets(new int[]{1,1,2,3}, 4));
        System.out.println(countSubsetsMemoization(new int[]{1,1,2,3}, 4));
    }

    public static int countSubsets(int[] num, int sum) {
        return countSubsetsRecursive(num, sum, 0);
    }

    public static int countSubsetsRecursive(int[] num, int sum, int curIndex) {
        if(sum == 0) return 1;
        int n = num.length;
        if(n == 0 || curIndex >= n) return 0;
        int count1 = 0;
        if (num[curIndex] <= sum) {
            count1 = countSubsetsRecursive(num, sum-num[curIndex], curIndex+1);
        }
        int count2 = countSubsetsRecursive(num, sum, curIndex+1);
        return count1+count2;

    }

    public static int countSubsetsMemoization(int[] num, int sum) {
        Integer[][] dp = new Integer[num.length][sum+1];
        return countSubsetsMemoizationRecursive(num, sum, 0, dp);
    }

    public static int countSubsetsMemoizationRecursive(int[] num, int sum, int curIndex, Integer[][] dp) {
        if(sum == 0) return 1;
        int n = num.length;
        if(n == 0 || curIndex >= n) return 0;
        if(dp[curIndex][sum] == null) {
            int count1 = 0;
            if (num[curIndex] <= sum) {
                count1 = countSubsetsMemoizationRecursive(num, sum - num[curIndex], curIndex + 1, dp);
            }
            int count2 = countSubsetsMemoizationRecursive(num, sum, curIndex + 1, dp);
            dp[curIndex][sum] = count1 + count2;
        }
        return dp[curIndex][sum];

    }

    public int countSubsets1(int[] num, int sum) {
        Integer[][] dp = new Integer[num.length][sum + 1];
        return this.countSubsetsRecursive1(dp, num, sum, 0);
    }


    private int countSubsetsRecursive1(Integer[][] dp, int[] num, int sum, int
            currentIndex) {
        // base checks
        if (sum == 0)
            return 1;

        if(num.length == 0 || currentIndex >= num.length)
            return 0;

        // check if we have not already processed a similar problem
        if(dp[currentIndex][sum] == null) {
            // recursive call after choosing the number at the currentIndex
            // if the number at currentIndex exceeds the sum, we shouldn't process this
            int sum1 = 0;
            if( num[currentIndex] <= sum )
                sum1 = countSubsetsRecursive1(dp, num, sum - num[currentIndex], currentIndex + 1);

            // recursive call after excluding the number at the currentIndex
            int sum2 = countSubsetsRecursive1(dp, num, sum, currentIndex + 1);

            dp[currentIndex][sum] = sum1 + sum2;
        }

        return dp[currentIndex][sum];
    }
}
