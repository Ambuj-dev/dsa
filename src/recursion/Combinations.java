package recursion;

import java.util.ArrayList;
import java.util.List;

/*
Example 1:

Input: array = [2,3,6,7], target = 7

Output: [[2,2,3],[7]]

Explanation: 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
             7 is a candidate, and 7 = 7.
             These are the only two combinations.


Example 2:

Input: array = [2], target = 1

Output: []

Explanation: No combination is possible.
 */
public class Combinations {
    private void findCombinations(int ind, int[] arr, int target, List<List<Integer>> ans, List<Integer> ds) {
        if (ind == arr.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(ds));
            }
            return;
        }

        if (arr[ind] <= target) {
            ds.add(arr[ind]);
            findCombinations(ind, arr, target - arr[ind], ans, ds);
            ds.remove(ds.size() - 1);
        }
        findCombinations(ind + 1, arr, target, ans, ds);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(0, candidates, target, ans, new ArrayList<>());
        return ans;
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 6, 7};
        int target = 7;
        Combinations sol = new Combinations();
        List<List<Integer>> ls = sol.combinationSum(arr, target);
        System.out.println("Combinations are: ");
        for (int i = 0; i < ls.size(); i++) {
            for (int j = 0; j < ls.get(i).size(); j++) {
                System.out.print(ls.get(i).get(j) + " ");
            }
            System.out.println();
        }

        // Test case 1
        int[] candidates = { 2, 3, 6, 7 };
         target = 7;
        System.out.println(sol.combinationSum(candidates, target)); // expected output: [[2, 2, 3], [7]]

        // Test case 2
        candidates = new int[] { 2, 3, 5 };
        target = 8;
        System.out.println(sol.combinationSum(candidates, target)); // expected output: []

        // Test case 3
        candidates = new int[] {};
        target = 8;
        System.out.println(sol.combinationSum(candidates, target)); // expected output: []

        // Test case 4
        candidates = new int[] { 5, 10, 15 };
        target = 20;
        System.out.println(sol.combinationSum(candidates, target)); // expected output: [[5,5,5,5], [5,5,10], [5,15], [10,10]]

        // Test case 5
        candidates = new int[] { 2, 4, 6, 8 };
        target = 10;
        System.out.println(sol.combinationSum(candidates, target)); // expected output: [[2,2,2,2,2], [2,2,2,4], [2,2,6],
        // [2,4,4], [2,8], [4,6]]

        // Test case 6
        candidates = new int[] { 2, 3, 5 };
        target = 0;
        System.out.println(sol.combinationSum(candidates, target)); // expected output: [[]]
    }
}
