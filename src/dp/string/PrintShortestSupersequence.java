package dp.string;

public class PrintShortestSupersequence {

    public static String shortestSupersequence(String s1, String s2) {
        // Write your code here..
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

        String res="";

        int ind1 = m;
        int ind2 = n;

        while(ind1 > 0 && ind2 > 0){
            if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                res += s1.charAt(ind1-1);
                ind1--;
                ind2--;
            }else if(dp[ind1-1][ind2]  > dp[ind1][ind2-1]){
                res += s1.charAt(ind1-1);
                ind1--;
            }else{
                res += s2.charAt(ind2-1);
                ind2--;
            }
        }
        while(ind1 > 0){
            res += s1.charAt(ind1-1);
            ind1--;
        }
        while(ind2 > 0){
            res += s2.charAt(ind2-1);
            ind2--;
        }
        return new StringBuilder(res).reverse().toString();
    }
}
