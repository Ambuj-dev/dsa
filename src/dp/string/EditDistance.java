package dp.string;

public class EditDistance {

    //0 based indexing
    public static int editDistance1(String str1, String str2) {
        //Your code goes here
        int m = str1.length();
        int n = str2.length();
        return editDistance1(m-1, n-1, str1, str2);
    }

    public static int editDistance1(int i1, int i2, String str1, String str2) {
        if(i1 < 0) return i2+1;
        if(i2 < 0) return i1+1;

        if(str1.charAt(i1) == str2.charAt(i2)){
            return editDistance1(i1-1, i2-1, str1, str2);
        }else{
            return Math.min(1+editDistance1(i1, i2-1, str1, str2),
                    Math.min(1+editDistance1(i1-1, i2, str1, str2),
                            1+editDistance1(i1-1, i2-1, str1, str2)));
        }
    }
    // 1 based indexing with memoization
    public static int editDistance(String str1, String str2) {
        //Your code goes here
        int m = str1.length();
        int n = str2.length();
        Integer[][] dp = new Integer[m+1][n+1];
        return editDistance(m, n, str1, str2, dp);
    }

    public static int editDistance(int i1, int i2, String str1, String str2, Integer[][] dp) {
        if(i1 == 0) return i2;
        if(i2 == 0) return i1;
        if(dp[i1][i2] != null) return dp[i1][i2];
        if(str1.charAt(i1-1) == str2.charAt(i2-1)){
            return dp[i1][i2] = editDistance(i1-1, i2-1, str1, str2, dp);
        }else{
            return dp[i1][i2] = Math.min(1+editDistance(i1, i2-1, str1, str2, dp),
                    Math.min(1+editDistance(i1-1, i2, str1, str2, dp),
                            1+editDistance(i1-1, i2-1, str1, str2, dp)));
        }
    }

    public static int editDistanceTabulation(String str1, String str2) {
        //Your code goes here
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i <= m; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= n; j++){
            dp[0][j] = j;
        }

        for(int i1 = 1; i1 <= m;i1++){
            for(int i2 = 1; i2 <= n;i2++){
                if(str1.charAt(i1-1) == str2.charAt(i2-1)){
                    dp[i1][i2] = dp[i1-1][i2-1];
                }else{
                    dp[i1][i2] = Math.min(1+dp[i1][i2-1],
                            Math.min(1+dp[i1-1][i2],
                                    1+dp[i1-1][i2-1]));
                }
            }
        }
        return dp[m][n];
    }
    public static int editDistanceTabulationOptimised(String str1, String str2) {
        //Your code goes here
        int m = str1.length();
        int n = str2.length();
        int[] prev = new int[n+1];

        for(int j = 0; j <= n; j++){
            prev[j] = j;
        }

        for(int i1 = 1; i1 <= m;i1++){
            int[] cur = new int[n+1];
            cur[0] = i1;
            for(int i2 = 1; i2 <= n;i2++){
                if(str1.charAt(i1-1) == str2.charAt(i2-1)){
                    cur[i2] = prev[i2-1];
                }else{
                    cur[i2] = 1 + Math.min(cur[i2-1],
                            Math.min(prev[i2],
                                    prev[i2-1]));
                }
            }
            prev = cur;
        }
        return prev[n];
    }

    public static void main(String args[]) {

        String s1 = "horse";
        String s2 = "ros";

        System.out.println("The minimum number of operations required is: "+
                editDistance(s1,s2));
        System.out.println("The minimum number of operations required is: "+
                editDistance1(s1,s2));
        System.out.println("The minimum number of operations required is: "+
                editDistanceTabulation(s1,s2));
        System.out.println("The minimum number of operations required is: "+
                editDistanceTabulationOptimised(s1,s2));
    }

}

