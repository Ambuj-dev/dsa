package dp.mcm;

import java.util.Arrays;

public class CostToCutChocolate {
    public static int cost(int n, int c, int cuts[]) {

        int[] cuts1 = add2BeginningEndOfArray(cuts, 0, n);
        Arrays.sort(cuts1);
        Integer[][] dp = new Integer[c + 1][c + 1];
        return f(1, c, cuts1, dp);

    }

    public static int f(int i, int j, int cuts[], Integer[][] dp) {

        if (i > j)
            return 0;

        if (dp[i][j] != null) return dp[i][j];

        int mini = Integer.MAX_VALUE;

        for (int ind = i; ind <= j; ind++) {

            int ans = cuts[j + 1] - cuts[i - 1] +
                    f(i, ind - 1, cuts, dp) +
                    f(ind + 1, j, cuts, dp);

            mini = Math.min(mini, ans);

        }

        return dp[i][j] = mini;

    }

    public static int[] add2BeginningEndOfArray(int[] elements, int firstElement, int lastElement) {
        //int[] newArray = Arrays.copyOf(elements, elements.length + 2);
        int[] newArray = new int[elements.length + 2];
        newArray[0] = firstElement;
        System.arraycopy(elements, 0, newArray, 1, elements.length);
        newArray[elements.length + 1] = lastElement;
        return newArray;
    }

    public static int costTabulation(int n, int c, int cuts[]) {
        int[] cuts1 = add2BeginningEndOfArray(cuts, 0, n);
        Arrays.sort(cuts1);
        int[][] dp = new int[c + 2][c + 2];

        for (int i = c; i >= 1; i--) {
            for (int j = 1; j <= c; j++) {
                if (i > j) continue;
                int mini = Integer.MAX_VALUE;
                for (int ind = i; ind <= j; ind++) {
                    int ans = cuts1[j + 1] - cuts1[i - 1] +
                            dp[i][ind - 1] +
                            dp[ind + 1][j];

                    mini = Math.min(mini, ans);

                }

                dp[i][j] = mini;
            }
        }
        return dp[1][c];
    }

    public static void main(String[] args) {
        int[] cuts = {3, 5, 1, 4};

        int c = cuts.length;

        int n = 7;

        System.out.println(cost(n, c, cuts));
        System.out.println(costTabulation(n, c, cuts));
    }
}
