package patterns.backtracking;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    public List<List<Integer>> getAllFactors(int n, int start, List<Integer> curr, List<List<Integer>> result) {
        // This loop is used to find all the factors of the input number n
        for (int i = start; i <= (int)Math.sqrt(n); i++) {
            // If n is divisible by i, add i to the curr list of factors; curr is used to store the current factors being calculated
            if (n % i == 0) {
                curr.add(i); // Found a factor, append it to the list of factors
                // Append the current factors and the corresponding factor of n // i to the result list
                List<Integer> currCopy = new ArrayList<>(curr);
                currCopy.add(n/i);
                result.add(currCopy);
                // Recursively call the function with n // i as the new input, i as the new start value, and curr and result as the current factors and results
                getAllFactors(n / i, i, curr, result);
                curr.remove(curr.size() - 1); // Remove last element from curr to backtrack and find other factors
            }
        }
        return result;
    }

    public List<List<Integer>> getFactors(int n) {
        // Call the helper function to get all the factor combinations and return the result
        return getAllFactors(n, 2, new ArrayList<Integer>(), new ArrayList<List<Integer>>());
    }

    public static void main(String[] args) {
        FactorCombinations s = new FactorCombinations();

        // Test case 1
        System.out.println(s.getFactors(8));
        // Expected output: [[2, 2, 2], [2, 4]]

        // Test case 2
        System.out.println(s.getFactors(12));
        // Expected output: [[2, 2, 3], [2, 6], [3, 4]]

        // Test case 3
        System.out.println(s.getFactors(16));
        // Expected output: [[2, 2, 2, 2], [2, 2, 4], [2, 8], [4, 4]]

        // Test case 4
        System.out.println(s.getFactors(20));
        // Expected output: [[2, 2, 5], [2, 10], [4, 5]]

        // Test case 5
        System.out.println(s.getFactors(1));
        // Expected output: []
    }
}
