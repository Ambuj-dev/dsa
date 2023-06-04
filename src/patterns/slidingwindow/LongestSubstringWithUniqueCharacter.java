package patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithUniqueCharacter {

    static public void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;
        System.out.println(kUniques(s, k));
        System.out.println(kUniques("aabbcc",3));
        System.out.println(kUniques("aabbcc",2));
        System.out.println(kUniques("aaabbb", 3));
        System.out.println(kUniquesOptimized(s, k));
        System.out.println(kUniquesOptimized("aabbcc",3));
        System.out.println(kUniquesOptimized("aabbcc",2));
        System.out.println(kUniquesOptimized("aaabbb", 3));

    }

    private static int kUniques(String word, int k) {
        Map<String, Integer> map = new HashMap<>();
        int i=0, j=0, max = 0;
        while(j<word.length()){
            String key = String.valueOf(word.charAt(j));
            map.put(key, map.getOrDefault(key, 0)+1);
            if(map.size() < k){
               // j++;
            }else if (map.size() == k){
                max = Math.max(max, j-i+1);
               // j++;
            }//else if(map.size() > k){
                while(map.size() > k){
                    key = String.valueOf(word.charAt(i));

                    map.put(key, map.get(key) - 1);
                    if(map.get(key) == 0){
                        map.remove(key);
                    }
                    i++;
                }
                j++;
            }
       // }
        return max;
    }

    private static int kUniquesOptimized(String word, int k) {
        Map<String, Integer> map = new HashMap<>();
        int i=0, j=0, max = 0;
        while(j<word.length()){
            String key = String.valueOf(word.charAt(j));
            map.put(key, map.getOrDefault(key, 0)+1);
            if (map.size() == k){
                max = Math.max(max, j-i+1);
            }
            while(map.size() > k){
                key = String.valueOf(word.charAt(i));

                map.put(key, map.get(key) - 1);
                if(map.get(key) == 0){
                    map.remove(key);
                }
                i++;
            }
            j++;
        }
        return max;
    }
}
