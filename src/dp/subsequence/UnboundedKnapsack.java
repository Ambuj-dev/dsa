package dp.subsequence;

public class UnboundedKnapsack {

    static int unboundedKnapsack(int[] weight, int[] value, int n, int maxWeight) {
        Integer[][] dp = new Integer[n][maxWeight+1];
        return unboundedKnapsackHelper(weight, value, n-1, maxWeight, dp);
    }

    static int unboundedKnapsackHelper(int[] weight, int[] value, int index, int maxWeight, Integer[][] dp){

        if(index == 0){
            if(weight[index] <= maxWeight) return (maxWeight/weight[index])*value[index];
            return 0;
        }
        if(dp[index][maxWeight] != null) return dp[index][maxWeight];

        int notTake = 0 + unboundedKnapsackHelper(weight, value, index-1, maxWeight, dp);
        int take = 0;
        if(weight[index] <= maxWeight)
            take = value[index]+ unboundedKnapsackHelper(weight, value, index, maxWeight-weight[index], dp);

        return dp[index][maxWeight] = Math.max(notTake, take);
    }

    static int unboundedKnapsackTabulation(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight+1];
        for(int w = 0; w<= maxWeight; w++){
            dp[0][w] = (w/weight[0]) * value[0];
        }
        for(int index = 1; index < n; index++){
            for(int cap = 0; cap <= maxWeight; cap++){
                int notTake = 0 + dp[index-1][cap];
                int take = 0;
                if(weight[index] <= cap)
                    take = value[index]+ dp[index][cap-weight[index]];

                dp[index][cap] = Math.max(notTake, take);
            }
        }
        return dp[n-1][maxWeight];
    }



    public static int unboundedKnapsackTabulationOptimised(int[] wt, int[] val, int n, int W) {
        // Write your code here.

        int cur[]=new int[W+1];

        //Base Condition

        for(int i=0; i<=W; i++){
            cur[i] = (i/wt[0]) * val[0];
        }

        for(int ind =1; ind<n; ind++){
            for(int cap=0; cap<=W; cap++){

                int notTaken = cur[cap];

                int taken = Integer.MIN_VALUE;
                if(wt[ind] <= cap)
                    taken = val[ind] + cur[cap - wt[ind]];

                cur[cap] = Math.max(notTaken, taken);
            }
        }

        return cur[W];

    }

    public static void main(String args[]) {

        int wt[] = {4,2, 6};
        int val[] = {5,11,13};
        int W=10;

        int n = wt.length;

        System.out.println("The Maximum value of items, thief can steal is "+unboundedKnapsack(wt, val, n , W));
        System.out.println("The Maximum value of items, thief can steal is "+unboundedKnapsackTabulation(wt, val, n , W));
        System.out.println("The Maximum value of items, thief can steal is "+unboundedKnapsackTabulationOptimised(wt, val, n , W));
    }

}
