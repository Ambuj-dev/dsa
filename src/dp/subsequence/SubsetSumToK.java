package dp.subsequence;

public class SubsetSumToK {

    public static boolean subsetSumToK(int n, int k, int arr[]){
        // Write your code here.
        Boolean[][] dp = new Boolean[n][k+1];
        return subsetSumToKHelper(n-1, k, arr, dp);
    }

    public static boolean subsetSumToKHelper(int index, int k, int arr[], Boolean[][] dp){
        // Write your code here.
        if(k == 0) return true;
        if(index == 0) return arr[0] == k;
        if(dp[index][k] != null) return dp[index][k];
        boolean notTake = subsetSumToKHelper(index-1, k, arr, dp);
        boolean take = false;
        if(arr[index] <= k)
            take = subsetSumToKHelper(index-1, k-arr[index], arr, dp);

        return dp[index][k] = notTake || take;
    }

    public static boolean subsetSumToKTabulation(int n, int k, int arr[]){
        // Write your code here.
        boolean[][] dp = new boolean[n][k+1];

        for(int i = 0; i < n; i++) dp[i][0] = true;
        if(arr[0] <= k)
            dp[0][arr[0]] = true;

        for(int index = 1; index < n; index++){
            for(int target = 1; target <= k; target++){
                boolean notTake = dp[index-1][target];
                boolean take = false;
                if(arr[index] <= target)
                    take = dp[index-1][target-arr[index]];

                dp[index][target] = notTake || take;
            }
        }

        return dp[n-1][k];
    }
    public static boolean subsetSumToKTabulationOptimised1(int n, int k, int arr[]){
        // Write your code here.
        boolean[] prev = new boolean[k+1];

        prev[0] = true;
        if(arr[0] <= k)
            prev[arr[0]] = true;

        for(int index = 1; index < n; index++){
            boolean[] cur = new boolean[k+1];
            cur[0] = true;
            for(int target = 1; target <= k; target++){
                boolean notTake = prev[target];
                boolean take = false;
                if(arr[index] <= target)
                    take =prev[target-arr[index]];

                cur[target] = notTake || take;
            }
            prev = cur;
        }

        return prev[k];
    }

    public static boolean subsetSumToKTabulationOptimised2(int n, int k, int arr[]){
        // Write your code here.
        boolean[] dp = new boolean[k+1];

        dp[0] = true;
        if(arr[0] <= k)
            dp[arr[0]] = true;

        for(int index = 1; index < n; index++){
            for(int target = k; target >= 0; target--){
                boolean notTake = dp[target];
                boolean take = false;
                if(arr[index] <= target)
                    take =dp[target-arr[index]];

                dp[target] = notTake || take;
            }
        }

        return dp[k];
    }


    public static void main(String args[]) {

        int arr[] = {1,2,3,4};
        int k=4;
        int n = arr.length;

        if(subsetSumToK(n,k,arr))
            System.out.println("Subset with given target found");
        else
            System.out.println("Subset with given target not found");

        System.out.println(subsetSumToKTabulation(n, k, arr));
        System.out.println(subsetSumToKTabulationOptimised1(n, k, arr));
        System.out.println(subsetSumToKTabulationOptimised2(n, k, arr));
    }

}
