package dp.stocks;

import java.util.List;

public class StockBuySellOnlyOnce {
    public static int maximumProfit(List<Integer> prices){
        // Write your code here.
        int min = prices.get(0);
        int profit = 0;
        for(int i = 1; i< prices.size(); i++){
            int cost = prices.get(i) - min;
            profit = Math.max(profit, cost);
            min = Math.min(min, prices.get(i));
        }
        return profit;
    }
    public static void main(String args[]) {
        List list = List.of(7,1,5,3,6,4);

        System.out.println("The maximum profit by selling the stock is "+
                maximumProfit(list));
    }

}
