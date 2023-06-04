package patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAllUniqueCharacter {

    static public void main(String[] args) {
        String s = "aabacbebebe";
        System.out.println(allUniques(s));
    }

    private static int allUniques(String word) {
        Map<String, Integer> map = new HashMap<>();
        int i=0, j=0, max = 0;
        while(j<word.length()){
            String key = String.valueOf(word.charAt(j));
            map.put(key, map.getOrDefault(key, 0)+1);
            if (map.size() == j-i+1){
                max = Math.max(max, j-i+1);
               // j++;
            }//else if(map.size() < j-i+1){
                while(map.size() < j-i+1){
                    key = String.valueOf(word.charAt(i));
                    map.put(key, map.get(key) - 1);
                    if(map.get(key) == 0){
                        map.remove(key);
                    }
                    i++;
                }
                j++;
            }
        //}
        return max;
    }
}
