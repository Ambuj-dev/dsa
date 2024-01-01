package dp.subsequence;

public class TargetSum {
        static int mod = (int) (Math.pow(10, 9) + 7);
        public static int targetSum(int n, int target, int[] arr) {
            int totalSum = 0;
            for (int i = 0; i < n; i++) totalSum += arr[i];

            if (target > totalSum || (totalSum - target) % 2 == 1) return 0;
            int targetSum = (totalSum - target) / 2;
            Integer[][] dp = new Integer[n][targetSum + 1];
            return targetSumHelper(n - 1, targetSum, arr, dp);
        }

        public static int targetSumHelper(int index, int target, int[] arr, Integer[][] dp) {

            if(index == 0 ){
                if(arr[0] == 0 && target == 0) return 2;
                if(target == 0 || arr[0] == target) return 1;
                return 0;
            }

            if(dp[index][target] != null) return dp[index][target];

            int notTaken = targetSumHelper(index - 1, target, arr, dp);
            int taken = 0;
            if (arr[index] <= target)
                taken = targetSumHelper(index - 1, target - arr[index], arr, dp);

            return dp[index][target] = (notTaken + taken ) % mod;
        }

        static int targetSumTabulation(int n, int target,int[] arr){
            int totSum = 0;
            for(int i=0; i<n;i++){
                totSum += arr[i];
            }

            //Checking for edge cases
            if(totSum-target <0 || (totSum-target)%2==1 ) return 0;

            target = (totSum-target)/2;

            int[][] dp=new int[n][target+1];

            if(arr[0] == 0) dp[0][0] =2;  // 2 cases -pick and not pick
            else dp[0][0] = 1;  // 1 case - not pick

            if(arr[0]!=0 && arr[0]<=target) dp[0][arr[0]] = 1;  // 1 case -pick

            for(int ind = 1; ind<n; ind++){
                for(int tar= 0; tar<=target; tar++){

                    int notTaken = dp[ind-1][tar];

                    int taken = 0;
                    if(arr[ind]<=tar)
                        taken = dp[ind-1][tar-arr[ind]];

                    dp[ind][tar]= (notTaken + taken)%mod;
                }
            }
            return dp[n-1][target];
        }

    static int targetSumTabulationOptimised(int n, int target,int[] arr){
        int totSum = 0;
        for(int i=0; i<n;i++){
            totSum += arr[i];
        }

        //Checking for edge cases
        if(totSum-target <0 || (totSum-target)%2==1 ) return 0;

        target = (totSum-target)/2;

        int prev[]=new int[target+1];

        if(arr[0] == 0) prev[0] =2;  // 2 cases -pick and not pick
        else prev[0] = 1;  // 1 case - not pick

        if(arr[0]!=0 && arr[0]<=target) prev[arr[0]] = 1;  // 1 case -pick

        for(int ind = 1; ind<n; ind++){
            int cur[]=new int[target+1];
            for(int tar= 0; tar<=target; tar++){
                int notTaken = prev[tar];

                int taken = 0;
                if(arr[ind]<=tar)
                    taken = prev[tar-arr[ind]];

                cur[tar]= (notTaken + taken)%mod;
            }
            prev = cur;
        }
        return prev[target];

    }

        public static void main(String args[]) {

            int arr[] = {1,2,3,1};
            int target=3;

            int n = arr.length;
            System.out.println("The number of ways found is "+targetSum(n,target,arr));
            System.out.println("The number of ways found is "+targetSumTabulation(n,target,arr));
            System.out.println("The number of ways found is "+targetSumTabulationOptimised(n,target,arr));
        }

    }
