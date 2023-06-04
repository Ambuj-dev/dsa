package patterns.dp.knapsack;

public class TargetSum {
    public static void main(String[] args) {
        System.out.println(findTargetSubsets(new int[]{1,1,2,3}, 1));
        System.out.println(findTargetSubsetsOptimised(new int[]{1,1,2,3}, 1));
    }
    public static int findTargetSubsets(int[] num, int s) {
        int totalSum = 0;
        for(int val : num){
            totalSum +=val;
            if(val<0) return -1;
        }
        if(totalSum < s || (s+totalSum)%2 == 1) return 0;
        return countSubsets(num, (s+totalSum)/2);
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
    public static int findTargetSubsetsOptimised(int[] num, int s) {
        int totalSum = 0;
        for(int val : num){
            totalSum +=val;
            if(val<0) return -1;
        }
        if(totalSum < s || (s+totalSum)%2 == 1) return 0;
        return countSubsetsOptimised(num, (s+totalSum)/2);
    }

    public static int countSubsetsOptimised(int[] num, int sum) {
        int n = num.length;
        int[] dp = new int[sum+1];
        dp[0] = 1;
        for(int s = 1; s<=sum; s++) dp[s] = num[0] == s ? 1 : 0;

        for(int i = 1; i < n; i++){
            for(int s = sum; s >= 0; s--){
               if(s >= num[i]) dp[s] += dp[s-num[i]];
            }
        }
        return dp[sum];
    }
    public int findTargetSubsets2(int[] num, int s) {
        int totalSum = 0;
        for (int n : num){
            totalSum += n;
            if(n < 1) return -1; //invalid input, the problem expects only positive numbers
        }

        // if 's + totalSum' is odd, we can't find a subset with sum equal to '(s + totalSum) / 2'
        if(totalSum < s || (s + totalSum) % 2 == 1)
            return 0;

        return countSubsets2(num, (s + totalSum) / 2);
    }

    // this function is exactly similar to what we have in 'Count of Subset Sum' problem.
    private int countSubsets2(int[] num, int sum) {
        int n = num.length;
        int[][] dp = new int[n][sum + 1];

        // populate the sum=0 columns, as we will always have an empty set for zero sum
        for(int i=0; i < n; i++)
            dp[i][0] = 1;

        // with only one number, we can form a subset only when the required sum is equal to the number
        for(int s=1; s <= sum ; s++) {
            dp[0][s] = (num[0] == s ? 1 : 0);
        }

        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
            for(int s=1; s <= sum; s++) {
                dp[i][s] = dp[i-1][s];
                if(s >= num[i])
                    dp[i][s] += dp[i-1][s-num[i]];
            }
        }

        // the bottom-right corner will have our answer.
        return dp[num.length-1][sum];
    }
    public int findTargetSubsets3(int[] num, int s) {
        int totalSum = 0;
        for (int n : num){
            totalSum += n;
            if(n < 1) return -1; //invalid input, the problem expects only positive numbers
        }

        // if 's + totalSum' is odd, we can't find a subset with sum equal to '(s + totalSum) / 2'
        if(totalSum < s || (s + totalSum) % 2 == 1)
            return 0;

        return countSubsets3(num, (s + totalSum) / 2);
    }

    // this function is exactly similar to what we have in 'Count of Subset Sum' problem.
    private int countSubsets3(int[] num, int sum) {
        int n = num.length;
        int[] dp = new int[sum + 1];
        dp[0] = 1;

        // with only one number, we can form a subset only when the required sum is equal to the number
        for(int s=1; s <= sum ; s++) {
            dp[s] = (num[0] == s ? 1 : 0);
        }

        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
            for(int s=sum; s >= 0; s--) {
                if(s >= num[i])
                    dp[s] += dp[s-num[i]];
            }
        }

        return dp[sum];
    }



}
