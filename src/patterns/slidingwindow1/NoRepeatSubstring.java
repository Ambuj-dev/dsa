package patterns.slidingwindow1;

import java.util.HashMap;
import java.util.Map;

public class NoRepeatSubstring {
    public static int nonRepeatSubstring(String word) {
        // sliding window with hashmap

        int windowStart = 0;
        int maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        //try to extend the range [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < word.length(); windowEnd++) {
            char endChar = word.charAt(windowEnd);

            //if the map already contains the endChar,
            //shrink the window from the beginning
            //so that we only have on occurrence of endChar
            if(charIndexMap.containsKey(endChar)) {

                //this is tricky; in the current window,
                //we will not have any endChar after
                //it's previous index. and if windowStart
                //is already ahead of the last index of
                //endChar, we'll keep windowStart
                windowStart = Math.max(windowStart, charIndexMap.get(endChar) + 1);
            }

            //insert the endChar into the map
            charIndexMap.put(endChar, windowEnd);

            //remember the maximum length so far
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(NoRepeatSubstring.nonRepeatSubstring("aabccbb"));//3
        System.out.println(NoRepeatSubstring.nonRepeatSubstring("abbbb"));//2
        System.out.println(NoRepeatSubstring.nonRepeatSubstring("abccde"));//3
        System.out.println(NoRepeatSubstring.nonRepeatSubstring("geeksforgeeks"));//7
    }


}
