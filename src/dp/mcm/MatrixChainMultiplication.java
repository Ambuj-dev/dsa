package dp.mcm;

public class MatrixChainMultiplication {

    public static int mcm(int[] p){
        int n = p.length;
        Integer[][] dp =new Integer[n][n];
        //i is 1 because in matrix A*B*C*D the array will have 1 more element, so we should from 1.
        return mcm(p, 1, n-1, dp);
    }

    public static int mcm(int[] arr, int i, int j, Integer[][] dp){

        if(i == j) return 0;
        if(dp[i][j] != null) return dp[i][j];
        int minCost = 	Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
            int cost = arr[i-1] * arr[k] * arr[j]+ mcm(arr, i, k, dp)+ mcm(arr, k+1, j, dp);
            minCost = Math.min(cost, minCost);
        }
        return dp[i][j] = minCost;
    }

    public static int mcmTabulation(int[] p){
        int n = p.length;
        int[][] dp =new int[n][n];
        for(int i = n-1; i>= 1; i--){
            for(int j = i+1; j < n; j++){
                int minCost = 	Integer.MAX_VALUE;
                for(int k = i; k < j; k++){
                    int cost = p[i-1] * p[k] * p[j]+ dp[i][k]+ dp[k+1][j];
                    minCost = Math.min(cost, minCost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][n-1];
    }
    public static void main(String args[]) {

        int arr[] = {10, 20, 30, 40, 50};

        int n = arr.length;

        System.out.println("The minimum number of operations are "+
                mcm(arr));
        System.out.println("The minimum number of operations are "+
                mcmTabulation(arr));

    }


}
