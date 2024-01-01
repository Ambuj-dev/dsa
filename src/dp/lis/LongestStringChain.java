package dp.lis;

import java.util.Arrays;
import java.util.Comparator;

public class LongestStringChain {
    public static int longestStrChain(String[] nums) {
        // Write your code here.
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        Arrays.sort(nums, Comparator.comparingInt(String::length));
        int maxLength = 1;
        for (int i=0; i<n; i++) {
            for (int prev=0; prev<i; prev++) {
                if (compare(nums[i], nums[prev])) {
                    dp[i] = Math.max(dp[i], dp[prev] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    static boolean compare(String s1, String s2){
        if(s1.length() != 1 + s2.length()) return false;
        int first = 0;
        int second = 0;
        while(first < s1.length()){
            if(second < s2.length() && s1.charAt(first) == s2.charAt(second)){
                first++;
                second++;
            }else{
                first++;
            }
        }
        if(first == s1.length() && second == s2.length()) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(longestStrChain(new String[]{"m", "nm", "mmm"}));
    }
}
