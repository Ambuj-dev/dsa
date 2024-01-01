package dp.string;

public class MinInsertionToMakePalindrome {
    public static int minInsertion(String str) {
        // Write your code here.
        return str.length() - longestPalindromeSubsequence(str);
    }

    public static int longestPalindromeSubsequence(String s) {
        // Write your code here.
        return lcs(s, new StringBuilder(s).reverse().toString());
    }

    public static int lcs(String s1, String s2) {
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

        String s= "abcaa";
        System.out.println("The Minimum insertions required to make string palindrome: "
                +minInsertion(s));
    }

}
