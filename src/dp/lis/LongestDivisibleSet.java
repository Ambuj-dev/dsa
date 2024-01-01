package dp.lis;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LongestDivisibleSet {

    public static List< Integer > divisibleSet(int []arr) {
        // Write your code here.
        int n = arr.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.sort(arr);

        Arrays.fill(dp, 1);
        int maxLength = 1;
        int lastIndex = 0;
        for (int i = 0; i < n; i++) {
            hash[i] = i;
            for (int prev = 0; prev < i; prev++) {
                if (arr[i] % arr[prev] == 0 && dp[prev] + 1 > dp[i]) {
                    dp[i] = dp[prev] + 1;
                    hash[i] = prev;
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }

        }
        LinkedList<Integer> lds = new LinkedList<>();
        lds.addFirst(arr[lastIndex]);
        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            lds.addFirst(arr[lastIndex]);

        }
        return lds;
    }

    public static void main(String[] args) {
        int[] arr = {1,16,7,8,4};

        System.out.println( divisibleSet(arr));
    }
}
