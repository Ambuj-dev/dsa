package patterns.slidingwindow;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OccurrencesOfAnagram {

    // Driver code
    public static void main(String args[])
    {
        String text = "forxxorfxdofr";
        String word = "for";
       // String text = "aAbaAbaa";
        //String word = "aaba";
        System.out.println(countAnagrams(text, word));
    }

    private static int countAnagrams(String text, String word) {

        int textLen = text.length();
        int wordLen = word.length();

        int res = 0;
        int i=0;
        int j=0;

        Map<String, Long> charCount = Arrays.stream(word.split(""))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));
        int count = charCount.size();
        text = text.toLowerCase();
        while(j<textLen){
            String key = String.valueOf(text.charAt(j));
            if(charCount.containsKey(key)){
                charCount.put(key, charCount.get(key) - 1);
                if(charCount.get(key) == 0){
                    count--;
                }
            }
            if(j-i+1 < wordLen){
                j++;
            }else if(j-i+1 == wordLen){
                if(count == 0){
                    res++;
                }
                key = String.valueOf(text.charAt(i));
                if(charCount.containsKey(key)) {
                    charCount.put(key, charCount.get(key) + 1);
                    if(charCount.get(key) == 1){
                        count++;
                    }
                }
                i++;
                j++;
            }
        }
        return res;
    }
}
