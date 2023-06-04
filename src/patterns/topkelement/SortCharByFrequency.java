package patterns.topkelement;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortCharByFrequency {

    public static void main(String[] args) {
        System.out.println(frequencySort("aaabbccccddddeefffff"));
        System.out.println(frequencySortMaxHeap("aaabbccccddddeefffff"));
        String result = sortCharacterByFrequency("Programming");
        System.out.println("Here is the given string after sorting characters by frequency: "
                + result);

        result = sortCharacterByFrequency("abcbab");
        System.out.println("Here is the given string after sorting characters by frequency: "
                + result);
        System.out.println(frequencySort("Programming"));
        System.out.println(frequencySort("abcbab"));

    }
    public static String frequencySort(String s) {
        if(s == null || s.length() <= 1) return s;
        List<Character>[] bucket = new List[s.length()+1];
        Map<Character, Integer> charCount = new HashMap<>();
        for(char c : s.toCharArray()){
            charCount.put(c, charCount.getOrDefault(c, 0)+1);
        }

        for(char key : charCount.keySet()){
            int count = charCount.get(key);
            if(bucket[count] == null){
                bucket[count]= new ArrayList<>();
            }
            bucket[count].add(key);
        }
        StringBuffer res = new StringBuffer();
        for(int i = bucket.length -1; i >0;i--){
            if(bucket[i] != null){
                for(char ch : bucket[i]){
                    int freq = 0;
                    while(freq++ < i) {
                        res.append(ch);
                    }
                }
            }
        }

        return res.toString();
    }
    public static String sortCharacterByFrequency(String str) {
        // find the frequency of each character
        Map<Character, Integer> characterFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray()) {
            characterFrequencyMap.put(chr, characterFrequencyMap.getOrDefault(chr, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>(
                        (e1, e2) -> e2.getValue() - e1.getValue());

        // add all characters to the max heap
        maxHeap.addAll(characterFrequencyMap.entrySet());

        // build a string, appending the most occurring characters first
        StringBuilder sortedString = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            for (int i = 0; i < entry.getValue(); i++)
                sortedString.append(entry.getKey());
        }
        return sortedString.toString();
    }
    public static String frequencySortMaxHeap(String s) {
        if(s == null || s.length() <= 1) return s;
        PriorityQueue<CharFreq> maxHeap = new PriorityQueue<>((a,b) -> b.freq - a.freq);
        Map<Character, Integer> charCount = new HashMap<>();
        for(char c : s.toCharArray()){
            charCount.put(c, charCount.getOrDefault(c, 0)+1);
        }

        for(char key : charCount.keySet()){
            maxHeap.add(new CharFreq(key, charCount.get(key)));
        }
        StringBuffer res = new StringBuffer();
        while(!maxHeap.isEmpty()){
            CharFreq charFreq = maxHeap.poll();
            int pointer = 0;
            while(pointer < charFreq.freq){
                res.append(charFreq.ch);
                pointer++;
            }
        }

        return res.toString();
    }
    static class CharFreq{
        char ch;
        int freq;

        public CharFreq(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

}
