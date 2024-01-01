package dp.twod;

public class MinimumPathSumInGrid {
    public static int minSumPath(int[][] grid) {
        // Write your code here.
        int m = grid.length;
        int n = grid[0].length;
        Integer[][] dp = new Integer[m][n];
        return minSumPath(m-1, n-1, grid, dp);
    }

    public static int minSumPath(int i , int j, int[][] grid, Integer[][] dp){
        if(i == 0 && j == 0) return grid[i][j];
        if(i < 0 || j < 0) return (int)Math.pow(10, 9);
        if(dp[i][j] != null) return dp[i][j];
        int up = grid[i][j] + minSumPath(i-1, j, grid, dp);
        int left = grid[i][j] + minSumPath(i, j-1, grid, dp);
        return dp[i][j] = Math.min(up, left);
    }

    public static int minSumPathTabulation(int[][] grid) {
        // Write your code here.
        int m = grid.length;
        int n = grid[0].length;
        Integer[][] dp = new Integer[m][n];

        for(int i = 0; i< m; i++){
            for(int j = 0; j< n; j++){
                if(i == 0 && j== 0) dp[i][j] = grid[i][j];
                else{
                    int up = grid[i][j];
                    if(i > 0) up += dp[i-1][j];
                    else up += (int)Math.pow(10, 9);

                    int left = grid[i][j];
                    if(j > 0) left += dp[i][j-1];
                    else left += (int)Math.pow(10, 9);

                    dp[i][j] = Math.min(up, left);

                }
            }
        }

        return dp[m-1][n-1];
    }

    public static int minSumPathTabulationOptimized(int[][] grid) {
        // Write your code here.
        int m = grid.length;
        int n = grid[0].length;
        Integer[] prev = new Integer[n];

        for(int i = 0; i< m; i++){
            Integer[] cur = new Integer[n];
            for(int j = 0; j< n; j++){
                if(i == 0 && j== 0) cur[j] = grid[i][j];
                else{
                    int up = grid[i][j];
                    if(i > 0) up += prev[j];
                    else up += (int)Math.pow(10, 9);

                    int left = grid[i][j];
                    if(j > 0) left += cur[j-1];
                    else left += (int)Math.pow(10, 9);

                    cur[j] = Math.min(up, left);

                }
            }
            prev = cur;
        }

        return prev[n-1];
    }

    public static void main(String[] args) {
        int matrix[][] = {{5,9,6},{11,5,2}};

        System.out.println(minSumPath(matrix));
        System.out.println(minSumPathTabulation(matrix));
        System.out.println(minSumPathTabulationOptimized(matrix));
    }
}
