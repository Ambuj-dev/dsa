package patterns.dp.knapsack;

public class CountOfSubsetSumTabulation {
    public static void main(String[] args) {
        System.out.println(countSubsets(new int[]{1,1,2,3}, 4));
        System.out.println(countSubsetsOptimised(new int[]{1,1,2,3}, 4));
        System.out.println(countSubsets2(new int[]{1,1,2,3}, 4));
        int[] num = new int[]{1, 2, 7, 1, 5};
        System.out.println(countSubsets(num, 9));
        System.out.println(countSubsetsOptimised(num, 9));
        System.out.println(countSubsets2(num, 9));
    }

    public static int countSubsets(int[] num, int sum) {
        int n = num.length;
        int[][] dp = new int[n][sum+1];
        for(int i = 0; i< n; i++) dp[i][0] = 1;
        for(int s = 1; s<=sum; s++) dp[0][s] = num[0] == s ? 1 : 0;

        for(int i = 1; i < n; i++){
            for(int s = 1; s <= sum; s++){
                dp[i][s] = dp[i-1][s];
                if(s >= num[i]) dp[i][s] += dp[i-1][s-num[i]];
            }
        }
        return dp[n-1][sum];
    }


    public static int countSubsetsOptimised(int[] num, int sum) {
        int n = num.length;
        int[] dp = new int[sum+1];
        dp[0] = 1;
        for(int s = 1; s<=sum; s++) dp[s] = num[0] == s ? 1 : 0;

        for(int i = 1; i < n; i++){
            for(int s = sum; s >= 1; s--){
                if(s >= num[i])
                    dp[s] += dp[s-num[i]];
            }
        }
        return dp[sum];
    }

    public static int countSubsets2(int[] num, int sum) {
        int n = num.length;
        int[][] dp = new int[n][sum + 1];

        // populate the sum=0 columns, as we will always have an empty set for zero sum
        for(int i=0; i < n; i++)
            dp[i][0] = 1;

        // with only one number, we can form a subset only when the required sum is equal to its value
        for(int s=1; s <= sum ; s++) {
            dp[0][s] = (num[0] == s ? 1 : 0);
        }

        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
            for(int s=1; s <= sum; s++) {
                // exclude the number
                dp[i][s] = dp[i-1][s];
                // include the number, if it does not exceed the sum
                if(s >= num[i])
                    dp[i][s] += dp[i-1][s-num[i]];
            }
        }

        // the bottom-right corner will have our answer.
        return dp[num.length-1][sum];
    }
}
