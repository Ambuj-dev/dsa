package dp.subsequence;

import java.util.Arrays;

public class CoinChange2 {

    public static long countWaysToMakeChange(int arr[], int T){

        int n = arr.length;
        long[][] dp = new long[n][T+1];
        for(long[] dpArr : dp) Arrays.fill(dpArr, -1);
        return countWaysToMakeChange(n-1, T, arr, dp);
    }

    public static long countWaysToMakeChange(int index, int T, int[] arr, long[][] dp){
        if(index == 0){
            return T%arr[index] == 0 ? 1: 0;
        }

        if(dp[index][T] != -1) return dp[index][T];
        long notTake = countWaysToMakeChange(index-1, T, arr, dp);

        long take = 0;
        if(arr[index] <= T)
            take = countWaysToMakeChange(index, T-arr[index], arr, dp);

        return dp[index][T] = notTake + take;
    }

    public static long countWaysToMakeChangeTabulation(int arr[], int T){

        int n = arr.length;
        long[][] dp = new long[n][T+1];
        for(int target = 0; target <= T; target++){
            dp[0][target] = target%arr[0] == 0? 1: 0;
        }
        for(int index = 1; index < n; index++){
            for(int target= 0; target <= T; target++){
                long notTake = dp[index-1][target];
                long take = 0;
                if(arr[index] <= target)
                    take = dp[index][target-arr[index]];

                dp[index][target] = notTake + take;
            }
        }
        return dp[n-1][T];
    }

    public static long countWaysToMakeChangeTabulationOptimised(int arr[], int T){

        int n = arr.length;
        long[] prev = new long[T+1];
        for(int target = 0; target <= T; target++){
            prev[target] = target%arr[0] == 0? 1: 0;
        }
        for(int index = 1; index < n; index++){
            long[] cur = new long[T+1];
            for(int target= 0; target <= T; target++){
                long notTake = prev[target];
                long take = 0;
                if(arr[index] <= target)
                    take = cur[target-arr[index]];

                cur[target] = notTake + take;
            }
            prev = cur;
        }
        return prev[T];
    }
    public static void main(String args[]) {

        int arr[] ={1,2,3};
        int target=4;

        int n =arr.length;

        System.out.println("The total number of ways is "+countWaysToMakeChange(arr,target));
        System.out.println("The total number of ways is "+countWaysToMakeChangeTabulation(arr,target));
        System.out.println("The total number of ways is "+countWaysToMakeChangeTabulationOptimised(arr,target));

    }


}
