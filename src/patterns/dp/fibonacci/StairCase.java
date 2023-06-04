package patterns.dp.fibonacci;

public class StairCase {
    public int CountWays(int n) {
        if( n == 0)
            return 1; // base case, we don't need to take any step, so there is only one way

        if(n == 1)
            return 1; // we can take one step to reach the end, and that is the only way

        if(n == 2)
            return 2; // we can take one step twice or jump two steps to reach at the top

        // if we take 1 step, we are left with 'n-1' steps;
        int take1Step = CountWays(n-1);
        // similarly, if we took 2 steps, we are left with 'n-2' steps;
        int take2Step = CountWays(n-2);
        // if we took 3 steps, we are left with 'n-3' steps;
        int take3Step = CountWays(n-3);

        return take1Step + take2Step + take3Step;
    }

    public int CountWaysMemoization(int n) {
        int dp[] = new int[n+1];
        return CountWaysRecursive(dp, n);
    }

    public int CountWaysRecursive(int[] dp, int n) {
        if( n == 0)
            return 1; // base case, we don't need to take any step, so there is only one way

        if(n == 1)
            return 1; // we can take one step to reach the end, and that is the only way

        if(n == 2)
            return 2; // we can take one step twice or jump two steps to reach at the top

        if(dp[n] == 0) {
            // if we take 1 step, we are left with 'n-1' steps;
            int take1Step = CountWaysRecursive(dp, n-1);
            // similarly, if we took 2 steps, we are left with 'n-2' steps;
            int take2Step = CountWaysRecursive(dp, n-2);
            // if we took 3 steps, we are left with 'n-3' steps;
            int take3Step = CountWaysRecursive(dp, n-3);
            dp[n] = take1Step + take2Step + take3Step;
        }

        return dp[n];
    }
    public int CountWaysTabulation(int n) {
        if (n < 2) return 1;
        if (n == 2) return 2;

        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=n; i++)
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];

        return dp[n];
    }

    public int CountWaysTabulationOptimize(int n) {
        if (n < 2) return 1;
        if (n == 2) return 2;

        int n1=1, n2=1, n3=2, temp;
        for(int i=3; i<=n; i++) {
            temp = n1 + n2 + n3;
            n1 = n2;
            n2 = n3;
            n3 = temp;
        }
        return n3;
    }


    public static void main(String[] args) {
        StairCase sc = new StairCase();
        System.out.println(sc.CountWays(3));
        System.out.println(sc.CountWays(4));
        System.out.println(sc.CountWays(5));

        System.out.println(sc.CountWaysMemoization(3));
        System.out.println(sc.CountWaysMemoization(4));
        System.out.println(sc.CountWaysMemoization(5));

        System.out.println(sc.CountWaysTabulation(3));
        System.out.println(sc.CountWaysTabulation(4));
        System.out.println(sc.CountWaysTabulation(5));

        System.out.println(sc.CountWaysTabulationOptimize(3));
        System.out.println(sc.CountWaysTabulationOptimize(4));
        System.out.println(sc.CountWaysTabulationOptimize(5));
    }
}
