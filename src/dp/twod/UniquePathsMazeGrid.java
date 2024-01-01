package dp.twod;

import java.util.ArrayList;

public class UniquePathsMazeGrid {
    static int mod = (int)1e9+7;

    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        // Write your code here.
        Integer[][] dp = new Integer[m][n];
        return uniquePathsRecursive(m-1, n-1, mat, dp);
    }

    public static int uniquePathsRecursive(int m, int n,  ArrayList<ArrayList<Integer>> mat, Integer[][] dp){
        if(m >= 0 && n >= 0 && mat.get(m).get(n) == -1) return 0;
        if(m == 0 && n == 0 ) return 1;
        if(m < 0 || n < 0) return 0;

        if(dp[m][n] != null) return dp[m][n];
        int up = uniquePathsRecursive(m-1, n, mat, dp);
        int left = uniquePathsRecursive(m, n-1, mat, dp);
        return dp[m][n] = (up+left) % mod;
    }
    static int mazeObstaclesTabulation(int n, int m,  ArrayList<ArrayList<Integer>> mat) {
        int dp[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Base conditions
                if (i >= 0 && j >= 0 && mat.get(i).get(j) == -1) {
                    dp[i][j] = 0; // If there's an obstacle, no paths can go through here.
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1; // There's one valid path to the start cell.
                    continue;
                }

                int up = 0;
                int left = 0;

                // Check if moving up is possible
                if (i > 0)
                    up = dp[i - 1][j];

                // Check if moving left is possible
                if (j > 0)
                    left = dp[i][j - 1];

                // Calculate the number of paths by adding paths from above and from the left
                dp[i][j] = up + left;
            }
        }

        // The final result is stored in the bottom-right cell of the DP matrix
        return dp[n - 1][m - 1];
    }



    static int mazeObstaclesTabulationOptimized(int m, int n, ArrayList<ArrayList<Integer>> mat) {
        // Write your code here.
        int prev[] = new int[n];
        for (int i = 0; i < m; i++) {
            int temp[] = new int[n];
            for (int j = 0; j < n; j++) {
                if (i >= 0 && j >= 0 && mat.get(i).get(j) == -1) {
                    temp[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    temp[j] = 1;
                    continue;
                }

                int up = 0;
                int left = 0;

                if (i > 0)
                    up = prev[j];
                if (j > 0)
                    left = temp[j - 1];

                temp[j] = (int) (up + left) % mod;
            }
            prev = temp;
        }

        return prev[n - 1];

    }

    public static void main(String[] args) {
        int m=2;
        int n=2;
        ArrayList<ArrayList<Integer>> input  = new ArrayList<>();
        ArrayList<Integer> temp1 = new ArrayList<>();
        ArrayList<Integer> temp2 = new ArrayList<>();
        temp1.add(0);
        temp1.add(-1);
        temp2.add(-1);
        temp2.add(0);
        input.add(temp1);
        input.add(temp2);
        System.out.println(mazeObstacles(m,n, input));
        System.out.println(mazeObstaclesTabulation(n,m, input));
        System.out.println(mazeObstaclesTabulationOptimized(m,n, input));
    }
}
