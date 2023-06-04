package patterns.dp.fibonacci;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(calculateFibonacciRecursive(5));
        System.out.println(calculateFibonacciIterative(5));
        System.out.println(calculateFibonacciMemoization(5));
        System.out.println(calculateFibonacciDP(5));
    }

    private static int calculateFibonacciRecursive(int n){
        if(n <= 1) return n;
        return calculateFibonacciRecursive(n-2) + calculateFibonacciRecursive(n-1);
    }

    private static int calculateFibonacciIterative(int n){
        if(n < 2) return n;
        int num1 = 0; int num2 = 1;int num3 = 0;
        for(int i = 2; i<= n; i++){
            num3 = num1+num2;
            num1 = num2;
            num2 = num3;
        }
        return num2;
    }


    private static int calculateFibonacciMemoization(int n){
        int[] memoize = new int[n+1];
        return fib(n, memoize);
    }
    private static int fib(int n, int[] memoize){
        if(n <= 1) return n;
        if(memoize[n] != 0) return memoize[n];
        memoize[n] = fib(n-1, memoize) + fib(n-2, memoize);
        return memoize[n];

    }
    private static int calculateFibonacciDP(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i<= n; i++){
           dp[i] = dp[i-1] +dp[i-2];
        }
        return dp[n];
    }

}
