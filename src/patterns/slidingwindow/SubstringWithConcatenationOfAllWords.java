package patterns.slidingwindow;

import java.util.*;
import java.util.stream.Collectors;

/*
* You are given a string s and an array of strings words. All the strings of words are of the same length.

A concatenated substring in s is a substring that contains all the strings of any permutation of words concatenated.

For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated substring because it is not the concatenation of any permutation of words.
Return the starting indices of all the concatenated substrings in s. You can return the answer in any order.

Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Since words.length == 2 and words[i].length == 3, the concatenated substring has to be of length 6.
The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
The output order does not matter. Returning [9,0] is fine too.
Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Explanation: Since words.length == 4 and words[i].length == 4, the concatenated substring has to be of length 16.
There is no substring of length 16 is s that is equal to the concatenation of any permutation of words.
We return an empty array.
Example 3:

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
Explanation: Since words.length == 3 and words[i].length == 3, the concatenated substring has to be of length 9.
The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"] which is a permutation of words.
The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"] which is a permutation of words.
The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"] which is a permutation of words.
* */
public class SubstringWithConcatenationOfAllWords {

    public static List<Integer> findSubstring(String s, String[] words) {
        long time = System.currentTimeMillis();
        int k = words.length;
        int wordLength = words[0].length();
        int substringSize = wordLength * k;
        HashMap<String, Long> wordCount = Arrays.stream(words).collect(Collectors.groupingBy(word->word, LinkedHashMap::new, Collectors.counting()));

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            HashMap<String, Long> wordMap = new HashMap<>(wordCount);
            int right = i,  left = i;
            int wordUsed = 0;
            while (right <= s.length() - wordLength) {
                String sub = s.substring(right, right + wordLength);
                if(left < right) {
                    while (left < right && ((wordMap.containsKey(sub) && wordMap.get(sub) <= 0) || !wordMap.containsKey(sub) || right - left == substringSize)) {
                        String leftSub = s.substring(left, left + wordLength);
                        if (wordMap.containsKey(leftSub)) {
                            wordMap.put(leftSub, wordMap.get(leftSub) + 1);
                            wordUsed--;
                        }
                        left = left + wordLength;
                    }
                }
                if (wordMap.containsKey(sub) && wordMap.get(sub) > 0) {
                    wordMap.put(sub, wordMap.get(sub) - 1);
                    wordUsed++;
                }
                right = right + wordLength;
                if (wordUsed == k) {
                    answer.add(left);
                }
            }
        }
        System.out.println("Total Time: "+(System.currentTimeMillis() - time));
        return answer;
    }
    public static List<Integer> findSubstringOptimized(String s, String[] words) {
        long time = System.currentTimeMillis();
        if(s == null || s.length() == 0 || words == null || words.length == 0){
            return new ArrayList<>();
        }
        int totalWords = words.length;
        int wordLength = words[0].length();
        HashMap<String, Long> wordCount = Arrays.stream(words).collect(Collectors.groupingBy(word->word, LinkedHashMap::new, Collectors.counting()));
        List<Integer> answer = new ArrayList<>();
        for(int i = 0; i <= s.length() - totalWords * wordLength ; i++) {
            HashMap<String, Long> copyMap = new HashMap<>(wordCount);
            for (int j = 0; j < totalWords; j++) {
                int wordIndex = i + j * wordLength;
                String word = s.substring(wordIndex, wordIndex + wordLength);
                if (copyMap.containsKey(word)) {
                    long count = copyMap.get(word);
                    if (count == 1) copyMap.remove(word);
                    else copyMap.put(word, copyMap.get(word) - 1);
                    if (copyMap.isEmpty()) {
                        answer.add(i);
                        break;
                    }
                }else{
                    break;
                }
            }
        }
        System.out.println("Total Time: "+(System.currentTimeMillis() - time));
        return answer;
    }




    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println( findSubstringOptimized("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));
        System.out.println( findSubstringOptimized("barfoofoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
        System.out.println( findSubstringOptimized("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
        System.out.println( findSubstringOptimized("barfoothefoobarman", new String[]{"bar","foo"}));
        System.out.println( findSubstringOptimized("ababafoobarmanre", new String[]{"aba","bar","foo"}));
        System.out.println( findSubstringOptimized("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
        System.out.println( findSubstringOptimized("foobarfoobarthefoobarman", new String[]{"bar", "foo"}));
        //System.out.println("Total Time: "+(System.currentTimeMillis() - time));
        long currentTime = System.currentTimeMillis();
        System.out.println( findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));
        System.out.println( findSubstring("barfoofoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
        System.out.println( findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
        System.out.println( findSubstring("barfoothefoobarman", new String[]{"bar","foo"}));
        System.out.println( findSubstring("ababafoobarmanre", new String[]{"aba","bar","foo"}));
        System.out.println( findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
        System.out.println( findSubstring("foobarfoobarthefoobarman", new String[]{"bar", "foo"}));
        //System.out.println("Total Time: "+(System.currentTimeMillis() - currentTime));

    }
}
/*
Answers:
[8]
[9, 12, 15]
[]
[0, 9]
[2]
[6, 9, 12]
[0, 3, 6, 15]
 */