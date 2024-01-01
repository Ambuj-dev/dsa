package dp.stocks;

public class StockBuySellKTimes {

    public static int maximumProfit(int[] prices, int n, int k)
    {
        // Write your code here.
        //int n = prices.length;
        int[][] ahead = new int[2][k+1];


        for(int ind = n-1; ind >= 0; ind--){
            int[][] cur = new int[2][k+1];
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = 1; cap <=k; cap++){
                    if(buy == 1){
                        cur[buy][cap] = Math.max(-prices[ind] + ahead[0][cap],
                                0 + ahead[1][cap]);
                    }else{
                        cur[buy][cap] = Math.max(prices[ind] + ahead[1][cap-1],
                                0 + ahead[0][cap]);
                    }
                }
            }
            ahead = cur;
        }
        return ahead[1][k];
    }
}

