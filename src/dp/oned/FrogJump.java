package dp.oned;

public class FrogJump {

    public static int frogJump(int n, int heights[]) {

        // Write your code here..
        Integer[] dp = new Integer[n];
        return jump(n-1, heights, dp);
    }

    public static int jump(int index, int[] heights, Integer[] dp){
        if(index == 0) return 0;
        if(dp[index] != null) return dp[index];
        int jumpOne = jump(index-1, heights, dp)+ Math.abs(heights[index] - heights[index - 1]);
        int jumpTwo = Integer.MAX_VALUE;
        if(index > 1)
            jumpTwo = jump(index-2, heights, dp) + Math.abs(heights[index] - heights[index - 2]);

        return dp[index] = Math.min(jumpOne, jumpTwo);

    }

    public static int frogJumpTabulation(int n, int heights[]) {

        // Write your code here..
        Integer[] dp = new Integer[n];
        dp[0] =  0;
        for(int index = 1; index < n; index++){
            int jumpOne = dp[index-1]+ Math.abs(heights[index] - heights[index - 1]);
            int jumpTwo = Integer.MAX_VALUE;
            if(index > 1)
                jumpTwo = dp[index-2] + Math.abs(heights[index] - heights[index - 2]);

            dp[index] = Math.min(jumpOne, jumpTwo);
        }
        return dp[n-1];
    }

    public static int frogJumpTabulationOptimized(int n, int heights[]) {

        // Write your code here..
        int prev = 0;
        int prev1 = 0;
        for(int index = 1; index < n; index++){
            int jumpOne = prev+ Math.abs(heights[index] - heights[index - 1]);
            int jumpTwo = Integer.MAX_VALUE;
            if(index > 1)
                jumpTwo = prev1 + Math.abs(heights[index] - heights[index - 2]);

            int cur = Math.min(jumpOne, jumpTwo);
            prev1 = prev;
            prev = cur;
        }
        return prev;
    }

    public static int frogJumpWithKDistance(int n, int heights[], int k) {

        // Write your code here..
        Integer[] dp = new Integer[n];
        dp[0] =  0;
        for(int index = 1; index < n; index++){
            int min = Integer.MAX_VALUE;
            for(int i = 1; i <= k; i++) {
                if(index - i >= 0) {
                    int jump = dp[index - i] + Math.abs(heights[index] - heights[index - i]);
                    min = Math.min(min, jump);
                }
            }
            dp[index] = min;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        System.out.println(frogJump(9, new int[]{7, 5, 5, 8, 4, 9, 1, 1, 10 }));//15
        System.out.println(frogJumpTabulation(9, new int[]{7, 5, 5, 8, 4, 9, 1, 1, 10 }));//15
        System.out.println(frogJumpTabulationOptimized(9, new int[]{7, 5, 5, 8, 4, 9, 1, 1, 10 }));
        System.out.println(frogJumpWithKDistance(5, new int[]{10, 40, 50, 20, 60}, 3));//50
    }
}
