package patterns.topkelement;

import java.util.*;

public class RearrangeStringKDistanceApart {
    public static void main(String[] args) {
        System.out.println(reorganizeString("aabbcc", 3));
        System.out.println(reorganizeString("aaabc", 3));
        System.out.println(reorganizeString("aaaddbbccc", 2));

        System.out.println(rearrangeString("aabbcc", 3));
        System.out.println(rearrangeString("aaabc", 3));
        System.out.println(rearrangeString("aaaddbbccc", 2));

        System.out.println(rearrangeStringOptimized("aabbcc", 3));
        System.out.println(rearrangeStringOptimized("aaabc", 3));
        System.out.println(rearrangeStringOptimized("aaaddbbccc", 2));

    }

    public static String rearrangeString(String s, int k) {
        if (k <= 1)
            return s;
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        // Initialize a max heap sorted based on the frequency of the characters
        // For two characters with the same frequency, sort based on alphabetical order
        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : b.getValue() - a.getValue());

        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            sb.append(entry.getKey());
            entry.setValue(entry.getValue() - 1);
            waitQueue.offer(entry);

            // need to wait for distance k
            if (waitQueue.size() < k) {
                continue;
            }

            Map.Entry<Character, Integer> front = waitQueue.poll();
            if (front.getValue() > 0) {
                maxHeap.offer(front);
            }
        }

        return sb.length() == s.length() ? sb.toString() : "";
    }


    public static String reorganizeString(String s, int k) {
        int[] charCount = new int[26];
        for(char ch: s.toCharArray()){
            charCount[ch - 'a']++;
        }
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a,b) -> (b.freq - a.freq == 0)?a.ch - b.ch : b.freq - a.freq);
        for(int i = 0; i< charCount.length; i++){
            if(charCount[i]>0) {
                maxHeap.add(new Pair((char) ('a'+i), charCount[i]));
            }
        }
        StringBuilder res = new StringBuilder();
        while(maxHeap.size() >= k){
            Pair[] pairs = new Pair[k];
            for(int i = 0 ; i< k; i++){
                pairs[i] = maxHeap.poll();
            }
            for(int i = 0; i < k; i++){
                res.append(pairs[i].ch);
                pairs[i].freq--;
                if(pairs[i].freq > 0) maxHeap.add(pairs[i]);
            }
        }
        while(!maxHeap.isEmpty()){
            Pair pair = maxHeap.poll();
            res.append(pair.ch);
            pair.freq--;
            if(pair.freq > 0) return "";
        }
        return  res.toString();
    }

    static class Pair{
        char ch;
        int freq;
        Pair(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }
    }

    public static String rearrangeStringOptimized(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int[] nextPosition = new int[26];
        for (int i = 0; i < len; i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < len; index++) {
            int candidate = findCandidate(count, nextPosition, index);
            if (candidate == -1) return "";
            count[candidate]--;
            nextPosition[candidate] = index + k;
            sb.append((char)('a' + candidate));
        }

        return sb.toString();
    }

    private static int findCandidate(int[] count, int[] nextPosition, int index) {
        int max = Integer.MIN_VALUE;
        int candidate = -1;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0 && count[i] > max && index >= nextPosition[i]) {
                max = count[i];
                candidate = i;
            }
        }
        return candidate;
    }
}
