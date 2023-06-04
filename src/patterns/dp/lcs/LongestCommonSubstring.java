package patterns.dp.lcs;

public class LongestCommonSubstring {

    public int findLCSLength(String s1, String s2) {
        return findLCSLengthRecursive(s1, s2, 0, 0, 0);
    }

    private int findLCSLengthRecursive(String s1, String s2, int i1, int i2, int count) {
        if(i1 == s1.length() || i2 == s2.length())
            return count;

        if(s1.charAt(i1) == s2.charAt(i2))
            count = findLCSLengthRecursive(s1, s2, i1+1, i2+1, count+1);

        int c1 = findLCSLengthRecursive(s1, s2, i1, i2+1, 0);
        int c2 = findLCSLengthRecursive(s1, s2, i1+1, i2, 0);

        return Math.max(count, Math.max(c1, c2));
    }

    public int findLCSLengthMemoization(String s1, String s2) {
        int maxLength = Math.min(s1.length(), s2.length());
        Integer[][][] dp = new Integer[s1.length()][s2.length()][maxLength];
        return findLCSLengthRecursive(dp, s1, s2, 0, 0, 0);
    }

    private int findLCSLengthRecursive(Integer[][][] dp, String s1, String s2, int i1, int i2, int count) {
        if(i1 == s1.length() || i2 == s2.length())
            return count;

        if(dp[i1][i2][count] == null) {
            int c1 = count;
            if(s1.charAt(i1) == s2.charAt(i2))
                c1 = findLCSLengthRecursive(dp, s1, s2, i1+1, i2+1, count+1);
            int c2 = findLCSLengthRecursive(dp, s1, s2, i1, i2+1, 0);
            int c3 = findLCSLengthRecursive(dp, s1, s2, i1+1, i2, 0);
            dp[i1][i2][count] = Math.max(c1, Math.max(c2, c3));
        }

        return dp[i1][i2][count];
    }
    public int findLCSLengthTabulation(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        int maxLength = 0;
        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength;
    }

    static int findLCSLengthTabulationOptimized(String s1, String s2) {
        int[][] dp = new int[2][s2.length()+1];
        int maxLength = 0;
        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                dp[i%2][j] = 0;
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i%2][j] = 1 + dp[(i-1)%2][j-1];
                    maxLength = Math.max(maxLength, dp[i%2][j]);
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        System.out.println(lcs.findLCSLength("abdca", "cbda"));//2
        System.out.println(lcs.findLCSLength("passport", "ppsspt"));//3

        System.out.println(lcs.findLCSLengthMemoization("abdca", "cbda"));//2
        System.out.println(lcs.findLCSLengthMemoization("passport", "ppsspt"));//3

        System.out.println(lcs.findLCSLengthTabulation("abdca", "cbda"));//2
        System.out.println(lcs.findLCSLengthTabulation("passport", "ppsspt"));//3

        System.out.println(lcs.findLCSLengthTabulationOptimized("abdca", "cbda"));//2
        System.out.println(lcs.findLCSLengthTabulationOptimized("passport", "ppsspt"));//3

    }
}
