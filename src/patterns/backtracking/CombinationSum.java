package patterns.backtracking;
import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            backtrack(candidates, 0, target, new ArrayList<>(), res);
            return res;
        }

        private void backtrack(int[] candidates, int start, int target, List<Integer> comb, List<List<Integer>> res) {
            // If target is 0, we have found a valid combination
            if (target == 0) {
                // Append a copy of the current combination to the result list
                res.add(new ArrayList<>(comb));
                return;
            }
            // Iterate through the candidates array starting from the given index
            for (int i = start; i < candidates.length; i++) {
                // If the current candidate is greater than the remaining target, move on to the
                // next
                if (target < candidates[i]) {
                    break;
                }
                // Add the current candidate to the current combination
                comb.add(candidates[i]);
                // Recursively call the function with the updated combination and remaining
                // target
                backtrack(candidates, i, target - candidates[i], comb, res);
                // Backtrack by removing the last added candidate from the combination
                comb.remove(comb.size() - 1);
            }
        }

        public static void main(String[] args) {
            CombinationSum s = new CombinationSum();

            // Test case 1
            int[] candidates = { 2, 3, 6, 7 };
            int target = 7;
            System.out.println(s.combinationSum(candidates, target)); // expected output: [[2, 2, 3], [7]]

            // Test case 2
            candidates = new int[] { 2, 3, 5 };
            target = 8;
            System.out.println(s.combinationSum(candidates, target)); // expected output: [[2, 2, 2, 2], [2, 3, 3], [3, 5]]

            // Test case 3
            candidates = new int[] {};
            target = 8;
            System.out.println(s.combinationSum(candidates, target)); // expected output: []

            // Test case 4
            candidates = new int[] { 5, 10, 15 };
            target = 20;
            System.out.println(s.combinationSum(candidates, target)); // expected output: [[5,5,5,5], [5,5,10], [5,15], [10,10]]

            // Test case 5
            candidates = new int[] { 2, 4, 6, 8 };
            target = 10;
            System.out.println(s.combinationSum(candidates, target)); // expected output: [[2,2,2,2,2], [2,2,2,4], [2,2,6],
            // [2,4,4], [2,8], [4,6]]

            // Test case 6
            candidates = new int[] { 2, 3, 5 };
            target = 0;
            System.out.println(s.combinationSum(candidates, target)); // expected output: [[]]
        }
    }

