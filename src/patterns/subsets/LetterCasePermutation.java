package patterns.subsets;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
        List<String> result =
                LetterCasePermutation.findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);

        result = LetterCasePermutation.findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);
    }

    public static List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        permutation(s.toCharArray(), 0, ans);
        return ans;
    }

    public static void permutation(char[] nums, int index, List<String> ans){
        if(index == nums.length){
            ans.add(new String(nums));
            return;
        }
        if(Character.isDigit(nums[index])){
            permutation(nums, index+1, ans);
            return;
        }
        nums[index] = Character.toLowerCase(nums[index]);
        permutation(nums, index+1, ans);
        nums[index] = Character.toUpperCase(nums[index]);
        permutation(nums, index+1, ans);
    }
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null)
            return permutations;

        permutations.add(str);
        // process every character of the string one by one
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) { // only process characters, skip digits
                // we'll take all existing permutations and change the letter case appropriately
                int n = permutations.size();
                for (int j = 0; j < n; j++) {
                    char[] chs = permutations.get(j).toCharArray();
                    // if the current char is in upper case change it to lower case or vice versa
                    if (Character.isUpperCase(chs[i]))
                        chs[i] = Character.toLowerCase(chs[i]);
                    else
                        chs[i] = Character.toUpperCase(chs[i]);
                    permutations.add(String.valueOf(chs));
                }
            }
        }
        return permutations;
    }

}
