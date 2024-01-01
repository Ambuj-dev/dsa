package dp.threed;

public class MaximumChocolate {

    public static int maximumChocolates(int r, int c, int[][] grid) {
        // Write your code here.
        Integer[][][] dp = new Integer[r][c][c];
        return maximumChocolates(0, 0, c - 1, r, c, grid, dp);
    }

    public static int maximumChocolates(int i, int j1, int j2, int row, int col, int[][] grid, Integer[][][] dp) {
        if (j1 < 0 || j1 >= col || j2 < 0 || j2 >= col) {
            return (int) Math.pow(-10, 9);
        }
        if (i == row - 1) {
            if (j1 == j2) {
                return grid[i][j1];
            } else {
                return grid[i][j1] + grid[i][j2];
            }
        }
        if (dp[i][j1][j2] != null) return dp[i][j1][j2];

        int max = Integer.MIN_VALUE;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int ans;
                if (j1 == j2)
                    ans = grid[i][j1] + maximumChocolates(i + 1, j1 + di, j2 + dj, row, col, grid, dp);
                else
                    ans = grid[i][j1] + grid[i][j2] + maximumChocolates(i + 1, j1 + di, j2 + dj, row, col, grid, dp);
                max = Math.max(ans, max);
            }
        }
        return dp[i][j1][j2] = max;
    }

    public static int maximumChocolatesTabulation(int r, int c, int[][] grid) {
        // Write your code here.
        Integer[][][] dp = new Integer[r][c][c];
        for(int j1 = 0; j1< c; j1++){
            for(int j2 = 0; j2 < c; j2++){
                if(j1 == j2) dp[r-1][j1][j2] = grid[r-1][j1];
                else dp[r-1][j1][j2] = grid[r-1][j1] + grid[r-1][j2];
            }
        }

        for(int i = r-2; i>=0; i--){
            for(int j1= 0; j1< c; j1++){
                for(int j2= 0; j2< c; j2++){
                    int max = Integer.MIN_VALUE;
                    for(int di = -1; di<= 1; di++){
                        for(int dj = -1; dj<= 1; dj++){
                            int ans;
                            if(j1 == j2)
                                ans = grid[i][j1];
                            else
                                ans = grid[i][j1]+ grid[i][j2];
                            if(j1+di < 0 || j1+di >= c || j2+dj < 0 || j2+dj >= c)
                                ans += (int) Math.pow(-10, 9);
                            else
                                ans += dp[i+1][j1+di][j2+dj];
                            max = Math.max(ans, max);
                        }

                    }
                    dp[i][j1][j2] = max;
                }

            }
        }
        return dp[0][0][c-1];
    }

    public static int maximumChocolatesTabulationOptimized(int r, int c, int[][] grid) {
        // Write your code here.
        Integer[][] front = new Integer[c][c];
        Integer[][] cur = new Integer[c][c];
        for(int j1 = 0; j1< c; j1++){
            for(int j2 = 0; j2 < c; j2++){
                if(j1 == j2) front[j1][j2] = grid[r-1][j1];
                else front[j1][j2] = grid[r-1][j1] + grid[r-1][j2];
            }
        }

        for(int i = r-2; i>=0; i--){
            for(int j1= 0; j1< c; j1++){
                for(int j2= 0; j2< c; j2++){
                    int max = Integer.MIN_VALUE;
                    for(int di = -1; di<= 1; di++){
                        for(int dj = -1; dj<= 1; dj++){
                            int ans;
                            if(j1 == j2)
                                ans = grid[i][j1];
                            else
                                ans = grid[i][j1]+ grid[i][j2];
                            if(j1+di < 0 || j1+di >= c || j2+dj < 0 || j2+dj >= c)
                                ans += (int) Math.pow(-10, 9);
                            else
                                ans += front[j1+di][j2+dj];
                            max = Math.max(ans, max);
                        }

                    }
                    cur[j1][j2] = max;
                }

            }

            for (int a = 0; a < c; a++) {
                front[a] = (Integer[])(cur[a].clone());
            }
        }

        return front[0][c - 1];
    }

    public static void main(String args[]) {

        int matrix[][] = {{2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}};
        int n = matrix.length;
        int m = matrix[0].length;

        System.out.println(maximumChocolates(n, m, matrix));
        System.out.println(maximumChocolatesTabulation(n, m, matrix));
        System.out.println(maximumChocolatesTabulationOptimized(n, m, matrix));
    }

}
