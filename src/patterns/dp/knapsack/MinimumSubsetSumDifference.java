package patterns.dp.knapsack;

import java.util.Arrays;

public class MinimumSubsetSumDifference {
    public static void main(String[] args) {
        System.out.println(minimumDifference(new int[]{3,9,7,3}));
        System.out.println(minimumDifference(new int[]{-36, 36}));
        System.out.println(minimumDifferenceMemoization(new int[]{3,9,7,3}));
        System.out.println(minimumDifferenceTabulation1(new int[]{3,9,7,3}));
        //System.out.println(minimumDifferenceMemoization(new int[]{-36, 36}));

        int[] num = {1, 2, 3, 9};
        System.out.println(minimumDifferenceTabulation(num));
        System.out.println(minimumDifferenceTabulationOptimised(num));
        System.out.println(minimumDifferenceTabulation1(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(minimumDifferenceTabulation(num));
        System.out.println(minimumDifferenceTabulationOptimised(num));
        System.out.println(minimumDifferenceTabulation1(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(minimumDifferenceTabulation(num));
        System.out.println(minimumDifferenceTabulationOptimised(num));
        System.out.println(minimumDifferenceTabulation1(num));
        num = new int[]{1, 6,11,5};
        System.out.println(minimumDifferenceTabulation(num));
        System.out.println(minimumDifferenceTabulationOptimised(num));
        System.out.println(minimumDifferenceTabulation1(num));
       System.out.println(minimumDifferenceTabulation1(new int[]{-36, 36}));
    }

    public static int minimumDifference(int[] nums) {
        return minimumDifferenceRecursive(nums, 0, 0, 0);
    }

    public static int minimumDifferenceRecursive(int[] num, int currentIndex, int sum1, int sum2){
        // base check
        if (currentIndex == num.length)
            return Math.abs(sum1 - sum2);

        // recursive call after including the number at the currentIndex in the first set
        int diff1 = minimumDifferenceRecursive(num, currentIndex+1, sum1+num[currentIndex], sum2);

        // recursive call after including the number at the currentIndex in the second set
        int diff2 = minimumDifferenceRecursive(num, currentIndex+1, sum1, sum2+num[currentIndex]);

        return Math.min(diff1, diff2);
    }

    public static int minimumDifferenceMemoization(int[] nums) {
        int sum = 0;
        for(int i =0; i<nums.length; i++){
            sum += nums[i];
        }
        Integer[][] dp = new Integer[nums.length][sum+1];
        return minimumDifferenceMemoization(nums, 0, 0, 0, dp);
    }

    public static int minimumDifferenceMemoization(int[] nums, int curIndex, int sum1, int sum2, Integer[][] dp){
        if(curIndex == nums.length){
            return Math.abs(sum1 - sum2);
        }
        if(dp[curIndex][sum1] == null) {
            int diff1 = minimumDifferenceMemoization(nums, curIndex + 1, sum1 + nums[curIndex], sum2, dp);
            int diff2 = minimumDifferenceMemoization(nums, curIndex + 1, sum1, sum2 + nums[curIndex], dp);
            dp[curIndex][sum1] =  Math.min(diff1, diff2);
        }
       return dp[curIndex][sum1];
    }
//Will not work in case of negative numbers
    public static int minimumDifferenceTabulation(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            sum += num[i];

        int n = num.length;
        boolean[][] dp = new boolean[n][sum/2 + 1];

        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for(int i=0; i < n; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is equal to that number
        for(int s=1; s <= sum/2 ; s++) {
            dp[0][s] = num[0] == s;
        }

        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
            for(int s=1; s <= sum/2; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if(dp[i-1][s]) {
                    dp[i][s] = dp[i-1][s];
                } else if (s >= num[i]) {
                    // else include the number and see if we can find a subset to get the remaining sum
                    dp[i][s] = dp[i-1][s-num[i]];
                }
            }
        }

        int sum1 = 0;
        // Find the largest index in the last row which is true
        for (int i = sum / 2; i >= 0; i--) {
            if (dp[n-1][i]) {
                sum1 = i;
                break;
            }
        }

        int sum2 = sum - sum1;
        return Math.abs(sum2-sum1);
    }

    public static int minimumDifferenceTabulationOptimised(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            sum += num[i];

        int n = num.length;
        boolean[] dp = new boolean[sum/2 + 1];

        dp[0] = true;
        // with only one number, we can form a subset only when the required sum is equal to that number
        for(int s=1; s <= sum/2 ; s++) {
            dp[s] = num[0] == s;
        }

        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
            for(int s=sum/2; s >= num[i]; s--) {
                // if we can get the sum 's' without the number at index 'i'
                if (dp[s - num[i]]) {
                    dp[s] = true;
                }
            }

        }

        int sum1 = 0;
        // Find the largest index in the last row which is true
        for (int i = sum / 2; i >= 0; i--) {
            if (dp[i]) {
                sum1 = i;
                break;
            }
        }

        int sum2 = sum - sum1;
        return Math.abs(sum2-sum1);
    }

    public static int minimumDifferenceTabulation1(int[] nums) {
        int n = nums.length;
        if (n == 2) return Math.abs(nums[1] - nums[0]);
        int[][] lists1 = generate(Arrays.copyOfRange(nums, 0, n / 2));
        int[][] lists2 = generate(Arrays.copyOfRange(nums, n / 2, n));
        int ans = Integer.MAX_VALUE;
        for (int d = 0; d <= n / 2; d++) {
            int[] arr1 = lists1[d], arr2 = lists2[d];
            int k = arr1.length;
            int i1 = 0, i2 = 0; // we use two pointers to find two elements in arr1, arr2 with minimum absolute difference
            while (i1 < k && i2 < k) {
                int diff = arr1[i1] - arr2[i2];
                ans = Math.min(ans, Math.abs(diff));
                if (diff <= 0) i1++;
                if (diff >= 0) i2++;
            }
        }
        return ans;
    }

    private static int[][] generate(int[] nums) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) total += num;
        int[][] ans = new int[n + 1][];
        int[] pos = new int[n + 1];
        for (int i = 0, binomial = 1; i <= n; i++) {
            ans[i] = new int[binomial]; // number of ways to choose i from n = binomial(i,n)
            binomial = binomial * (n - i) / (i + 1);
        }
        int maxValue = 1 << n;
        for (int key = 0; key < maxValue; key++) {
            int sum1 = 0;
            for (int i = 0; i < n; i++) {
                if ((key >> i & 1) == 1) sum1 += nums[i];
            }
            int sum2 = total - sum1;
            int bits = Integer.bitCount(key);
            ans[bits][pos[bits]++] = sum1 - sum2;
        }
        for (int[] arr : ans) Arrays.sort(arr);
        return ans;
    }
}
