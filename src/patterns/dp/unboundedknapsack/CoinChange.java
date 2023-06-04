package patterns.dp.unboundedknapsack;

public class CoinChange {

    public int countChange(int[] denominations, int total) {
        return this.countChangeRecursive(denominations, total, 0);
    }

    private int countChangeRecursive(int[] denominations, int total, int currentIndex) {
        // basic checks
        if (total == 0)
            return 1;

        if(denominations.length == 0 || currentIndex >= denominations.length)
            return 0;

        // recursive call after selecting the coin at the currentIndex
        // if the coin at currentIndex exceeds the total, we shouldn't process this
        int sum1 = 0;
        if( denominations[currentIndex] <= total )
            sum1 = countChangeRecursive(denominations, total - denominations[currentIndex], currentIndex);

        // recursive call after excluding the coin at the currentIndex
        int sum2 = countChangeRecursive(denominations, total, currentIndex + 1);

        return sum1 + sum2;
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
    }
}
