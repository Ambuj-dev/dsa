package patterns.dp.lcs;

public class EditDistance {


    static int findMinOperations(String s1, String s2) {
        return findMinOperationsRecursive(s1, s2, 0, 0);
    }

    static int findMinOperationsRecursive(String s1, String s2, int index1, int index2) {
        // if we have reached the end of s1, then we have to insert all the remaining characters of s2
        if (index1 == s1.length()) return s2.length() - index2;

        // if we have reached the end of s2, then we have to delete all the remaining characters of s1
        if (index2 == s2.length()) return s1.length() - index1;

        // If the strings have a matching character, we can recursively match for the remaining lengths.
        if (s1.charAt(index1) == s2.charAt(index2)) {
            return findMinOperationsRecursive(s1, s2, index1 + 1, index2 + 1);
        }
        //perform deletion
        int onDeletion = 1 + findMinOperationsRecursive(s1, s2, index1 + 1, index2);
        //perform insertion
        int onInsertion =
                1 + findMinOperationsRecursive(s1, s2, index1, index2 + 1);
        // perform replacement
        int onReplacement =
                1 + findMinOperationsRecursive(s1, s2, index1 + 1, index2 + 1);

        return Math.min(onDeletion, Math.min(onInsertion, onReplacement));
    }

    static int findMinOperationsMemoized(String s1, String s2) {
        Integer[][] dp = new Integer[s1.length()][s2.length()];
        return findMinOperationsRecursive(dp,s1, s2, 0, 0);
    }

    static int findMinOperationsRecursive(Integer[][] dp, String s1, String s2, int index1, int index2) {
        // if we have reached the end of s1, then we have to insert all the remaining characters of s2
        if (index1 == s1.length()) return s2.length() - index2;

        // if we have reached the end of s2, then we have to delete all the remaining characters of s1
        if (index2 == s2.length()) return s1.length() - index1;

        if(dp[index1][index2] != null) return dp[index1][index2];

        // If the strings have a matching character, we can recursively match for the remaining lengths.
        if (s1.charAt(index1) == s2.charAt(index2)) {
            return dp[index1][index2] = findMinOperationsRecursive(dp, s1, s2, index1 + 1, index2 + 1);
        }
        //perform deletion
        int onDeletion = 1 + findMinOperationsRecursive(dp, s1, s2, index1 + 1, index2);
        //perform insertion
        int onInsertion =
                1 + findMinOperationsRecursive(dp, s1, s2, index1, index2 + 1);
        // perform replacement
        int onReplacement =
                1 + findMinOperationsRecursive(dp, s1, s2, index1 + 1, index2 + 1);

        return dp[index1][index2] = Math.min(onDeletion, Math.min(onInsertion, onReplacement));
    }

    static int findMinOperationsTabulation(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        // if s2 is empty, we can remove all the characters of s1 to make it empty too
        for (int index1 = 0; index1 <= s1.length(); index1++) dp[index1][0] = index1;

        // if s1 is empty, we have to insert all the characters of s2
        for (int index2 = 0; index2 <= s2.length(); index2++) dp[0][index2] = index2;

        for (int index1 = 1; index1 <= s1.length(); index1++) {
            for (int index2 = 1; index2 <= s2.length(); index2++) {
                // If the strings have a matching character, we can recursively match for the remaining lengths
                if (s1.charAt(index1 - 1) == s2.charAt(index2 - 1)) {
                    dp[index1][index2] = dp[index1 - 1][index2 - 1];
                } else {
                    dp[index1][index2] =
                            1 +Math.min(dp[index1 - 1][index2],//delete
                                            Math.min(dp[index1][index2 - 1], //insert
                                                    dp[index1 - 1][index2 - 1])); //replace
                }
            }
        }

        return dp[s1.length()][s2.length()];

    }


    public static void main(String[] args) {
        System.out.println(findMinOperations("bat", "but"));//1
        System.out.println(findMinOperations("abdca", "cbda"));//2
        System.out.println(findMinOperations("passpot", "ppsspqrt"));//3

        System.out.println(findMinOperationsMemoized("bat", "but"));//1
        System.out.println(findMinOperationsMemoized("abdca", "cbda"));//2
        System.out.println(findMinOperationsMemoized("passpot", "ppsspqrt"));//3

        System.out.println(findMinOperationsTabulation("bat", "but"));//1
        System.out.println(findMinOperationsTabulation("abdca", "cbda"));//2
        System.out.println(findMinOperationsTabulation("passpot", "ppsspqrt"));//3
    }
}
