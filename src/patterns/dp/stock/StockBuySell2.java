package patterns.dp.stock;

import java.util.Arrays;

public class StockBuySell2 {

    static long getMaximumProfitRecursive(long Arr[], int n)
    {
        Long dp[][]=new Long[n+1][2];
        if(n== 0) return 0;
        return getMaximumProfitRecursive(Arr, 0, 0, n, dp);
    }

    static long getMaximumProfitRecursive(long Arr[], int index, int buy, int n, Long[][] dp)
    {
        if(index == n) return 0;
        if(dp[index][buy] != null) return dp[index][buy];
        long profit = 0;
        if(buy == 0)  profit = Math.max(0 + getMaximumProfitRecursive(Arr, index+1, 0,n, dp),
                -Arr[index]+ getMaximumProfitRecursive(Arr, index+1, 1, n, dp));
        if(buy == 1)  profit = Math.max(0 + getMaximumProfitRecursive(Arr, index+1, 1,n, dp),
                Arr[index]+ getMaximumProfitRecursive(Arr, index+1, 0, n, dp));

        return dp[index][buy] = profit;
    }
    static long getMaximumProfit(long Arr[], int n)
    {
        //Write your code here
        long dp[][]=new long[n+1][2];

        //base condition
        dp[n][0] = dp[n][1] = 0;

        long profit=0;

        for(int ind= n-1; ind>=0; ind--){
            for(int buy=0; buy<=1; buy++){
                if(buy==0){// We can buy the stock
                    profit = Math.max(0+dp[ind+1][0], -Arr[ind] + dp[ind+1][1]);
                }

                if(buy==1){// We can sell the stock
                    profit = Math.max(0+dp[ind+1][1], Arr[ind] + dp[ind+1][0]);
                }

                dp[ind][buy]  = profit;
            }
        }
        return dp[0][0];
    }
    static long getMaximumProfitOptimised(long Arr[], int n)
    {
        //Write your code here

        long ahead[]=new long[2];
        long cur[] =new long[2];

        //base condition
        ahead[0] = ahead[1] = 0;

        long profit=0;

        for(int ind= n-1; ind>=0; ind--){
            for(int buy=0; buy<=1; buy++){
                if(buy==0){// We can buy the stock
                    profit = Math.max(0+ahead[0], -Arr[ind] + ahead[1]);
                }

                if(buy==1){// We can sell the stock
                    profit = Math.max(0+ahead[1], Arr[ind] + ahead[0]);
                }
                cur[buy]  = profit;
            }

            ahead = cur.clone();
        }
        return cur[0];
    }

    static long getMaximumProfitOptimised1(long Arr[], int n) {
        //Write your code here
        long aheadBuy = 0, aheadNotBuy = 0, curBuy, curNotBuy;

        for (int ind = n - 1; ind >= 0; ind--) {
            curBuy = Math.max(0 + aheadBuy, -Arr[ind] + aheadNotBuy);
            curNotBuy = Math.max(0 + aheadNotBuy, Arr[ind] + aheadBuy);
            aheadBuy = curBuy;
            aheadNotBuy = curNotBuy;

        }
        return aheadBuy;
    }


    public static void main(String args[]) {

        int n =6;
        long Arr[] = {7,1,5,3,6,4};

        System.out.println("The maximum profit that can be generated is "+getMaximumProfitRecursive(Arr, n));//7
        System.out.println("The maximum profit that can be generated is "+getMaximumProfit(Arr, n));//7
        System.out.println("The maximum profit that can be generated is "+getMaximumProfitOptimised(Arr, n));//7
        System.out.println("The maximum profit that can be generated is "+getMaximumProfitOptimised1(Arr, n));//7
    }
}
