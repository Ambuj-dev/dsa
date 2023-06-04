package patterns.backtracking;

import java.util.HashSet;
import java.util.Set;

public class MaxNumberOfUniqueSubstring {
    public int maxUniqueSplit(String s) {
        return splitAndCount(s, 0, new HashSet<>());
    }

    private int splitAndCount(String s, int start, Set<String> set) {
        // base case, if we have reached the end of the input string, return the size of the set
        if (start == s.length())
            return set.size();

        int count = 0;
        // loop through all substrings starting from the current start position
        for (int i = start + 1; i <= s.length(); i++) {
            String string = s.substring(start, i);
            // if the substring is not in the set, add it and recursively call the function with the new start position
            if (set.add(string)) {
                count = Math.max(count, splitAndCount(s, i, set));
                set.remove(string); // remove the substring from the set and backtrack
            }
        }
        return count; // return the maximum count of unique substrings found
    }

    public static void main(String[] args) {
        MaxNumberOfUniqueSubstring sol = new MaxNumberOfUniqueSubstring();

        // Test Case 1
        String input1 = "abcabc";
        int output1 = sol.maxUniqueSplit(input1);
        System.out.println("maxUniqueSplit(" + input1 + ") = " + output1); //Expected: 4

        // Test Case 2
        String input2 = "aab";
        int output2 = sol.maxUniqueSplit(input2);
        System.out.println("maxUniqueSplit(" + input2 + ") = " + output2); //Expected: 2

        // Test Case 3
        String input3 = "ababab";
        int output3 = sol.maxUniqueSplit(input3);
        System.out.println("maxUniqueSplit(" + input3 + ") = " + output3); //Expected: 4

        // Test Case 4
        String input4 = "";
        int output4 = sol.maxUniqueSplit(input4);
        System.out.println("maxUniqueSplit(" + input4 + ") = " + output4); //Expected: 0

        // Test Case 5
        String input5 = "a";
        int output5 = sol.maxUniqueSplit(input5);
        System.out.println("maxUniqueSplit(" + input5 + ") = " + output5); //Expected: 1
    }
}
