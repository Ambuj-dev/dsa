package dp.mcm;

public class BurstBalloons {
    public static int burstBalloons(int []arr){
        // Write your code here.
        int n = arr.length;
        Integer[][] dp = new Integer[n+1][n+2];
        int[] newArray = addAtBeginAndEnd(arr, 1, 1);
        return burstBalloonsHelper(newArray, 1, n, dp);
    }
// Think in reverse the problem is solved from end to first.
    public static int burstBalloonsHelper(int []arr, int i , int j, Integer[][] dp){
        if(i > j) return 0;
        if(dp[i][j] != null) return dp[i][j];
        int maxCoins = Integer.MIN_VALUE;
        for(int ind = i; ind <= j; ind++){
            int coins = arr[i-1] * arr[ind] * arr[j+1] +
                    burstBalloonsHelper(arr, i, ind-1, dp) + burstBalloonsHelper(arr, ind+1, j, dp);
            maxCoins = Math.max(coins, maxCoins);
        }
        return dp[i][j] = maxCoins;
    }

    public static int[] addAtBeginAndEnd(int[] elements, int firstElement, int lastElement){
        int[] newArray = new int[elements.length+2];
        newArray[0] = firstElement;
        System.arraycopy(elements, 0, newArray, 1, elements.length);
        newArray[elements.length+1] = lastElement;
        return newArray;
    }

    public static int burstBalloonsTabulation(int []arr){
        // Write your code here.
        int n = arr.length;
        int[][] dp = new int[n+2][n+2];
        int[] newArray = addAtBeginAndEnd(arr, 1, 1);
        for(int i = n; i>= 1; i--){
            for(int j = 1; j <= n; j++){
                if(i > j) continue;
                int maxCoins = Integer.MIN_VALUE;
                for(int ind = i; ind <= j; ind++){
                    int coins = newArray[i-1] * newArray[ind] * newArray[j+1] +
                            dp[i][ind-1] + dp[ind+1][j];
                    maxCoins = Math.max(coins, maxCoins);
                }
                dp[i][j] = maxCoins;
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 5, 8};
        System.out.println(burstBalloons(a));
        System.out.println(burstBalloonsTabulation(a));
    }
}
