package patterns.dp.unboundedknapsack;

public class CoinChangeTabulation {
    public int countChange(int[] denominations, int total) {
        int n = denominations.length;
        int[][] dp = new int[n][total + 1];

        // populate the total=0 columns, as we will always have an empty set for zero total
        for(int i=0; i < n; i++)
            dp[i][0] = 1;

        // process all sub-arrays for all capacities
        for(int i=0; i < n; i++) {
            for(int t=1; t <= total; t++) {
                if(i > 0)
                    dp[i][t] = dp[i-1][t];
                if(t >= denominations[i])
                    dp[i][t] += dp[i][t-denominations[i]];
            }
        }

        // total combinations will be in the bottom-right corner.
        return dp[n-1][total];
    }

    public int countChangeOptimised(int[] denominations, int total) {
        int n = denominations.length;
        int[] dp = new int[total + 1];

        // populate the total=0 columns, as we will always have an empty set for zero total
       // for(int i=0; i < n; i++)
            dp[0] = 1;

        // process all sub-arrays for all capacities
        for(int i=0; i < n; i++) {
            for(int t=1; t <= total; t++) {
                if(t >= denominations[i])
                    dp[t] += dp[t-denominations[i]];
            }
        }

        // total combinations will be in the bottom-right corner.
        return dp[total];
    }

    public static void main(String[] args) {
        CoinChangeTabulation cc = new CoinChangeTabulation();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
        System.out.println(cc.countChangeOptimised(denominations, 5));
    }
}
