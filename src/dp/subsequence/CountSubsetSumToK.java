package dp.subsequence;

public class CountSubsetSumToK {

    public static int findWays(int num[], int tar) {
        // Write your code here..
        int n = num.length;
        Integer[][] dp = new Integer[n][tar+1];
        return findWays(n-1, tar, num, dp);
    }

    public static int findWays(int index, int k, int arr[], Integer[][] dp){
        // Write your code here.
        if(k == 0) return 1;
        if(index == 0) return arr[0] == k? 1 : 0;
        if(dp[index][k] != null) return dp[index][k];
        int notTake = findWays(index-1, k, arr, dp);
        int take = 0;
        if(arr[index] <= k)
            take = findWays(index-1, k-arr[index], arr, dp);

        return dp[index][k] = notTake + take;
    }

    public static int findWaysTabulation(int arr[], int k){
        // Write your code here.
        int n = arr.length;
        int[] dp = new int[k+1];

        dp[0] = 1;
        if(arr[0] <= k)
            dp[arr[0]] = 1;

        for(int index = 1; index < n; index++){
            for(int target = k; target >= 0; target--){
                int notTake = dp[target];
                int take = 0;
                if(arr[index] <= target)
                    take =dp[target-arr[index]];

                dp[target] = notTake + take;
            }
        }

        return dp[k];
    }

    public static void main(String[] args) {
        System.out.println(findWays(new int[]{1, 4, 4, 5}, 5));
        System.out.println(findWaysTabulation(new int[]{1, 4, 4, 5}, 5));
    }
}
