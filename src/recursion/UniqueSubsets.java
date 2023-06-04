package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/*
Example 1:

Input: array[] = [1,2,2]

Output: [ [ ],[1],[1,2],[1,2,2],[2],[2,2] ]

Explanation: We can have subsets ranging from  length 0 to 3. which are listed above. Also the subset [1,2] appears twice but is printed only once as we require only unique subsets.

Input: array[] = [1]

Output: [ [ ], [1] ]

Explanation: Only two unique subsets are available
 */
public class UniqueSubsets {

    static void printAns(List<String> ans) {
        System.out.println("The unique subsets are ");
        System.out.println(ans.toString().replace(",", " "));
    }

    public static void fun(int[] nums, int index, List<Integer> ds, HashSet<String> res) {
        if (index == nums.length) {
            Collections.sort(ds);
            res.add(ds.toString());
            return;
        }
        ds.add(nums[index]);
        fun(nums, index + 1, ds, res);
        ds.remove(ds.size() - 1);
        fun(nums, index + 1, ds, res);
    }

    public static List<String> subsetsWithDup(int[] nums) {
        List<String> ans = new ArrayList<>();
        HashSet<String> res = new HashSet<>();
        List<Integer> ds = new ArrayList<>();
        fun(nums, 0, ds, res);
        for (String it : res) {
            ans.add(it);
        }
        return ans;
    }

    public static void main(String args[]) {
        int nums[] = {1, 2, 2};
        List<String> ans = subsetsWithDup(nums);
        printAns(ans);

    }
}
