package patterns.dp.lcs;

import java.util.HashMap;
import java.util.Map;

public class StringInterleaving {

    static boolean findSI(String m, String n, String p) {
        return findSIRecursive(m, n, p, 0, 0, 0);
    }
    static boolean findSIRecursive(String m, String n, String p, int mIndex, int nIndex, int pIndex) {
        // if we have reached the end of the all the strings
        if (mIndex == m.length() && nIndex == n.length() && pIndex == p.length())
            return true;

        // if we have reached the end of 'p' but 'm' or 'n' still have some characters left
        if (pIndex == p.length()) return false;

        boolean mMatchesP = false;
        boolean nMatchesP = false;

        if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex))
            mMatchesP = findSIRecursive(m, n, p, mIndex + 1, nIndex, pIndex + 1);

        if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex))
            nMatchesP = findSIRecursive(m, n, p, mIndex, nIndex + 1, pIndex + 1);

        return mMatchesP || nMatchesP;
    }
    static boolean findSIMemoized(String m, String n, String p) {
        Map<String, Boolean> map = new HashMap<>();
        return findSIRecursive(map, m, n, p, 0, 0, 0);
    }
    static boolean findSIRecursive(Map<String, Boolean> map, String m, String n, String p, int mIndex, int nIndex, int pIndex) {
        // if we have reached the end of the all the strings
        if (mIndex == m.length() && nIndex == n.length() && pIndex == p.length())
            return true;

        // if we have reached the end of 'p' but 'm' or 'n' still have some characters left
        if (pIndex == p.length()) return false;

        String key = mIndex+" | "+nIndex+" | "+pIndex;
        if(map.get(key) != null) return map.get(key);

        boolean mMatchesP = false;
        boolean nMatchesP = false;

        if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex))
            mMatchesP = findSIRecursive(map, m, n, p, mIndex + 1, nIndex, pIndex + 1);

        if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex))
            nMatchesP = findSIRecursive(map, m, n, p, mIndex, nIndex + 1, pIndex + 1);

        boolean matches = mMatchesP || nMatchesP;
        map.put(key, matches);
        return matches;
    }

    static boolean findSITabulation(String m, String n, String p){
        boolean[][] dp = new boolean[m.length()+1][n.length()+1];

        if (m.length() + n.length() != p.length()) return false;

        for (int mIndex = 0; mIndex <= m.length(); mIndex++) {
            for (int nIndex = 0; nIndex <= n.length(); nIndex++) {
                // if 'm' and 'n' are empty, then 'p' must have been empty too.
                if (mIndex == 0 && nIndex == 0) {
                    dp[mIndex][nIndex] = true;
                }
                // if 'm' is empty, we need to check the leterleaving with 'n' only
                else {
                    int pIndex = mIndex + nIndex;
                    if (mIndex == 0 && n.charAt(nIndex - 1) == p.charAt(pIndex - 1)) {
                        dp[mIndex][nIndex] = dp[mIndex][nIndex - 1];
                    }
                    // if 'n' is empty, we need to check the leterleaving with 'm' only
                    else if (nIndex == 0 && m.charAt(mIndex - 1) == p.charAt(pIndex - 1)) {
                        dp[mIndex][nIndex] = dp[mIndex - 1][nIndex];
                    } else {
                        // if the letter of 'm' and 'p' match, we take whatever is matched till mIndex-1
                        if (mIndex > 0 && m.charAt(mIndex - 1) == p.charAt(pIndex - 1)) {
                            dp[mIndex][nIndex] = dp[mIndex - 1][nIndex];
                        }
                        // if the letter of 'n' and 'p' match, we take whatever is matched till nIndex-1 too
                        // note the '||', this is required when we have common letters
                        if (nIndex > 0 && n.charAt(nIndex - 1) == p.charAt(pIndex - 1)) {
                            dp[mIndex][nIndex] = dp[mIndex][nIndex] || dp[mIndex][nIndex - 1];
                        }
                    }
                }
            }
        }


        return dp[m.length()][n.length()];
    }


    public static void main(String[] args) {
        System.out.println(findSI("abd", "cef", "abcdef"));
        System.out.println(findSI("abc", "def", "abddccf"));

        System.out.println(findSIMemoized("abd", "cef", "abcdef"));
        System.out.println(findSIMemoized("abc", "def", "abddccf"));

        System.out.println(findSITabulation("abd", "cef", "abcdef"));
        System.out.println(findSITabulation("abc", "def", "abddccf"));

    }
}
