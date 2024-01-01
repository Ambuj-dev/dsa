package patterns.dp;

import java.util.Arrays;

public class OptimalSchedule {
    public static String optimalSchedule(int[] a, int[] b){

        StringBuilder res1 = new StringBuilder();
        StringBuilder res2 = new StringBuilder();
        Integer[][] dp = new Integer[2][a.length];
        int profit1 = optimalScheduleHelper(a, b, res1, "A", 0, dp);
        int profit2 = optimalScheduleHelper(a, b, res2, "B", 0, dp);
        System.out.println(Math.max(profit1, profit2));
        if(profit1 < profit2) return res2.toString();
        else return res1.toString();
    }

    public static int optimalScheduleHelper(int[] a, int[] b, StringBuilder res, String nextPoint, int index, Integer[][] dp){
        if(index == a.length) return 0;
       // if(dp[nextPoint][index] != null) return dp[nextPoint][index];
        int profit1 = 0;
        int profit2 = 0;
        if(nextPoint.equals("A")){
            profit1 = a[index] + optimalScheduleHelper(a, b, res.append("A"), nextPoint, index+1,dp);
            res.deleteCharAt(res.length()-1);
            profit2 = optimalScheduleHelper(a, b, res.append("T"), "B", index+1, dp);
        }else if(nextPoint.equals("B")){
            profit1 = b[index] + optimalScheduleHelper(a, b, res.append("B"), nextPoint, index+1, dp);
            res.deleteCharAt(res.length()-1);
            profit2 = optimalScheduleHelper(a, b, res.append("T"), "A", index+1, dp);
        }
        return Math.max(profit1, profit2);

    }


    public static int findMPWithDP(int []ar1, int []ar2)
    {
        int dp[][]=new int[ar1.length][ar2.length];
        dp[0][0]=ar1[0];
        dp[1][0]=ar2[0];
        int i=0;
        for(i=1;i<ar1.length;i++)
        {
            dp[0][i]=Math.max(ar1[i]+dp[0][i-1],dp[1][i-1]);
            dp[1][i]=Math.max(dp[0][i-1],ar2[i]+dp[1][i-1]);
        }
        if(dp[0][i-1] > dp[1][i-1]){
            System.out.println(findPath(ar1, ar2, 0, dp));
        }else{
            System.out.println(findPath(ar1, ar2, 1, dp));
        }

        return Math.max(dp[0][i-1],dp[1][i-1]);

    }
    public static String findPath(int[] ar1, int[] ar2, int start, int[][] dp){
        StringBuilder sb = new StringBuilder();
            for (int index = ar2.length - 1; index >= 0; index--) {
                if(start == 1) {
                    int temp = dp[1][index] - ar2[index];
                    if(index == 0 && temp == 0) {
                        sb.insert(0, "B");
                        break;
                    }
                    if (temp == dp[1][index - 1]) sb.insert(0, "B");
                    else {
                        sb.insert(0, "T");
                        start = 0;
                    }
                }else{
                    int temp = dp[0][index] - ar1[index];
                    if(index == 0 && temp == 0) {
                        sb.insert(0, "A");
                        break;
                    }
                    if (temp == dp[0][index - 1]) sb.insert(0, "A");
                    else {
                        sb.insert(0, "A");
                        start = 1;
                    }
            }
        }
            return sb.toString();
    }

    public static int findMaxProfit(int[] ar1, int[] ar2) {

        int n = ar1.length;
        int[][] dp = new int[2][n];
        for (int[] arr : dp) Arrays.fill(arr, -1);

        int profit1 = findMaxProfitHelper(n - 1, 0, ar1, ar2, dp);
        int profit2 = findMaxProfitHelper(n - 1, 1, ar1, ar2, dp);
        if (profit1 > profit2) System.out.println(findPath(ar1, ar2, 0, dp));
        else System.out.println(findPath(ar1, ar2, 1, dp));
        return Math.max(profit1, profit2);
    }

    public static int findMaxProfitHelper(int index, int nextPoint, int[] ar1, int[] ar2, int[][] dp) {

        if (index < 0) {
            return 0;
           // if(nextPoint == 0) return ar1[0];
            //if(nextPoint == 1) return ar2[0];
        }
        if (dp[nextPoint][index] != -1) return dp[nextPoint][index];
        if (nextPoint == 0) {
            return dp[nextPoint][index] = Math.max(ar1[index] + findMaxProfitHelper(index - 1, nextPoint, ar1, ar2, dp), findMaxProfitHelper(index - 1, 1, ar1, ar2, dp));
        } else {
            return dp[nextPoint][index] = Math.max(ar2[index] + findMaxProfitHelper(index - 1, nextPoint, ar1, ar2, dp), findMaxProfitHelper(index - 1, 0, ar1, ar2, dp));
        }

    }

    public static int findMaxProfitTabulation(int[] ar1, int[] ar2) {

        int n = ar1.length;
        int[][] dp = new int[2][n];
        dp[0][0] = ar1[0];
        dp[1][0] = ar2[0];

        for (int index = 1; index < n; index++) {
            dp[0][index] = Math.max(ar1[index] + dp[0][index - 1], dp[1][index - 1]);
            dp[1][index] = Math.max(ar2[index] + dp[1][index - 1], dp[0][index - 1]);
        }

        int profit1 = dp[0][n - 1];
        int profit2 = dp[1][n - 1];
        if (profit1 > profit2) System.out.println(findPath(ar1, ar2, 0, dp));
        else System.out.println(findPath(ar1, ar2, 1, dp));
        return Math.max(profit1, profit2);
    }

    public static void main(String[] args) {
        //System.out.println(optimalSchedule(new int[]{23,5,20,43}, new int[]{21,1, 40, 100}));
        //System.out.println(findMPWithDP(new int[]{23,5,20,43}, new int[]{21,1, 40, 100}));
        System.out.println(findMaxProfit(new int[]{23,5,20,43}, new int[]{21,1, 40, 100}));
        System.out.println(findMaxProfitTabulation(new int[]{23,5,20,43}, new int[]{21,1, 40, 100}));
        System.out.println(findMPWithDP(new int[]{23,5,20,43}, new int[]{21,1, 40, 20}));
        System.out.println(findMaxProfit(new int[]{23,5,20,43}, new int[]{21,1, 40, 20}));
        System.out.println(findMPWithDP(new int[]{2,5,20,43}, new int[]{21,1, 40, 20}));
        System.out.println(findMaxProfit(new int[]{2,5,20,43}, new int[]{21,1, 40, 20}));
        System.out.println(findMPWithDP(new int[]{2,5,50,43}, new int[]{21,1, 40, 20}));
        System.out.println(findMaxProfit(new int[]{2,5,50,43}, new int[]{21,1, 40, 20}));
    }
}
