package dp.subsequence;

public class MinimumSubsetSumDifference {
    public static int minSubsetSumDifference(int []arr, int n) {
        // Write your code here.
        int totalSum = 0;
        for(int i = 0; i<n; i++){
            totalSum += arr[i];
        }

        int k = (totalSum/2)+1;

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
        int min = Integer.MAX_VALUE;
        for(int s1 = totalSum/2; s1>=0; s1--){
            if(dp[s1]){
                int s2 = totalSum - s1;
                min = Math.abs(s1-s2);
                break;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(minSubsetSumDifference(new int[]{1,2,3,4}, 4));
        System.out.println(minSubsetSumDifference(new int[]{8,6,5}, 3));
    }
}
