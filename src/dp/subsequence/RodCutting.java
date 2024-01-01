package dp.subsequence;

public class RodCutting {

    public static int cutRod(int price[], int n) {
        // Write your code here.
        int length = price.length;
        Integer[][] dp = new Integer[length][n+1];
        return cutRod(length-1, n, price, dp);
    }

    public static int cutRod(int index, int size, int price[], Integer[][] dp) {
        // Write your code here.
        if(index == 0){
            return size*price[index];
        }

        if(dp[index][size] != null) return dp[index][size];

        int notTaken = 0 + cutRod(index - 1, size, price, dp);

        int taken = Integer.MIN_VALUE;
        int rodLength = index + 1;
        if(rodLength <= size)
            taken = price[index] + cutRod(index, size-rodLength, price, dp);

        return dp[index][size] = Math.max(notTaken,taken);

    }

    public static int cutRodTabulation(int price[], int n) {
        // Write your code here.
        int length = price.length;
        Integer[][] dp = new Integer[length][n+1];
        for(int i =0; i<=n; i++){
            dp[0][i] = i * price[0];
        }
        for(int index = 1; index < length; index++){
            for(int size = 0; size <= n; size++){
                int notTaken = 0 + dp[index - 1][size];
                int taken = Integer.MIN_VALUE;
                int rodLength = index+1;
                if(rodLength <= size)
                    taken = price[index] + dp[index][size-rodLength];

                dp[index][size] = Math.max(notTaken,taken);
            }
        }
        return dp[length-1][n];
    }
    public static int cutRodTabulationOptimisation(int price[], int n) {
        // Write your code here.
        int length = price.length;
        Integer[] dp = new Integer[n+1];
        for(int i =0; i<=n; i++){
            dp[i] = i * price[0];
        }

        for(int index = 1; index < length; index++){
            for(int size = 0; size <= n; size++){
                int notTaken = 0 + dp[size];
                int taken = Integer.MIN_VALUE;
                int rodLength = index+1;
                if(rodLength <= size)
                    taken = price[index] + dp[size-rodLength];

                dp[size] = Math.max(notTaken,taken);
            }
        }
        return dp[n];
    }

    public static void main(String args[]) {

        int price[] = {2,5,7,8,10};

        int n = price.length;

        System.out.println("The Maximum price generated is "+cutRod(price,n));
        System.out.println("The Maximum price generated is "+cutRodTabulation(price,n));
        System.out.println("The Maximum price generated is "+cutRodTabulationOptimisation(price,n));
    }

}
