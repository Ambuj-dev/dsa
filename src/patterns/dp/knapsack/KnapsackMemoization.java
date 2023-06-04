package patterns.dp.knapsack;

public class KnapsackMemoization {

    public static void main(String[] args) {
        System.out.println(solveKnapsack(new int[]{1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 7));
    }

    private static int solveKnapsack(int[] profits, int[] weights, int capacity){
        int[][] memo = new int[profits.length + 1][capacity+1];
        for(int i = 0; i<= profits.length; i++){
            for(int j =0; j<=capacity; j++){
                memo[i][j] = -1;
            }
        }
        return knapsack(profits, weights, capacity, 0, memo);
    }

    private static int knapsack(int[] profits, int[] weights, int capacity, int currentIndex, int[][] memo){
        if(capacity == 0 || currentIndex >= profits.length) return 0;
        if(memo[currentIndex][capacity] != -1) return memo[currentIndex][capacity];
        int currentProfit = 0;
        if(weights[currentIndex] <= capacity){
            currentProfit = profits[currentIndex] + knapsack(profits, weights, capacity-weights[currentIndex], currentIndex+1, memo);
        }
        int currentProfitWithoutCurrentItem = knapsack(profits, weights, capacity, currentIndex+1, memo);
        return memo[currentIndex][capacity] = Math.max(currentProfit, currentProfitWithoutCurrentItem);
    }
    public int solveKnapsack1(int[] profits, int[] weights, int capacity) {
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return this.knapsackRecursive(dp, profits, weights, capacity, 0);
    }

    private int knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity,
                                  int currentIndex) {

        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;

        // if we have already solved a similar problem, return the result from memory
        if(dp[currentIndex][capacity] != null)
            return dp[currentIndex][capacity];

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profit1 = 0;
        if( weights[currentIndex] <= capacity )
            profit1 = profits[currentIndex] + knapsackRecursive(dp, profits, weights,
                    capacity - weights[currentIndex], currentIndex + 1);

        // recursive call after excluding the element at the currentIndex
        int profit2 = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);

        dp[currentIndex][capacity] = Math.max(profit1, profit2);
        return dp[currentIndex][capacity];
    }

}
