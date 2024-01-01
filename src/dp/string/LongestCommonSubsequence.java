package dp.string;

public class LongestCommonSubsequence {

    public static int lcsZeroIndex(String s, String t) {
        //Your code goes here
        Integer[][] dp = new Integer[s.length()][t.length()];
        return lcsZeroIndex(s.length()-1, t.length()-1 , s, t, dp);
    }

    public static int lcsZeroIndex(int ind1, int ind2, String s1, String s2, Integer[][] dp) {

        if(ind1 < 0 || ind2 < 0) return 0;

        if(dp[ind1][ind2] != null) return dp[ind1][ind2];

        if(s1.charAt(ind1) == s2.charAt(ind2)){
            return dp[ind1][ind2] = 1 + lcsZeroIndex(ind1-1, ind2-1, s1, s2, dp);
        }else{
            return dp[ind1][ind2] = 0 + Math.max(lcsZeroIndex(ind1, ind2-1, s1, s2, dp), lcsZeroIndex(ind1-1, ind2, s1, s2, dp));
        }

    }

    public static int lcs(String s, String t) {
        //Your code goes here
        Integer[][] dp = new Integer[s.length()+1][t.length()+1];
        return lcs(s.length(), t.length() , s, t, dp);
    }

    public static int lcs(int ind1, int ind2, String s1, String s2, Integer[][] dp) {

        if(ind1 == 0 || ind2 == 0) return 0;

        if(dp[ind1][ind2] != null) return dp[ind1][ind2];

        if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
            return dp[ind1][ind2] = 1 + lcs(ind1-1, ind2-1, s1, s2, dp);
        }else{
            return dp[ind1][ind2] = 0 + Math.max(lcs(ind1, ind2-1, s1, s2, dp), lcs(ind1-1, ind2, s1, s2, dp));
        }

    }

    public static int lcsTabulation(String s1, String s2) {
        //Your code goes here
        int m = s1.length();
        int n = s2.length();
        Integer[][] dp = new Integer[m+1][n+1];

        for(int i = 0; i<= m; i++){
            dp[i][0] = 0;
        }
        for(int i = 0; i<= n; i++){
            dp[0][i] = 0;
        }

        for(int ind1 = 1; ind1 <= m; ind1++){
            for(int ind2 = 1; ind2 <= n; ind2++){
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                }else{
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1][ind2-1], dp[ind1-1][ind2]);
                }
            }
        }
        return dp[m][n];
    }

    public static int lcsTabulationOptimised1(String s1, String s2) {
        //Your code goes here
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[2][n+1];

        for(int ind1 = 1; ind1 <= m; ind1++){
            for(int ind2 = 1; ind2 <= n; ind2++){
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1%2][ind2] = 1 + dp[(ind1-1)%2][ind2-1];
                }else{
                    dp[ind1%2][ind2] = 0 + Math.max(dp[ind1%2][ind2-1], dp[(ind1-1)%2][ind2]);
                }
            }
        }
        return dp[m%2][n];
    }

    public static int lcsTabulationOptimised(String s1, String s2) {
        //Your code goes here
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n+1];

        for(int ind1 = 1; ind1 <= m; ind1++){
            int[] cur = new int[n+1];
            for(int ind2 = 1; ind2 <= n; ind2++){
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    cur[ind2] = 1 + prev[ind2-1];
                }else{
                    cur[ind2] = 0 + Math.max(cur[ind2-1], prev[ind2]);
                }
            }
            prev = cur;
        }
        return prev[n];
    }


    public static void main(String args[]) {

        String s1= "acd";
        String s2= "ced";

        System.out.println("The Length of Longest Common Subsequence is "+lcsZeroIndex(s1,s2));
        System.out.println("The Length of Longest Common Subsequence is "+lcs(s1,s2));
        System.out.println("The Length of Longest Common Subsequence is "+lcsTabulation(s1,s2));
        System.out.println("The Length of Longest Common Subsequence is "+lcsTabulationOptimised(s1,s2));
        System.out.println("The Length of Longest Common Subsequence is "+lcsTabulationOptimised1(s1,s2));
    }

}
