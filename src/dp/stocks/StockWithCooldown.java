package dp.stocks;

public class StockWithCooldown {
    public static int stockProfit(int[] prices) {
        // Write your code here.
        int n = prices.length;
        Integer[][] dp = new Integer[n][2];

        return stockProfit(0, 1, prices, dp);
    }

    public static int stockProfit(int ind, int buy, int[] prices, Integer[][] dp) {
        // Write your code here.

        if(ind >= prices.length) return 0;
        if(dp[ind][buy] != null) return dp[ind][buy];
        if(buy == 1){
            return dp[ind][buy] = Math.max(-prices[ind] + stockProfit(ind+1, 0, prices, dp),
                    0 + stockProfit(ind+1, 1, prices, dp));
        }else{
            return dp[ind][buy] = Math.max(prices[ind] + stockProfit(ind+2, 1, prices, dp),
                    0 + stockProfit(ind+1, 0, prices, dp));
        }
    }

    public static int stockProfitTabulation(int[] prices) {
        // Write your code here.
        int n = prices.length;
        int[][] dp = new int[n+2][2];

        for(int ind = n-1; ind >= 0; ind--){
            for(int buy = 0; buy <=1; buy++){
                if(buy == 1){
                    dp[ind][buy] = Math.max(-prices[ind] + dp[ind+1][0],
                            0 + dp[ind+1][1]);
                }else{
                    dp[ind][buy] = Math.max(prices[ind] + dp[ind+2][1],
                            0 + dp[ind+1][0]);
                }
            }
        }

        return dp[0][1];
    }

    public static int stockProfitTabulationOptimised(int[] prices) {
        // Write your code here.
        int n = prices.length;
        int[][] dp = new int[n+2][2];

        for(int ind = n-1; ind >= 0; ind--){
            dp[ind][1] = Math.max(-prices[ind] + dp[ind+1][0],
                    0 + dp[ind+1][1]);

            dp[ind][0] = Math.max(prices[ind] + dp[ind+2][1],
                    0 + dp[ind+1][0]);
        }

        return dp[0][1];
    }

    public static int stockProfitTabulationOptimised1(int[] prices) {
        // Write your code here.
        int n = prices.length;
        int[] front1 = new int[2];
        int[] front2 = new int[2];

        for(int ind = n-1; ind >= 0; ind--){
            int[] cur = new int[2];
            cur[1] = Math.max(-prices[ind] + front1[0],
                    0 + front1[1]);

            cur[0] = Math.max(prices[ind] + front2[1],
                    0 + front1[0]);

            front2 = front1;
            front1 = cur;
        }

        return front1[1];
    }

    public static void main(String args[]) {

        int prices[]= {4,9,0,4,10};

        System.out.println("The maximum profit that can be generated is "+
                stockProfit(prices));
        System.out.println("The maximum profit that can be generated is "+
                stockProfitTabulation(prices));
        System.out.println("The maximum profit that can be generated is "+
                stockProfitTabulationOptimised(prices));
        System.out.println("The maximum profit that can be generated is "+
                stockProfitTabulationOptimised1(prices));
    }

}
