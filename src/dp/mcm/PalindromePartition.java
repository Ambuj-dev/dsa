package dp.mcm;

public class PalindromePartition {
    public static int palindromePartitioning(String str) {
        // Write your code here
        int n = str.length();
        Integer[] dp = new Integer[n];
        return palindromePartitioning(str, 0, n, dp) - 1;
    }

    public static int palindromePartitioning(String s, int i, int n, Integer[] dp){
        if(i == n) return 0;
        if(dp[i] != null) return dp[i];
        int min = Integer.MAX_VALUE;
        for(int ind = i; ind < n; ind++){
            if(isPalindrome(s, i, ind)){
                int cost = 1 + palindromePartitioning(s, ind+1, n, dp);
                min = Math.min(min, cost);
            }
        }
        return dp[i] = min;
    }

    public static boolean isPalindrome(String str, int i, int j){
        while(i < j) {
            if(str.charAt(i) != str.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public static int palindromePartitioningTabulation(String str) {
        // Write your code here
        int n = str.length();
        int[] dp = new int[n+1];
        for(int i = n-1; i >=0; i--){
            int min = Integer.MAX_VALUE;
            for(int ind = i; ind < n; ind++){
                if(isPalindrome(str, i, ind)){
                    int cost = 1 + dp[ind+1];
                    min = Math.min(min, cost);
                }
            }
            dp[i] = min;
        }
        return dp[0] - 1;
    }

    public static void main(String[] args) {
        String str = "BABABCBADCEDE";
        System.out.println(palindromePartitioning(str));
        System.out.println(palindromePartitioningTabulation(str));
    }
}
