package dp.string;

public class LongestCommonSubstring {

    public static int lcsOptimised(String s1, String s2){
        // Write your code here.
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n+1];

        int ans  = 0;
        for(int ind1 = 1; ind1 <= m; ind1++){
            int[] cur = new int[n+1];
            for(int ind2 = 1; ind2 <= n; ind2++){
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    cur[ind2] = 1 + prev[ind2-1];
                    ans = Math.max(ans, cur[ind2]);
                }else{
                    cur[ind2] = 0;
                }
            }
            prev = cur;
        }
        return ans;
    }

    public static int lcsOpt(String s1, String s2){
        // Write your code here.
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[2][n+1];
        int ans  = 0;
        for(int ind1 = 1; ind1 <= m; ind1++){
            for(int ind2 = 1; ind2 <= n; ind2++){
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1%2][ind2] = 1 + dp[(ind1-1)%2][ind2-1];
                    ans = Math.max(ans, dp[ind1%2][ind2]);
                }else{
                    dp[ind1%2][ind2] = 0;
                }
            }
        }
        return ans;
    }

    public static int lcs(String s1, String s2){
        // Write your code here.
        int m = s1.length();
        int n = s2.length();
        Integer[][] dp = new Integer[m+1][n+1];

        for(int i = 0; i<= m; i++){
            dp[i][0] = 0;
        }
        for(int i = 0; i<= n; i++){
            dp[0][i] = 0;
        }
        int ans  = 0;
        for(int ind1 = 1; ind1 <= m; ind1++){
            for(int ind2 = 1; ind2 <= n; ind2++){
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                    ans = Math.max(ans, dp[ind1][ind2]);
                }else{
                    dp[ind1][ind2] = 0;
                }
            }
        }
        return ans;
    }

    public static void main(String args[]) {

        String s1= "abcjklp";
        String s2= "acjkp";

        System.out.println("The Length of Longest Common Substring is "+lcs(s1,s2));
        System.out.println("The Length of Longest Common Substring is "+lcsOptimised(s1,s2));
        System.out.println("The Length of Longest Common Substring is "+lcsOpt(s1,s2));
    }


}
