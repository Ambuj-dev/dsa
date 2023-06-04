package patterns.topkelement;

import java.util.*;

public class TaskScheduler {
    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
        System.out.println(leastInterval(new char[]{'A','A','A','B','B','B'}, 0));
        System.out.println(leastInterval(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'}, 2));
        System.out.println(leastInterval(new char[]{'A','A','A','B','B','C','B','C','D','D','F','G'}, 2));

        System.out.println(leastIntervalSorting(new char[]{'A','A','A','B','B','B'}, 2));
        System.out.println(leastIntervalSorting(new char[]{'A','A','A','B','B','B'}, 0));
        System.out.println(leastIntervalSorting(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'}, 2));
        System.out.println(leastIntervalSorting(new char[]{'A','A','A','B','B','C','B','C','D','D','F','G'}, 2));

        char[] tasks = new char[] { 'a', 'a', 'a', 'b', 'c', 'c' };
        System.out.println("Minimum intervals needed to execute all tasks: " +
                TaskScheduler.scheduleTasks(tasks, 2));

        tasks = new char[] { 'a', 'b', 'a' };
        System.out.println("Minimum intervals needed to execute all tasks: " +
                TaskScheduler.scheduleTasks(tasks, 3));

    }
/*
max = length of maximum occurring character
maxCount = total number of character that are occurring for max times.
Let's take a example AAABBBC , n=2
result: 7 => ABCAB#AB
first, we try to fill most frequent character and a empty gap of length n is created between these two characters
A _ _ A _ _ A
here places of most frequent character is fixed , and in empty spaces we have to fill remaining character, we can also notice that length of all part (excluding last character 'A') is equals to (max - 1) * (n+1) , i.e. A _ _ A _ _
Reason -> as we are excluding last 'A' total slots created is (max-1) and after including 'A' and empty spaces (A _ _ ) , length of one slot is (n+1)
so, length after excluding last 'A' = (max-1)*(n+1)
But, there can be a case where more than one character can appear for max times, so in this case we have to fill second character just after first,
A B _ A B _ A B
in this case, length of last part which was excluded earlier is equals to maxCount, A B _ A B _ A B
so, totalLength = ((max-1) * (n+1)) + maxCount;
But, wait!, what if we have given n=0, we don't have to bother about the order of occurrences, so it result will be equals to tasks.length.
So,
result=Math.max(tasks.length, ((max-1) * (n+1)) + maxCount);

 */
   // Time - Complexity : O(n)
   // Space - Complexity: O(26)
    public static int leastInterval(char[] tasks, int n) {
        if(n < 1) return tasks.length;

        int[] count = new int[26];
        int max = 0, maxCount = 0;

        for(char ch : tasks){
            count[ch-'A']++;
            if(count[ch-'A']==max){
                maxCount++;
            }else if(count[ch-'A']>max){
                max=count[ch-'A'];
                maxCount=1;
            }
        }

        return Math.max(tasks.length, (max-1)*(n+1) + maxCount);
    }

    //O(N∗logN)
    public static int scheduleTasks(char[] tasks, int k) {
        int intervalCount = 0;
        Map<Character, Integer> taskFrequencyMap = new HashMap<>();
        for (char chr : tasks)
            taskFrequencyMap.put(chr, taskFrequencyMap.getOrDefault(chr, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<Map.Entry<Character, Integer>>(
                        (e1, e2) -> e2.getValue() - e1.getValue());

        // add all tasks to the max heap
        maxHeap.addAll(taskFrequencyMap.entrySet());

        while (!maxHeap.isEmpty()) {
            List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();
            int n = k + 1; // try to execute as many as 'k+1' tasks from the max-heap
            for (; n > 0 && !maxHeap.isEmpty(); n--) {
                intervalCount++;
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                if (currentEntry.getValue() > 1) {
                    currentEntry.setValue(currentEntry.getValue() - 1);
                    waitList.add(currentEntry);
                }
            }
            maxHeap.addAll(waitList); // put all the waiting list back on the heap
            if (!maxHeap.isEmpty())
                intervalCount += n; // we'll be having 'n' idle intervals for the next iteration
        }

        return intervalCount;
    }
    public static int leastIntervalSorting(char[] tasks, int n) {
        // Create a frequency array to keep track of the count of each task
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        // Sort the frequency array in non-decreasing order
        Arrays.sort(freq);
        // Calculate the maximum frequency of any task
        int maxFreq = freq[25] - 1;
        // Calculate the number of idle slots that will be required
        int idleSlots = maxFreq * n;
        // Iterate over the frequency array from the second highest frequency to the lowest frequency
        for (int i = 24; i >= 0 && freq[i] > 0; i--) {
            // Subtract the minimum of the maximum frequency and the current frequency from the idle slots
            idleSlots -= Math.min(maxFreq, freq[i]);
        }
        // If there are any idle slots left, add them to the total number of tasks
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }

}
