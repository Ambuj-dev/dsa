package dp.twod;

public class CountSquares {

    public static void main(String[] args) {

        int arr[][] = {
                {0, 1, 1, 1}, {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        int n = 3, m = 4;
        int squares = countSquares(n, m, arr);
        System.out.println("The number of squares: " +squares);
    }

    private static int countSquares(int row, int col, int[][] arr) {
        int[][] dp = new int[row+1][col+1];
        for (int j = 0; j < col; j++) dp[0][j] = arr[0][j];
        for (int i = 0; i < row; i++) dp[i][0] = arr[i][0];

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (arr[i][j] == 0) dp[i][j] = 0;
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                            Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sum += dp[i][j];
            }
        }
        return sum;
    }
}
