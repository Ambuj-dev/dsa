package patterns.dp.matrixmultiplication;

public class MatrixMultiplication {

    static int helper(int[] arr, int i, int j) {

        // base condition
        if (i == j)
            return 0;

        int mini = Integer.MAX_VALUE;

        // partioning loop
        for (int k = i; k <= j - 1; k++) {

            int ans = helper(arr, i, k) + helper(arr, k + 1, j) + arr[i - 1] * arr[k] * arr[j];

            mini = Math.min(mini, ans);

        }

        return mini;
    }

    static int f(int[] arr, int i, int j,int[][] dp){

        // base condition
        if(i == j)
            return 0;

        if(dp[i][j]!=-1)
            return dp[i][j];

        int mini = Integer.MAX_VALUE;

        // partioning loop
        for(int k = i; k<= j-1; k++){

            int ans = f(arr,i,k,dp) + f(arr, k+1,j,dp) + arr[i-1]*arr[k]*arr[j];

            mini = Math.min(mini,ans);

        }

        return mini;
    }



    static int matrixMultiplication(int[] arr, int N) {

        int i = 1;
        int j = N - 1;

        return helper(arr, i, j);


    }

    public static void main(String args[]) {

        int arr[] = {10, 20, 30, 40, 50};

        int n = arr.length;

        System.out.println("The minimum number of operations are " +
                matrixMultiplication(arr, n));

    }

}
