package dp.lis;

import java.util.Arrays;

public class FindNumberOfLIS {
    public static int findNumberOfLIS(int []nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int maxLength = 1;
        for (int i=0; i<n; i++) {
            for (int prev=0; prev<i; prev++) {
                if (nums[i] > nums[prev] && dp[prev] + 1 > dp[i]) {
                    dp[i] = dp[prev] + 1;
                    count[i] = count[prev];
                }
                else if (nums[i] > nums[prev] && dp[prev] + 1 == dp[i]) {
                    count[i] += count[prev];
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        int nos =0;
        for(int i=0; i<=n-1; i++){
            if(dp[i]==maxLength) nos+=count[i];
        }

        return nos;
    }

    public static void main(String args[]) {

        int[] arr = {1,5,4,3,2,6,7,2};

        System.out.println("The count of LIS is "+findNumberOfLIS(arr));

    }

}
