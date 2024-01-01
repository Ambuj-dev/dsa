package dp.lis;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PrintLIS {

    public static List<Integer> printingLongestIncreasingSubsequence(int[] arr, int n) {

        int[] dp = new int[n];
        int[] hash = new int[n];

        Arrays.fill(dp, 1);
        int maxLength = 1;
        int lastIndex = 0;
        for (int i = 0; i < n; i++) {
            hash[i] = i;
            for (int prev = 0; prev < i; prev++) {
                if (arr[i] > arr[prev] && dp[prev] + 1 > dp[i]) {
                    dp[i] = dp[prev] + 1;
                    hash[i] = prev;
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }

        }
        LinkedList<Integer> lis = new LinkedList<>();
        lis.addFirst(arr[lastIndex]);
        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            lis.addFirst(arr[lastIndex]);
        }
        return lis;
    }

    public static void main(String[] args) {
        System.out.println(printingLongestIncreasingSubsequence(new int[]{5, 38, 36}, 3));
    }
}
