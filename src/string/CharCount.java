package string;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharCount {
    public static void main(String[] args) {
        System.out.println(charCount("ccccddaabbb"));
    }
    public static Map<Character, Long> charCount(String s) {
        //if(s == null || s.length() <= 1) return s;
        Map<Character, Long> collect = s.chars().boxed().collect(Collectors.groupingBy(c -> (char)c.intValue(), Collectors.counting()));
        System.out.println(collect);
        Map<Character, Long> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0L)+1);
        }
        System.out.println(map);
        Map<Character,Long> charCount = s.chars().mapToObj(c->
                (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Character,Long> charCount1 = s.chars().mapToObj(c->
                (char)c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println(charCount1);

        return charCount;
    }
}
