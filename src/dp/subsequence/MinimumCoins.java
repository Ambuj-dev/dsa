package dp.subsequence;

public class MinimumCoins {
    public static int minimumElements(int num[], int x) {
        int n = num.length;
        Integer[][] dp = new Integer[n][x + 1];
        int result = minimumElement(n - 1, x, num, dp);

        return result == (int) Math.pow(10, 9) ? -1 : result;
    }

    public static int minimumElement(int index, int target, int[] num, Integer[][] dp) {
        if (index == 0) {
            if (target % num[index] == 0) return target / num[index];
            return (int) Math.pow(10, 9);
        }
        if (dp[index][target] != null) return dp[index][target];

        int notTake = minimumElement(index - 1, target, num, dp);
        int take = (int) Math.pow(10, 9);
        if (num[index] <= target)
            take = 1 + minimumElement(index, target - num[index], num, dp);

        return dp[index][target] = Math.min(notTake, take);
    }

    public static int minimumElementsTabulation(int num[], int x) {
        // Write your code here..
        int n = num.length;
        int[][] dp = new int[n][x+1];

        for(int target = 0; target <= x; target++){
            if(target % num[0] == 0)
                dp[0][target] = target / num[0];
            else
                dp[0][target] = (int) Math.pow(10, 9);
        }

        for(int index = 1; index < n; index++){
            for(int target = 0; target <= x; target++){
                int notTake = dp[index-1][target];
                int take = (int) Math.pow(10, 9);
                if(num[index] <= target)
                    take = 1 + dp[index][target- num[index]];

                dp[index][target] = Math.min(notTake, take);
            }
        }

        return dp[n-1][x]  >= (int) Math.pow(10, 9) ? -1 : dp[n-1][x] ;
    }
    static int minimumElements1(int[] arr, int T){

        int n= arr.length;

        int prev[]=new int[T+1];
        int cur[] =new int[T+1];

        for(int i=0; i<=T; i++){
            if(i%arr[0] == 0)
                prev[i] = i/arr[0];
            else prev[i] = (int)Math.pow(10,9);
        }

        for(int ind = 1; ind<n; ind++){
            for(int target = 0; target<=T; target++){

                int notTake = 0 + prev[target];
                int take = (int)Math.pow(10,9);
                if(arr[ind]<=target)
                    take = 1 + cur[target - arr[ind]];

                cur[target] = Math.min(notTake, take);
            }
            prev = cur;
        }

        int ans = prev[T];
        if(ans >=(int)Math.pow(10,9)) return -1;
        return ans;
    }


    public static int minimumElementsTabulationOptimised(int num[], int x) {
        // Write your code here..
        int n = num.length;
        int[] dp = new int[x+1];

        for(int target = 0; target <= x; target++){
            if(target % num[0] == 0)
                dp[target] = target / num[0];
            else
                dp[target] = (int) Math.pow(10, 9);
        }

        for(int index = 1; index < n; index++){
            for(int target = 0; target <= x; target++){
                int notTake = dp[target];
                int take = (int) Math.pow(10, 9);
                if(num[index] <= target)
                    take = 1 + dp[target- num[index]];

                dp[target] = Math.min(notTake, take);
            }
        }

        return dp[x]  >= (int) Math.pow(10, 9) ? -1 : dp[x] ;
    }

    public static void main(String args[]) {

        int arr[] = {1, 2, 3};
        int T = 7;

        System.out.println("The minimum number of coins required to form the target sum is "+minimumElements(arr,T));
        System.out.println("The minimum number of coins required to form the target sum is "+minimumElementsTabulation(arr,T));
        System.out.println("The minimum number of coins required to form the target sum is "+minimumElementsTabulationOptimised(arr,T));
    }


}
