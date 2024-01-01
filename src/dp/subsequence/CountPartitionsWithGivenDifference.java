package dp.subsequence;

public class CountPartitionsWithGivenDifference {
    static int mod = (int) (Math.pow(10, 9) + 7);

    public static int countPartitions(int n, int d, int[] arr) {

        int totalSum = 0;
        for (int i = 0; i < n; i++) totalSum += arr[i];

        if (d > totalSum || (totalSum - d) % 2 == 1) return 0;
        int targetSum = (totalSum - d) / 2;
        Integer[][] dp = new Integer[n][targetSum + 1];
        return countPartitionsHelper(n - 1, targetSum, arr, dp);
    }

    public static int countPartitionsHelper(int index, int target, int[] arr, Integer[][] dp) {
        if (index == 0) {
            //when we are at index 0, if the target sum is 0 and the first index is also 0, like in case [0,1],
            // we can form the subset in two ways, either by considering the first element or leaving it, so we can return 2
            if (arr[0] == 0 && target == 0) return 2;
            //•	Or if at index 0, when the first element is not 0, and the target is equal to the first element ,
            // then we will include it in the subset and we will return 1 way.
            if (target == 0 || arr[0] == target) return 1;
            return 0;
        }
        if (dp[index][target] != null) return dp[index][target];

        int notTaken = countPartitionsHelper(index - 1, target, arr, dp);
        int taken = 0;
        if (arr[index] <= target)
            taken = countPartitionsHelper(index - 1, target - arr[index], arr, dp);

        return dp[index][target] = (notTaken + taken) % mod;
    }

    static int findWays(int[] num, int tar){
        int n = num.length;

        int dp[][] = new int[n][tar+1];

        if(num[0] == 0) dp[0][0] = 2;  // 2 cases - pick and not pick
        else dp[0][0] = 1;  // 1 case - not pick

        if(num[0]!=0 && num[0]<=tar) dp[0][num[0]] = 1;  // 1 case -pick

        for(int ind = 1; ind<n; ind++){
            for(int target= 0; target<=tar; target++){

                int notTaken = dp[ind-1][target];

                int taken = 0;
                if(num[ind]<=target)
                    taken = dp[ind-1][target-num[ind]];

                dp[ind][target]= (notTaken + taken)%mod;
            }
        }
        return dp[n-1][tar];
    }

    static int countPartitionsTabulation(int n, int d,int[] arr){
        int totSum = 0;
        for(int i=0; i<n;i++){
            totSum += arr[i];
        }

        //Checking for edge cases
        if(totSum-d <0 || (totSum-d)%2==1 ) return 0;

        return findWays(arr,(totSum-d)/2);
    }


    public static int countPartitionsTabulationOptimised(int n, int d, int[] arr) {
        // Write your code here.
        int totalSum = 0;
        for(int i =0; i < n; i++) totalSum += arr[i];

        if(d > totalSum || (totalSum - d)%2 == 1) return 0;
        int targetSum = (totalSum - d)/2;

        int[] dp = new int[targetSum+1];

        if(arr[0] == 0) dp[0] = 2;
        else dp[0] = 1;
        if( arr[0] != 0 && arr[0] <= targetSum) dp[arr[0]] = 1;

        for(int index = 1; index < n; index++){
            for(int target = targetSum; target >= 0; target--){
                int notTake = dp[target];
                int take = 0;
                if(arr[index] <= target)
                    take =dp[target-arr[index]];

                dp[target] = (notTake + take) % mod;
            }
        }
        return dp[targetSum];
    }

    public static void main(String[] args) {

        int arr[] = {5, 2, 6, 4};
        int n = arr.length;
        int d = 3;
        System.out.println("The number of subsets found are " + countPartitions(n, d, arr));
        System.out.println("The number of subsets found are " + countPartitionsTabulation(n, d, arr));
        System.out.println("The number of subsets found are " + countPartitionsTabulationOptimised(n, d, arr));
    }
}
