package dp.subsequence;

public class Knapsack {

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        Integer[][] dp = new Integer[n][maxWeight+1];
        return knapsackHelper(weight, value, n-1, maxWeight, dp);
    }

    static int knapsackHelper(int[] weight, int[] value, int index, int maxWeight, Integer[][] dp){

        if(index == 0){
            if(weight[index] <= maxWeight) return value[index];
            return 0;
        }
        if(dp[index][maxWeight] != null) return dp[index][maxWeight];

        int notTake = 0 + knapsackHelper(weight, value, index-1, maxWeight, dp);
        int take = 0;
        if(weight[index] <= maxWeight)
            take = value[index]+ knapsackHelper(weight, value, index-1, maxWeight-weight[index], dp);

        return dp[index][maxWeight] = Math.max(notTake, take);
    }

    static int knapsackTabulation(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight+1];
        for(int i = weight[0]; i <= maxWeight; i++){
            dp[0][i] = value[0];
        }

        for(int index = 1; index < n; index++){
            for(int cap = 0; cap <= maxWeight; cap++){
                int notTake = 0 + dp[index-1][cap];
                int take = 0;
                if(weight[index] <= cap)
                    take = value[index]+ dp[index-1][cap-weight[index]];

                dp[index][cap] = Math.max(notTake, take);
            }
        }

        return dp[n-1][maxWeight];
    }

    static int knapsackTabulationOptimised(int[] weight, int[] value, int n, int maxWeight) {
        int[] dp = new int[maxWeight+1];
        for(int i = weight[0]; i <= maxWeight; i++){
            dp[i] = value[0];
        }

        for(int index = 1; index < n; index++){
            for(int cap = maxWeight; cap >= 0; cap--){
                int notTake = 0 + dp[cap];
                int take = 0;
                if(weight[index] <= cap)
                    take = value[index]+ dp[cap-weight[index]];

                dp[cap] = Math.max(notTake, take);
            }
        }

        return dp[maxWeight];
    }


    public static void main(String args[]) {

        int wt[] = {1,2,4,5};
        int val[] = {5,4,8,6};
        int W=5;

        int n = wt.length;

        System.out.println("The Maximum value of items, thief can steal is "+
                knapsack(wt,val,n,W));

        System.out.println("The Maximum value of items, thief can steal is "+
                knapsackTabulation(wt,val,n,W));

        System.out.println("The Maximum value of items, thief can steal is "+
                knapsackTabulationOptimised(wt,val,n,W));
    }

}
