package dp.oned;

public class ClimbStairs {


    public static int countDistinctWayToClimbStair(long nStairs) {
        // Write your code here.
        if(nStairs == 0) return 1;
        if(nStairs ==1) return 1;
        return countDistinctWayToClimbStair(nStairs-1)+countDistinctWayToClimbStair(nStairs-2);
    }


    public static int countDistinctWayToClimbStairTab(long nStairs) {
        // Write your code here.
        if(nStairs == 0) return 1;
        int[] dp = new int[(int)nStairs+1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 2; i <= nStairs; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[(int)nStairs];
    }

    public static int countDistinctWayToClimbStairTab1(long nStairs) {
        // Write your code here.
        if(nStairs == 0) return 1;

        int prev1 = 1;
        int prev2 = 1;
        for(int i = 2; i <= nStairs; i++){
            int cur = prev2 + prev1;
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(countDistinctWayToClimbStair(8));
        System.out.println(countDistinctWayToClimbStairTab(8));
        System.out.println(countDistinctWayToClimbStairTab(33));
        System.out.println(countDistinctWayToClimbStairTab(20));
        System.out.println(countDistinctWayToClimbStairTab(20));
        System.out.println(countDistinctWayToClimbStairTab(16));
        System.out.println(countDistinctWayToClimbStairTab(11));
        System.out.println(countDistinctWayToClimbStairTab(38));
        System.out.println(countDistinctWayToClimbStairTab(40));
        System.out.println(countDistinctWayToClimbStairTab(0));
        System.out.println(countDistinctWayToClimbStairTab1(8));
        System.out.println(countDistinctWayToClimbStairTab1(33));
        System.out.println(countDistinctWayToClimbStairTab1(20));
        System.out.println(countDistinctWayToClimbStairTab1(20));
        System.out.println(countDistinctWayToClimbStairTab1(16));
        System.out.println(countDistinctWayToClimbStairTab1(11));
        System.out.println(countDistinctWayToClimbStairTab1(38));
        System.out.println(countDistinctWayToClimbStairTab1(40));
        System.out.println(countDistinctWayToClimbStairTab1(0));

    }
}
