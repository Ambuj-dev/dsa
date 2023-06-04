package patterns.dp.knapsack;

public class KnapsackTabulation {

    public static void main(String[] args) {
        System.out.println(solveKnapsack(new int[]{1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 7));
        System.out.println(solveKnapsack1(new int[]{1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 7));
        System.out.println(solveKnapsack2(new int[]{4,5,3,7}, new int[]{2,3,1,4}, 5));
        System.out.println(solveKnapsack2(new int[]{1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 7));
    }
    public static int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[][] dp = new int[n][capacity + 1];

        // populate the capacity=0 columns, with '0' capacity we have '0' profit
        //not necessary as by default 0
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        // if we have only one weight, we will take it if it is not more than the capacity
        for(int c=0; c <= capacity; c++) {
            if(weights[0] <= c)
                dp[0][c] = profits[0];
        }

        // process all sub-arrays for all the capacities
        for(int i=1; i < n; i++) {
            for(int c=1; c <= capacity; c++) {
                int profit1= 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[i-1][c-weights[i]];
                // exclude the item
                profit2 = dp[i-1][c];
                // take maximum
                dp[i][c] = Math.max(profit1, profit2);
            }
        }
        printSelectedItems(dp, weights, profits, capacity);
        // maximum profit will be at the bottom-right corner.
        return dp[n-1][capacity];
    }

    private static void printSelectedItems(int[][] dp, int[] weights, int[] profits, int capacity){
        int totalProfits = dp[weights.length - 1][capacity];
        for(int i = weights.length - 1; i>0; i--){
            if(dp[i-1][capacity] != totalProfits){
                totalProfits -= profits[i];
                System.out.print(weights[i]+" ");
                capacity -= weights[i];
            }
        }
        if(totalProfits != 0)
            System.out.println(weights[0]);
        System.out.println("");
    }

    //We only need one previous row to find the optimal solution!
    //SC:O(2*C) where C is capacity
    public static int solveKnapsack1(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        // we only need one previous row to find the optimal solution, overall we need '2' rows
        // the above solution is similar to the previous solution, the only difference is that
        // we use `i%2` instead if `i` and `(i-1)%2` instead if `i-1`
        int[][] dp = new int[2][capacity+1];

        // if we have only one weight, we will take it if it is not more than the capacity
        for(int c=0; c <= capacity; c++) {
            if(weights[0] <= c)
                dp[0][c] = dp[1][c] = profits[0];
        }

        // process all sub-arrays for all the capacities
        for(int i=1; i < n; i++) {
            for(int c=0; c <= capacity; c++) {
                int profit1= 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[(i-1)%2][c-weights[i]];
                // exclude the item
                profit2 = dp[(i-1)%2][c];
                // take maximum
                dp[i%2][c] = Math.max(profit1, profit2);
            }
        }

        return dp[(n-1)%2][capacity];
    }


    public static int solveKnapsack2(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[] dp = new int[capacity + 1];

        // if we have only one weight, we will take it if it is not more than the
        // capacity
        for (int c = 0; c <= capacity; c++) {
            if (weights[0] <= c)
                dp[c] = profits[0];
        }

        // process all sub-arrays for all the capacities
        for (int i = 1; i < n; i++) {
            for (int c = capacity; c >= 0; c--) {
                int profit1 = 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if (weights[i] <= c)
                    profit1 = profits[i] + dp[c - weights[i]];
                // exclude the item
                profit2 = dp[c];
                // take maximum
                dp[c] = Math.max(profit1, profit2);
            }
        }

        return dp[capacity];
    }
}
