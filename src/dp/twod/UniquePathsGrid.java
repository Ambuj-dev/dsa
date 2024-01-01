package dp.twod;

public class UniquePathsGrid {

    public static int uniquePaths(int m, int n) {
        // Write your code here.
        Integer[][] dp = new Integer[m][n];
        return uniquePathsRecursive(m-1, n-1, dp);
    }

    public static int uniquePathsRecursive(int m, int n, Integer[][] dp){
        if(m == 0 && n == 0 ) return 1;
        if(m < 0 || n < 0) return 0;
        if(dp[m][n] != null) return dp[m][n];
        int up = uniquePathsRecursive(m-1, n, dp);
        int left = uniquePathsRecursive(m, n-1, dp);
        return dp[m][n] = up+left;
    }

    public static int uniquePathsTabulation(int m, int n) {
        // Write your code here.
        Integer[][] dp = new Integer[m][n];

        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                if(row == 0 && col == 0 ) dp[row][col] = 1;
                else{
                    int up = 0;
                    int left = 0;
                    if(row > 0) up = dp[row-1][col];
                    if(col > 0) left = dp[row][col-1];
                    dp[row][col] = up + left;
                }
            }
        }
        return dp[m-1][n-1];
    }
    public static int uniquePathsTabulationOptimized(int m, int n) {
        // Write your code here.
        Integer[] prev = new Integer[n];

        for(int row = 0; row < m; row++){
            Integer[] cur = new Integer[n];
            for(int col = 0; col < n; col++){
                if(row == 0 && col == 0 ) cur[col] = 1;
                else{
                    int up = 0;
                    int left = 0;
                    if(row > 0) up = prev[col];
                    if(col > 0) left = cur[col-1];
                    cur[col] = up + left;
                }
            }
            prev = cur;
        }
        return prev[n-1];
    }

    public static void main(String[] args) {
        int m=3;
        int n=3;
        System.out.println(uniquePaths(m,n));
        System.out.println(uniquePathsTabulation(m,n));
        System.out.println(uniquePathsTabulationOptimized(m,n));
    }
}
