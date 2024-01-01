package dp.twod;

public class MinimumPathSumInTriangularGrid {
    public static int minimumPathSum(int[][] triangle, int n) {
        // Write your code here.
        Integer[][] dp = new Integer[n][n];
        return minimumPathSum(0, 0, n, triangle, dp);
    }

    public static int minimumPathSum(int i, int j, int n, int[][] triangle, Integer[][] dp){
        if(i == n-1) return triangle[n-1][j];

        if(dp[i][j] != null) return dp[i][j];
        int down = triangle[i][j] + minimumPathSum(i+1, j, n, triangle, dp);
        int diagonal = triangle[i][j] + minimumPathSum(i+1, j+1, n, triangle, dp);
        return dp[i][j] = Math.min(down, diagonal);
    }


    public static int minimumPathSumTabulation(int[][] triangle, int n) {
        // Write your code here.
        Integer[][] dp = new Integer[n][n];

        for(int j = 0; j<n; j++){
            dp[n-1][j] = triangle[n-1][j];
        }

        for(int i = n-2; i >=0; i--){
            for(int j = i; j>=0; j--){
                int down = triangle[i][j] + dp[i+1][j];
                int diagonal = triangle[i][j] +dp[i+1][j+1];
                dp[i][j] = Math.min(down, diagonal);
            }
        }
        return dp[0][0];
    }

    public static int minimumPathSumTabulationOptimized(int[][] triangle, int n) {
        // Write your code here.
        Integer[] front = new Integer[n];


        for(int j = 0; j<n; j++){
            front[j] = triangle[n-1][j];
        }

        for(int i = n-2; i >=0; i--){
            Integer[] cur = new Integer[n];
            for(int j = i; j>=0; j--){
                int down = triangle[i][j] + front[j];
                int diagonal = triangle[i][j] + front[j+1];
                cur[j] = Math.min(down, diagonal);
            }
            front = cur;
        }
        return front[0];
    }

    public static void main(String[] args) {
        int triangle [][] = {{1},
                {2,3},
                {3,6,7},
                {8,9,6,10}};

        int n = triangle.length;

        System.out.println(minimumPathSum(triangle,n));
        System.out.println(minimumPathSumTabulation(triangle,n));
        System.out.println(minimumPathSumTabulationOptimized(triangle,n));

    }
}
