package patterns.dp.unboundedknapsack;

public class MinimumCoinChangeTabulation {
    public int countChange(int[] denominations, int total)
    {
        int n = denominations.length;
        int[][] dp = new int[n][total + 1];

        for(int i=0; i < n; i++)
            for(int j=0; j <= total; j++)
                dp[i][j] = Integer.MAX_VALUE - 1;

        // populate the total=0 columns, as we don't need any coin to make zero total
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        for(int i=0; i < n; i++) {
            for(int t=1; t <= total; t++) {
                if(i > 0)
                    dp[i][t] = dp[i-1][t]; //exclude the coin
                if(t >= denominations[i]) {
                        dp[i][t] = Math.min(dp[i][t], dp[i][t-denominations[i]]+1); // include the coin
                }
            }
        }

        // total combinations will be at the bottom-right corner.
        return (dp[n-1][total] == Integer.MAX_VALUE - 1 ? -1 : dp[n-1][total]);
    }

    public int countChangeOptimised(int[] denominations, int total) {
        int n = denominations.length;
        int[] dp = new int[total + 1];

        for (int j = 0; j <= total; j++)
            dp[j] = Integer.MAX_VALUE - 1;

        // populate the total=0 columns, as we don't need any coin to make zero total
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int t = 1; t <= total; t++) {
                if (t >= denominations[i]) {
                    dp[t] = Math.min(dp[t], dp[t - denominations[i]] + 1); // include the coin
                }
            }
        }

        // total combinations will be at the bottom-right corner.
        return (dp[total] == Integer.MAX_VALUE - 1 ? -1 : dp[total]);
    }


    public static void main(String[] args) {
        MinimumCoinChangeTabulation cc = new MinimumCoinChangeTabulation();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
        System.out.println(cc.countChange(denominations, 11));
        System.out.println(cc.countChange(denominations, 7));
        System.out.println(cc.countChangeOptimised(denominations, 5));
        System.out.println(cc.countChangeOptimised(denominations, 11));
        System.out.println(cc.countChangeOptimised(denominations, 7));
        denominations = new int[]{3, 5};
        System.out.println(cc.countChange(denominations, 7));
        System.out.println(cc.countChangeOptimised(denominations, 7));
    }
}
