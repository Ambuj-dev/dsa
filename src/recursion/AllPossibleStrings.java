package recursion;

import java.util.ArrayList;
import java.util.Collections;

public class AllPossibleStrings {
    static ArrayList<String> AllPossibleStrings(String s) {
        int n = s.length();
        ArrayList<String> ans = new ArrayList<>();
        //Loop for 1 to 2^n-1
        for (int num = 0; num < (1 << n); num++) {
            String sub = "";
            //Loop from strings start to end
            for (int i = 0; i < n; i++) {
                //check if the ith bit is set or not
                if ((num & (1 << i)) != 0) {
                    sub += s.charAt(i);
                }
            }
            if (sub.length() > 0) {
                ans.add(sub);
            }
        }
        Collections.sort(ans);
        return ans;
    }

    static void solve(int i, String s, String f) {
        if (i == s.length()) {
            System.out.print(f+" ");
            return;
        }
        //picking
        //f = f + s.charAt(i);
        solve(i + 1, s,  f+s.charAt(i));
        //poping out while backtracking
        //f.pop_back();
        solve(i + 1, s,  f);
    }


    public static void main(String args[]) {
        String s = "abc";
        ArrayList<String> ans = AllPossibleStrings(s);
        //printint all the subsequence.
        System.out.println("All possible subsequences are ");
        for (String it : ans) {
            System.out.print(it + " ");
        }
        System.out.println("");
        String f = "";
        System.out.println("All possible subsequences are: ");
        solve(0, s, f);


    }


}
