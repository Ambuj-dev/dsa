package dp.string;

public class PrintLCS {

    public static String printLCS(int m, int n, String s1, String s2){
        // Write your code here.

        int[][] dp = new int[m+1][n+1];

        for(int ind1 = 1; ind1 <= m; ind1++){
            for(int ind2 = 1; ind2 <= n; ind2++){
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                }else{
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1][ind2-1], dp[ind1-1][ind2]);
                }
            }
        }
        int index = dp[m][n] - 1;
        String str="";
        for(int k=0; k<=index;k++){
            str +="$"; // dummy string
        }
        StringBuilder res = new StringBuilder(str);

        int ind1 = m;
        int ind2 = n;

        while(ind1 > 0 && ind2 > 0){
            if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                res.setCharAt(index, s1.charAt(ind1-1));
                index--;
                ind1--;
                ind2--;
            }else if(dp[ind1-1][ind2]  > dp[ind1][ind2-1]){
                ind1--;
            }else{
                ind2--;
            }
        }
        return res.toString();
    }

    public static void main(String args[]) {

        String s1= "ababa";
        String s2= "cbbcad";

        System.out.println("The Longest Common Subsequence is "+printLCS(s1.length(), s2.length(), s1, s2));
        System.out.println("The Longest Common Subsequence is "+printLCS(5, 5, "bdgek", "abcde"));
    }

}
