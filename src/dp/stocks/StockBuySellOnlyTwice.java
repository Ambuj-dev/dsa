package dp.stocks;

public class StockBuySellOnlyTwice {
    public static int maxProfit(int []prices) {
        // Write your code here.
        int n = prices.length;
        Integer[][][] dp = new Integer[n+1][2][3];
        return maxProfitHelper(0, 1, 2, prices, n, dp);
    }

    public static int maxProfitHelper(int ind, int buy, int cap, int []prices, int n, Integer[][][] dp) {
        // Write your code here.
        if(ind == n || cap == 0) return 0;
        if(dp[ind][buy][cap] != null) return dp[ind][buy][cap];
        if(buy == 1){
            return dp[ind][buy][cap] = Math.max(-prices[ind] + maxProfitHelper(ind+1, 0, cap, prices, n, dp),
                    0 + maxProfitHelper(ind+1, 1, cap, prices, n, dp));
        }else{
            return dp[ind][buy][cap] = Math.max(prices[ind] + maxProfitHelper(ind+1, 1, cap-1, prices, n, dp),
                    0 + maxProfitHelper(ind+1, 0, cap, prices, n, dp));
        }

    }

    public static int maxProfitTabulation(int []prices) {
        // Write your code here.
        int n = prices.length;
        int[][][] dp = new int[n+1][2][3];

        for(int ind = n-1; ind >= 0; ind--){
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = 1; cap <= 2; cap++){
                    if(buy == 1){
                        dp[ind][buy][cap] = Math.max(-prices[ind] + dp[ind+1][0][cap],
                                0 + dp[ind+1][1][cap]);
                    }else{
                        dp[ind][buy][cap] = Math.max(prices[ind] +dp[ind+1][1][cap-1],
                                0 + dp[ind+1][0][cap]);
                    }
                }
            }
        }
        return dp[0][1][2];
    }
    public static int maxProfitTabulationOptimised(int []prices) {
        // Write your code here.
        int n = prices.length;
        int[][] ahead = new int[2][3];


        for(int ind = n-1; ind >= 0; ind--){
            int[][] cur = new int[2][3];
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = 1; cap <=2; cap++){
                    if(buy == 1){
                        cur[buy][cap] = Math.max(-prices[ind] + ahead[0][cap],
                                0 + ahead[1][cap]);
                    }else{
                        cur[buy][cap] = Math.max(prices[ind] +ahead[1][cap-1],
                                0 + ahead[0][cap]);
                    }
                }
            }
            ahead = cur;
        }
        return ahead[1][2];
    }

    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};

        System.out.println("The maximum profit that can be generated is "+maxProfit(prices));
        System.out.println("The maximum profit that can be generated is "+maxProfitTabulation(prices));
        System.out.println("The maximum profit that can be generated is "+maxProfitTabulationOptimised(prices));

    }

}
