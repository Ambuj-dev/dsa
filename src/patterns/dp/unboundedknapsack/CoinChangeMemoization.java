package patterns.dp.unboundedknapsack;

public class CoinChangeMemoization {

    public int countChange(int[] denominations, int total) {
        Integer[][] dp = new Integer[denominations.length][total + 1];
        return this.countChangeRecursive(denominations, total, 0, dp);
    }

    private int countChangeRecursive(int[] denominations, int total, int currentIndex, Integer[][] dp) {
        // basic checks
        if (total == 0)
            return 1;

        if(denominations.length == 0 || currentIndex >= denominations.length)
            return 0;

        if(dp[currentIndex][total] != null) return dp[currentIndex][total];

        // recursive call after selecting the coin at the currentIndex
        // if the coin at currentIndex exceeds the total, we shouldn't process this
        int sum1 = 0;
        if( denominations[currentIndex] <= total )
            sum1 = countChangeRecursive(denominations, total - denominations[currentIndex], currentIndex, dp);

        // recursive call after excluding the coin at the currentIndex
        int sum2 = countChangeRecursive(denominations, total, currentIndex + 1, dp);

        return dp[currentIndex][total] = sum1 + sum2;
    }

    public static void main(String[] args) {
        CoinChangeMemoization cc = new CoinChangeMemoization();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
    }
}
