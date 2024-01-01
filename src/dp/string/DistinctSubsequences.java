package dp.string;

public class DistinctSubsequences {
    static int prime = (int)(Math.pow(10,9)+7);
    public static int distinctSubsequences(String str, String sub) {
        // Write your code here.
        int m = str.length();
        int n = sub.length();
        Integer[][] dp = new Integer[m+1][n+1];
        return distinctSubsequences(m, n, str, sub, dp);
    }

    public static int distinctSubsequences(int i, int j, String str, String sub, Integer[][] dp) {
        if(j == 0) return 1;
        if(i == 0) return 0;
        if(dp[i][j] != null) return dp[i][j];
        if(str.charAt(i-1) == sub.charAt(j-1)){
            return dp[i][j] = (distinctSubsequences(i-1, j-1, str, sub, dp) + distinctSubsequences(i-1, j, str, sub, dp))%prime;
        }else{
            return dp[i][j] = distinctSubsequences(i-1, j, str, sub, dp);
        }
    }

    public static int distinctSubsequencesTabulation(String str, String sub) {
        // Write your code here.
        int m = str.length();
        int n = sub.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i<= m; i++){
            dp[i][0] = 1;
        }
        for(int j = 1; j<= n; j++){
            dp[0][j] = 0;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j<= n; j++){
                if(str.charAt(i-1) == sub.charAt(j-1)){
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j])%prime;
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }
    public static int distinctSubsequencesTabulationOptimised(String str, String sub) {
        // Write your code here.
        int m = str.length();
        int n = sub.length();
        int[] dp = new int[n+1];

        dp[0] = 1;

        for(int i = 1; i <= m; i++){
            for(int j = n; j>=1; j--){
                if(str.charAt(i-1) == sub.charAt(j-1)){
                    dp[j] = (dp[j-1] + dp[j])%prime;
                }else{
                    dp[j] = dp[j];
                }
            }
        }
        return dp[n];
    }


    public static void main(String args[]) {

        String s1 = "babgbag";
        String s2 = "bag";
        System.out.println("The Count of Distinct Subsequences is "+
                distinctSubsequences(s1,s2));
        System.out.println("The Count of Distinct Subsequences is "+
                distinctSubsequencesTabulation(s1,s2));
        System.out.println("The Count of Distinct Subsequences is "+
                distinctSubsequencesTabulationOptimised(s1,s2));
    }

}
