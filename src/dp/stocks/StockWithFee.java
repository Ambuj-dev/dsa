package dp.stocks;

public class StockWithFee {
    public static int maximumProfit(int[] values, int n, int fee) {
        // Write your code here.
        int aheadNotBuy, aheadBuy, curNotBuy, curBuy;
        aheadNotBuy = aheadBuy = 0;
        for(int index = n-1; index >=0 ; index--){
            curBuy = Math.max(-values[index] + aheadNotBuy,
                    0 + aheadBuy);
            curNotBuy = Math.max(values[index]- fee + aheadBuy,
                    0 + aheadNotBuy);
            aheadNotBuy = curNotBuy;
            aheadBuy = curBuy;
        }
        return aheadBuy;
    }
    public static void main(String args[]) {

        int prices[] = {1,3,2,8,4,9};
        int n = prices.length;
        int fee = 2;

        System.out.println("The maximum profit that can be generated is "+
                maximumProfit(prices, n,fee));
    }

}
