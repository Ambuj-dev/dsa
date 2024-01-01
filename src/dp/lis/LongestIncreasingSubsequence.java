package dp.lis;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    //TC: O(N*N)
    // SC: O(N*N) + O(N)
    public static int longestIncreasingSubsequence(int arr[]) {
        //Your code goes here
        int n = arr.length;
        Integer[][] dp = new Integer[n+1][n+1];
        return longestIncreasingSubsequence(0, -1, arr, dp);
    }

    public static int longestIncreasingSubsequence(int ind, int prevInd, int arr[], Integer[][] dp) {
        //Your code goes here
        if(ind == arr.length) return 0;

        if(dp[ind][prevInd+1] != null) return dp[ind][prevInd+1];
        int notTake = 0 + longestIncreasingSubsequence(ind+1, prevInd, arr, dp);
        int take = Integer.MIN_VALUE;
        if(prevInd == -1 || arr[ind] > arr[prevInd]){
            take = 1 + longestIncreasingSubsequence(ind+1, ind, arr, dp);
        }
        return dp[ind][prevInd+1] = Math.max(take, notTake);
    }

    //TC: O(N*N)
    // SC: O(N*N)
    public static int longestIncreasingSubsequenceTabulation(int arr[]) {
        //Your code goes here
        int n = arr.length;
        int[][] dp = new int[n+1][n+1];
        for(int ind = n-1; ind >=0; ind--){
            for(int prevInd = ind-1; prevInd >= -1; prevInd--){
                int notTake = 0 + dp[ind+1][prevInd+1];
                int take = Integer.MIN_VALUE;
                if(prevInd == -1 || arr[ind] > arr[prevInd]){
                    take = 1+ dp[ind+1][ind+1];
                }
                dp[ind][prevInd+1] = Math.max(take, notTake);
            }
        }

        return dp[0][0];
    }

    //TC: O(N*N)
    // SC: O(N)
    public static int longestIncreasingSubsequenceTabulationOptimised(int arr[]) {
        //Your code goes here
        int n = arr.length;
        int[] next = new int[n+1];

        for(int ind = n-1; ind >=0; ind--){
            int[] cur = new int[n+1];
            for(int prevInd = ind-1; prevInd >= -1; prevInd--){
                int notTake = 0 + next[prevInd+1];
                int take = Integer.MIN_VALUE;
                if(prevInd == -1 || arr[ind] > arr[prevInd]){
                    take = 1+ next[ind+1];
                }
                cur[prevInd+1] = Math.max(take, notTake);
            }
            next = cur;
        }

        return next[0];
    }


    //TC: O(N*N)
    // SC: O(N)
    //Required for Trace back to find the LIS.
    public static int longestIncreasingSubsequenceTabulationOptimised1(int nums[]) {
        //Your code goes here
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxLength = 1;
        for (int i=0; i<nums.length; i++) {
            for (int prev=0; prev<i; prev++) {
                if (nums[i] > nums[prev]) {
                    dp[i] = Math.max(dp[i], dp[prev] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;

    }

    //TC: O(N*logN)
    //SC: 0(N)
    public static int longestIncreasingSubsequenceUsingBinarySearchAndDP(int arr[])
    {
        int n = arr.length;

        // dp[i] represents i+1'th length LIS ending at minimum integer dp[i]
        int dp[] = new int[n];
        int ans = 0;

        for (int i = 0; i < n; i++)
        {
			/*
			    Since dp array stores elements in the sorted order therefore
			    we can use binary search to find the correct position for
			    arr[i] to be placed.
			    And elements are present in the dp array from 0 to ans-1 position
			    So we will be doing the binary search in this range.
			*/
            int position = lowerBound(dp, 0, ans, arr[i]);
            dp[position] = arr[i];

            if (position == ans)
            {
                ans++;
            }
        }

        return ans;
    }

    private static int lowerBound(int[] a, int low, int high, int element)
    {
        while (low < high)
        {
            int middle = low + (high - low) / 2;
            if (element > a[middle])
            {
                low = middle + 1;
            } else
            {
                high = middle;
            }
        }

        return low;
    }

    public static void main(String args[]) {

        int arr[] = {10,9,2,5,3,7,101,18};

        int n = arr.length;

        System.out.println("The length of the longest increasing subsequence is "+longestIncreasingSubsequence(arr));
        System.out.println("The length of the longest increasing subsequence is "+longestIncreasingSubsequenceTabulation(arr));
        System.out.println("The length of the longest increasing subsequence is "+longestIncreasingSubsequenceTabulationOptimised(arr));
        System.out.println("The length of the longest increasing subsequence is "+longestIncreasingSubsequenceTabulationOptimised1(arr));
        System.out.println("The length of the longest increasing subsequence is "+longestIncreasingSubsequenceUsingBinarySearchAndDP(arr));

    }


}
