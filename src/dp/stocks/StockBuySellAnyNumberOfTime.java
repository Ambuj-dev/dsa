package dp.stocks;

public class StockBuySellAnyNumberOfTime {

    public static long getMaximumProfit (int n, long[] values) {
        // Your code goes here.
        Long[][] dp = new Long[n][2];
        return getMaximumProfit(0, 1, n, values, dp);
    }

    public static long getMaximumProfit (int index, int buy, int n, long[] values, Long[][] dp) {
        if(index == n) return 0;

        if(dp[index][buy] != null) return dp[index][buy];
        if(buy == 1){
            return dp[index][buy] = Math.max(-values[index]+getMaximumProfit(index+1, 0, n, values, dp),
                    0+getMaximumProfit(index+1, 1, n, values, dp));
        }else{
            return dp[index][buy] = Math.max(values[index]+getMaximumProfit(index+1, 1, n, values, dp),
                    0+getMaximumProfit(index+1, 0, n, values, dp));
        }
    }

    public static long getMaximumProfitTabulation(int n, long[] values) {
        // Your code goes here.
        long[][] dp = new long[n+1][2];
        dp[n][0] = dp[n][1] = 0;
        for(int index = n-1; index >=0 ; index--){
            for(int buy = 0; buy <= 1; buy++){
                if(buy == 1){
                    dp[index][buy] = Math.max(-values[index]+dp[index+1][0],
                            0+dp[index+1][1]);
                }else{
                    dp[index][buy] = Math.max(values[index]+dp[index+1][1],
                            0+dp[index+1][0]);
                }
            }

        }
        return dp[0][1];
    }

    public static long getMaximumProfitTabulationOptimised(int n, long[] values) {
        // Your code goes here.
        long[] ahead = new long[2];
        ahead[0] = ahead[1] = 0;
        for(int index = n-1; index >=0 ; index--){
            long[] cur = new long[2];
            for(int buy = 0; buy <= 1; buy++){
                if(buy == 1){
                    cur[buy] = Math.max(-values[index]+ahead[0],
                            0+ahead[1]);
                }else{
                    cur[buy] = Math.max(values[index]+ahead[1],
                            0+ahead[0]);
                }
            }
            ahead = cur;
        }
        return ahead[1];
    }

    public static long getMaximumProfitTabulationOptimised1(int n, long[] values) {
        // Your code goes here.
        long[] ahead = new long[2];
        ahead[0] = ahead[1] = 0;
        for(int index = n-1; index >=0 ; index--){
            long[] cur = new long[2];
            cur[1] = Math.max(-values[index]+ahead[0],
                            0+ahead[1]);
            cur[0] = Math.max(values[index]+ahead[1],
                            0+ahead[0]);
            ahead = cur;
        }
        return ahead[1];
    }

    public static long getMaximumProfitTabulationOptimised2(int n, long[] values) {
        // Your code goes here.
        long aheadNotBuy, aheadBuy, curNotBuy, curBuy;
        aheadNotBuy = aheadBuy = 0;
        for(int index = n-1; index >=0 ; index--){
            curBuy = Math.max(-values[index]+aheadNotBuy,
                    0+aheadBuy);
            curNotBuy = Math.max(values[index]+aheadBuy,
                    0+aheadNotBuy);
            aheadNotBuy = curNotBuy;
            aheadBuy = curBuy;
        }
        return aheadBuy;
    }

    public static void main(String args[]) {

        int n =6;
        long Arr[] = {7,1,5,3,6,4};

        System.out.println("The maximum profit that can be generated is "+
                getMaximumProfit(n, Arr));

        System.out.println("The maximum profit that can be generated is "+
                getMaximumProfitTabulation(n, Arr));

        System.out.println("The maximum profit that can be generated is "+
                getMaximumProfitTabulationOptimised(n, Arr));

        System.out.println("The maximum profit that can be generated is "+
                getMaximumProfitTabulationOptimised1(n, Arr));
        System.out.println("The maximum profit that can be generated is "+
                getMaximumProfitTabulationOptimised2(n, Arr));
    }

}
