package blind75;

import java.util.*;

public class GroupAnagram {
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs.length == 0) return res;
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            int[] hash = new int[26];
            for(char ch : str.toCharArray()){
                hash[ch-'a']++;
            }
            String key = Arrays.toString(hash);
            map.computeIfAbsent(key, k -> new ArrayList<>() );
            map.get(key).add(str);
        }
        res.addAll(map.values());
        return res;
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }
}
