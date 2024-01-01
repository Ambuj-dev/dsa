package dp.subsequence;

public class PartitionEqualSubsetSum {
    public static boolean canPartition(int[] arr, int n) {
        // Write your code here.
        int totalSum = 0;
        for(int i = 0; i< n; i++){
            totalSum += arr[i];
        }
        if(totalSum % 2 == 1) return false;

        return subsetSumToK(n, totalSum/2, arr);
    }

    public static boolean subsetSumToK(int n, int k, int arr[]){
        // Write your code here.
        boolean[] dp = new boolean[k+1];

        for(int i = 0; i < n; i++) dp[0] = true;
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

        int arr[] = {2,3,3,3,4,5};
        int n = arr.length;

        if(canPartition(arr, n))
            System.out.println("The Array can be partitioned into two equal subsets");
        else
            System.out.println("The Array cannot be partitioned into two equal subsets");
    }

}
