package dp.string;

public class WildcardMatching {
    public static boolean wildcardMatching(String pattern, String text) {
        // Write your code here.
        int m = pattern.length();
        int n = text.length();
        return wildcardMatching(m-1, n-1, pattern, text);
    }

    public static boolean wildcardMatching(int i1, int i2, String pattern, String text) {

        if(i1 < 0 && i2 < 0) return true;
        if(i1 < 0 && i2 >= 0) return false;
        if(i2 < 0 && i1 >= 0){
            for(int ii = 0; ii <= i1; ii++){
                if(pattern.charAt(ii) != '*') return false;
            }
            return true;
        }


        if(pattern.charAt(i1) == text.charAt(i2) || pattern.charAt(i1) == '?'){
            return wildcardMatching(i1-1, i2-1, pattern, text);
        }
        else if(pattern.charAt(i1) == '*'){
            return wildcardMatching(i1, i2-1, pattern, text) || wildcardMatching(i1-1, i2, pattern, text);
        }

        return false;
    }

    public static boolean wildcardMatchingMemoized(String pattern, String text) {
        // Write your code here.
        int m = pattern.length();
        int n = text.length();
        Boolean[][] dp = new Boolean[m+1][n+1];
        return wildcardMatchingMemoized(m, n, pattern, text, dp);
    }

    public static boolean wildcardMatchingMemoized(int i1, int i2, String pattern, String text, Boolean[][] dp) {

        if(i1 == 0 && i2 == 0) return true;
        if(i1 == 0 && i2 > 0) return false;
        if(i2 == 0 && i1 > 0){
            for(int ii = 1; ii <= i1; ii++){
                if(pattern.charAt(ii-1) != '*') return false;
            }
            return true;
        }
        if(dp[i1][i2] != null) return dp[i1][i2];

        if(pattern.charAt(i1-1) == text.charAt(i2-1) || pattern.charAt(i1-1) == '?'){
            return dp[i1][i2] = wildcardMatchingMemoized(i1-1, i2-1, pattern, text, dp);
        }
        else if(pattern.charAt(i1-1) == '*'){
            return dp[i1][i2] = wildcardMatchingMemoized(i1, i2-1, pattern, text, dp) || wildcardMatchingMemoized(i1-1, i2, pattern, text, dp);
        }

        return dp[i1][i2] = false;
    }


    public static boolean wildcardMatchingTabulation(String pattern, String text) {
        // Write your code here.
        int m = pattern.length();
        int n = text.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        //Can be ignored as default value is false
        for(int j = 1; j <= n; j++){
            dp[0][j] = false;
        }

        for(int i = 1; i <= m; i++){
            boolean flag = true;
            for(int ii = 1; ii <= i; ii++){
                if(pattern.charAt(ii-1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[i][0] = flag;
        }

        for(int i1 = 1; i1 <= m; i1++){
            for(int i2= 1; i2 <= n; i2++){
                if(pattern.charAt(i1-1) == text.charAt(i2-1) || pattern.charAt(i1-1) == '?'){
                    dp[i1][i2] = dp[i1-1][i2-1];
                }
                else if(pattern.charAt(i1-1) == '*'){
                    dp[i1][i2] = dp[i1][i2-1] || dp[i1-1][i2];
                }else{
                    dp[i1][i2] = false;
                }
            }
        }

        return dp[m][n];
    }

    public static boolean wildcardMatchingTabulationOptimised(String pattern, String text) {
        // Write your code here.
        int m = pattern.length();
        int n = text.length();
        boolean[] prev = new boolean[n+1];

        prev[0] = true;


        for(int i1 = 1; i1 <= m; i1++){
            boolean[] cur = new boolean[n+1];
            boolean flag = true;
            for(int ii = 1; ii <= i1; ii++){
                if(pattern.charAt(ii-1) != '*') {
                    flag = false;
                    break;
                }
            }
            cur[0] = flag;
            for(int i2= 1; i2 <= n; i2++){
                if(pattern.charAt(i1-1) == text.charAt(i2-1) || pattern.charAt(i1-1) == '?'){
                    cur[i2] = prev[i2-1];
                }
                else if(pattern.charAt(i1-1) == '*'){
                    cur[i2] = cur[i2-1] || prev[i2];
                }else{
                    cur[i2] = false;
                }
            }
            prev = cur;
        }

        return prev[n];
    }

    public static void main(String args[]) {

        String S1 = "ab*cd";
        String S2 = "abdefcd";

        System.out.println(wildcardMatching(S1, S2));
        System.out.println(wildcardMatchingMemoized(S1, S2));
        System.out.println(wildcardMatchingTabulation(S1, S2));
        System.out.println(wildcardMatchingTabulationOptimised(S1, S2));
    }

}
