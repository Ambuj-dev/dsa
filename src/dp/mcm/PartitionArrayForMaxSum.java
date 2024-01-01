package dp.mcm;

public class PartitionArrayForMaxSum {
    public static int maximumSubarray(int arr[], int k) {
        int n = arr.length;
        Integer[] dp = new Integer[n];
        return maximumSubarray(arr, k, 0, dp);
    }

    public static int maximumSubarray(int num[], int k, int ind, Integer[] dp) {
        int n = num.length;
        //base case:
        if (ind == n) return 0;
        if(dp[ind] != null) return dp[ind];

        int len = 0;
        int maxi = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;
        for (int j = ind; j < Math.min(ind + k, n); j++) {
            len++;
            maxi = Math.max(maxi, num[j]);
            int sum = len * maxi + maximumSubarray(num, k, j + 1, dp);
            maxAns = Math.max(maxAns, sum);
        }
        return dp[ind] = maxAns;
    }

    public static int maximumSubarrayTabulation(int arr[], int k) {
        int n = arr.length;
        int[] dp = new int[n+1];
        for(int ind =  n-1; ind >=0; ind--){
            int len = 0;
            int maxi = Integer.MIN_VALUE;
            int maxAns = Integer.MIN_VALUE;
            for (int j = ind; j < Math.min(ind + k, n); j++) {
                len++;
                maxi = Math.max(maxi, arr[j]);
                int sum = len * maxi + dp[j + 1];
                maxAns = Math.max(maxAns, sum);
            }
            dp[ind] = maxAns;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[] num = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;
        System.out.println(maximumSubarray(num, k));
        System.out.println(maximumSubarrayTabulation(num, k));
    }
}
