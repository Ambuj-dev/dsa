package dp.twod;

public class MaxFallingPathSum {

    public static int getMaxPathSum(int[][] matrix) {
        // Write your code here
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        Integer[][] dp = new Integer[row][col];
        for (int i = 0; i < col; i++) {
            max = Math.max(max, getMaxPathSum(row - 1, i, col, matrix, dp));
        }
        return max;
    }

    public static int getMaxPathSum(int i, int j, int m, int[][] matrix, Integer[][] dp) {
        if (j < 0 || j >= m) return (int) Math.pow(-10, 9);

        if (i == 0) return matrix[0][j];
        if (dp[i][j] != null) return dp[i][j];
        int up = matrix[i][j] + getMaxPathSum(i - 1, j, m, matrix, dp);
        int ld = matrix[i][j] + getMaxPathSum(i - 1, j - 1, m, matrix, dp);
        int rd = matrix[i][j] + getMaxPathSum(i - 1, j + 1, m, matrix, dp);
        return dp[i][j] = Math.max(up, Math.max(ld, rd));
    }

    public static int getMaxPathSumTabulation(int[][] matrix) {
        // Write your code here
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        Integer[][] dp = new Integer[row][col];
        for(int j = 0; j< col; j++){
            dp[0][j] = matrix[0][j];
        }
        for(int i =1;i< row; i++){
            for(int j = 0; j< col; j++){
                int up = matrix[i][j]+ dp[i-1][j];
                int ld = matrix[i][j];
                if(j-1>=0) ld += dp[i-1][j-1];
                else ld += (int)Math.pow(-10, 9);
                int rd = matrix[i][j];
                if(j+1<col) rd+= dp[i-1][j+1];
                else rd+= (int)Math.pow(-10, 9);
                dp[i][j] = Math.max(up, Math.max(ld, rd));
            }
        }
        for(int i = 0; i < col; i++){
            max = Math.max(max, dp[row-1][i]);
        }
        return max;
    }
    public static int getMaxPathSumTabulationOptimized(int[][] matrix) {
        // Write your code here
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        Integer[] prev = new Integer[col];

        for(int j = 0; j< col; j++){
            prev[j] = matrix[0][j];
        }
        for(int i =1;i< row; i++){
            Integer[] cur = new Integer[col];
            for(int j = 0; j< col; j++){
                int up = matrix[i][j]+ prev[j];
                int ld = matrix[i][j];
                if(j-1>=0) ld += prev[j-1];
                else ld += (int)Math.pow(-10, 9);
                int rd = matrix[i][j];
                if(j+1<col) rd+= prev[j+1];
                else rd+= (int)Math.pow(-10, 9);
                cur[j] = Math.max(up, Math.max(ld, rd));
            }
            prev = cur;
        }

        for(int i = 0; i < col; i++){
            max = Math.max(max, prev[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int matrix[][] = {{1,2,10,4},
                {100,3,2,1},
                {1,1,20,2},
                {1,2,2,1}};

        System.out.println(getMaxPathSum(matrix));
        System.out.println(getMaxPathSumTabulation(matrix));
        System.out.println(getMaxPathSumTabulationOptimized(matrix));

    }
}
