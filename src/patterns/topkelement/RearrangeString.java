package patterns.topkelement;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RearrangeString {
    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));

        System.out.println(reorganizeStringOptimised("aab"));
        System.out.println(reorganizeStringOptimised("aaab"));
        System.out.println("Rearranged string: " +
                RearrangeString.rearrangeString("aappp"));
        System.out.println("Rearranged string: " +
                RearrangeString.rearrangeString("Programming"));
        System.out.println("Rearranged string: " +
                RearrangeString.rearrangeString("aapa"));


    }
//TC: O(N)
    public static String reorganizeStringOptimised(String s) {
        int[] charCount = new int[26];
        char[] arr = s.toCharArray();
        for (char ch : arr) {
            charCount[ch - 'a']++;
        }
        int max = 0, letter = 0;
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > max) {
                max = charCount[i];
                letter = i;
            }
        }
        if (max > (s.length() + 1) / 2) return "";
        int index = 0;
        char[] res = new char[s.length()];
        while (charCount[letter] > 0) {
            res[index] = (char) (letter + 'a');
            index += 2;
            charCount[letter]--;
        }
        for (int i = 0; i < 26; i++) {
            while (charCount[i] > 0) {
                if (index >= s.length()) index = 1;
                res[index] = (char) (i + 'a');
                index += 2;
                charCount[i]--;
            }
        }
        return String.valueOf(res);
    }

    //TC: O(N*logN)
    public static String reorganizeString(String s) {
        int[] charCount = new int[26];
        for (char ch : s.toCharArray()) {
            charCount[ch - 'a']++;
        }
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 0) {
                maxHeap.add(new Pair((char) ('a' + i), charCount[i]));
            }
        }
        StringBuilder res = new StringBuilder();
        Pair block = maxHeap.poll();
        res.append(block.ch);
        block.freq--;
        while (!maxHeap.isEmpty()) {
            Pair temp = maxHeap.poll();
            res.append(temp.ch);
            temp.freq--;
            if (block.freq > 0)
                maxHeap.add(block);
            block = temp;
        }
        if (block.freq > 0) return "";
        return res.toString();
    }
  //TC:  O(N*logN)
    public static String rearrangeString(String str) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<Map.Entry<Character, Integer>>(
                        (e1, e2) -> e2.getValue() - e1.getValue());

        // add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entrySet());

        Map.Entry<Character, Integer> previousEntry = null;
        StringBuilder resultString = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            // add the previous entry back in the heap if its frequency is greater than zero
            if (previousEntry != null && previousEntry.getValue() > 0)
                maxHeap.offer(previousEntry);
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            previousEntry = currentEntry;
        }
        // if we were successful in appending all the characters to the result string,
        // return it
        return resultString.length() == str.length() ? resultString.toString() : "";

    }



        static class Pair{
        char ch;
        int freq;
        Pair(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }
    }
}
