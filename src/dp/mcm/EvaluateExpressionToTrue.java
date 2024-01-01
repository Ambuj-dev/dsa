package dp.mcm;

public class EvaluateExpressionToTrue {
    static int mod = 1000000007;
    public static int evaluateExp(String exp) {
        // Write your code here.
        int n = exp.length();
        long[][][] dp = new long[n][n][2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
        return evaluateExp(0, n - 1, 1, exp, dp);
    }

    public static int evaluateExp(int i, int j, int isTrue, String exp, long[][][] dp) {
        if(i > j) return 0;
        if(i == j){
            if(isTrue == 1) return exp.charAt(i) == 'T'? 1 : 0;
            else return exp.charAt(i) == 'F'? 1 : 0;
        }
        if(dp[i][j][isTrue] != -1) return (int) dp[i][j][isTrue];
        long ways = 0;
        for(int ind = i+1; ind < j; ind += 2){

            if(dp[i][ind-1][0] == -1) dp[i][ind-1][0] = evaluateExp(i, ind-1, 0, exp, dp);
            if(dp[i][ind-1][1] == -1) dp[i][ind-1][1] = evaluateExp(i, ind-1, 1, exp, dp);
            if(dp[ind+1][j][0] == -1) dp[ind+1][j][0] = evaluateExp(ind+1, j, 0, exp, dp);
            if(dp[ind+1][j][1] == -1) dp[ind+1][j][1] = evaluateExp(ind+1, j, 1, exp, dp);

            long lF = dp[i][ind-1][0];
            long lT = dp[i][ind-1][1];
            long rF = dp[ind+1][j][0];
            long rT = dp[ind+1][j][1];

            if('|' == exp.charAt(ind)){
                if(isTrue == 1) ways = (ways + (lF*rT) % mod + (lT*rF) % mod + (lT*rT) % mod) % mod;
                else ways = (ways +  (lF*rF) % mod) % mod;
            }
            else if('&' == exp.charAt(ind)){
                if(isTrue == 1) ways = (ways +  (lT*rT) % mod) % mod;
                else ways = (ways +  (lF*rT) % mod + (lT*rF) % mod + (lF*rF) % mod) % mod;
            }
            else if('^' == exp.charAt(ind)){
                if(isTrue == 1) ways = (ways +  (lF*rT) % mod + (lT*rF) % mod) % mod;
                else ways = (ways +  (lT*rT) % mod + (lF*rF) % mod) % mod;
            }
        }
        dp[i][j][isTrue] = ways;
        return (int)ways;
    }

    public static int evaluateExpTabulation(String exp) {
        // Write your code here.
        int n = exp.length();
        long[][][] dp = new long[n][n][2];
        for(int i = n-1; i>= 0; i--){
            for(int j = 0; j< n; j++){
                if(i > j) continue;
                for (int isTrue = 0; isTrue <= 1; isTrue++) {
                    if(i == j){
                        if(isTrue == 1) dp[i][j][isTrue] = exp.charAt(i) == 'T'? 1 : 0;
                        else dp[i][j][isTrue] = exp.charAt(i) == 'F'? 1 : 0;
                        continue;
                    }
                    long ways = 0;
                    for(int ind = i+1; ind < j; ind += 2){
                        long lF = dp[i][ind-1][0];
                        long lT = dp[i][ind-1][1];
                        long rF = dp[ind+1][j][0];
                        long rT = dp[ind+1][j][1];

                        if('|' == exp.charAt(ind)){
                            if(isTrue == 1) ways = (ways + (lF*rT) % mod + (lT*rF) % mod + (lT*rT) % mod) % mod;
                            else ways = (ways +  (lF*rF) % mod) % mod;
                        }
                        else if('&' == exp.charAt(ind)){
                            if(isTrue == 1) ways = (ways +  (lT*rT) % mod) % mod;
                            else ways = (ways +  (lF*rT) % mod + (lT*rF) % mod + (lF*rF) % mod) % mod;
                        }
                        else if('^' == exp.charAt(ind)){
                            if(isTrue == 1) ways = (ways +  (lF*rT) % mod + (lT*rF) % mod) % mod;
                            else ways = (ways +  (lT*rT) % mod + (lF*rF) % mod) % mod;
                        }
                    }
                    dp[i][j][isTrue] = ways;
                }
            }
        }
        return (int)dp[0][n - 1][1];
    }

    public static void main(String[] args) {
        System.out.println(evaluateExp("F|T^F"));
        System.out.println(evaluateExp("F^F^F^F&T|T|F|T|F|F|T|T|T|T&T|T|T&T|F&T|F|T|T|T^T|F^T|T&F^T|F|T|F|T&T|T^F|F^T&T^T&T^T&T^T&F&T^F|F^T|T|F|F^F|F&F|F|T&F&F"));

        System.out.println(evaluateExpTabulation("F|T^F"));
        System.out.println(evaluateExpTabulation("F^F^F^F&T|T|F|T|F|F|T|T|T|T&T|T|T&T|F&T|F|T|T|T^T|F^T|T&F^T|F|T|F|T&T|T^F|F^T&T^T&T^T&T^T&F&T^F|F^T|T|F|F^F|F&F|F|T&F&F"));
    }
}
