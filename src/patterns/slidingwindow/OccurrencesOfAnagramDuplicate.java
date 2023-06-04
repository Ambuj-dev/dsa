package patterns.slidingwindow;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OccurrencesOfAnagramDuplicate {

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
       int res = 0, startPos = 0, endPos = 0;
       Map<String, Long> charCount = Arrays.stream(word.split(""))
               .map(String::toLowerCase)
               .collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));
       int count = charCount.size();

       while(endPos < textLen){
           String key = String.valueOf(text.charAt(endPos));
           if(charCount.containsKey(key)){
               charCount.put(key, charCount.get(key) - 1);
               if(charCount.get(key) == 0){
                   count --;
               }
           }
           if(endPos - startPos + 1 < wordLen){
               endPos++;
           }
           else if(endPos - startPos + 1 == wordLen){
               if(count == 0){
                   res++;
               }
               key = String.valueOf(text.charAt(startPos));
               if(charCount.containsKey(key)){
                   charCount.put(key, charCount.get(key) + 1);
                   if(charCount.get(key) == 0){
                       count ++;
                   }
               }
               startPos++;
               endPos++;
           }

       }
       return  res;
    }
}
