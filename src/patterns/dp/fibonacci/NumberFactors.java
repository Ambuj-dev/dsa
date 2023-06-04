package patterns.dp.fibonacci;

public class NumberFactors {
    public int CountWays(int n) {
        if( n == 0)
            return 1; // base case, we don't need to subtract anything, so there is only one way

        if(n == 1)
            return 1; // we can subtract 1 to be left with zero, and that is the only way

        if(n == 2)
            return 1; // we can subtract 1 twice to get zero and that is the only way

        if(n == 3)
            return 2; // '3' can be expressed as {1,1,1}, {3}

        // if we subtract 1, we are left with 'n-1'
        int subtract1 = CountWays(n-1);
        // if we subtract 3, we are left with 'n-3'
        int subtract3 = CountWays(n-3);
        // if we subtract 4, we are left with 'n-4'
        int subtract4 = CountWays(n-4);

        return subtract1 + subtract3 + subtract4;
    }

    public int CountWaysMemoization(int n) {
        int dp[] = new int[n + 1];
        return CountWaysRecursive(dp, n);
    }

    public int CountWaysRecursive(int[] dp, int n) {
        if (n == 0)
            return 1; // base case, we don't need to subtract anything, so there is only one way

        if (n == 1)
            return 1; // we can take subtract 1 to be left with zero, and that is the only way

        if (n == 2)
            return 1; // we can subtract 1 twice to get zero and that is the only way

        if (n == 3)
            return 2; // '3' can be expressed as {1,1,1}, {3}

        if (dp[n] == 0) {
            // if we subtract 1, we are left with 'n-1'
            int subtract1 = CountWaysRecursive(dp, n - 1);
            // if we subtract 3, we are left with 'n-3'
            int subtract3 = CountWaysRecursive(dp, n - 3);
            // if we subtract 4, we are left with 'n-4'
            int subtract4 = CountWaysRecursive(dp, n - 4);
            dp[n] = subtract1 + subtract3 + subtract4;
        }

        return dp[n];
    }
    public int CountWaysTabulation(int n) {
        // bottom up
        int n0 = 1, n1 = 1, n2 = 1, n3 = 2;
        for(int i = 4; i <= n; i++){
            int temp = n0 + n1 + n3;
            n0 = n1;
            n1 = n2;
            n2 = n3;
            n3 = temp;
        }
        return n3;
    }


    public static void main(String[] args) {
        NumberFactors en = new NumberFactors();
        System.out.println(en.CountWays(4));
        System.out.println(en.CountWays(5));
        System.out.println(en.CountWays(6));

        System.out.println(en.CountWaysMemoization(4));
        System.out.println(en.CountWaysMemoization(5));
        System.out.println(en.CountWaysMemoization(6));

        System.out.println(en.CountWaysTabulation(4));
        System.out.println(en.CountWaysTabulation(5));
        System.out.println(en.CountWaysTabulation(6));
    }
}
