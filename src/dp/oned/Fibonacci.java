package dp.oned;

import java.util.Arrays;

public class Fibonacci {

    static int fibonacci(int n, int[] dp){
        if(n<=1) return n;

        if(dp[n]!= -1) return dp[n];
        return dp[n]= fibonacci(n-1,dp) + fibonacci(n-2,dp);
    }

    static int fibonacciTabulation(int n, int[] dp){
        dp[0]= 0;
        dp[1]= 1;

        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1]+ dp[i-2];
        }
        return dp[n];
    }

    static int fibonacciTabulationOpt(int n, int[] dp){
        int prev2 = 0;
        int prev = 1;
        for(int i=2; i<=n; i++){
            int cur = prev2 + prev;
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }


    public static void main(String args[]) {

        int n=7;
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(fibonacci(n,dp));

        Arrays.fill(dp,-1);
        System.out.println(fibonacciTabulation(n,dp));

        Arrays.fill(dp,-1);
        System.out.println(fibonacciTabulationOpt(n,dp));

    }

}
