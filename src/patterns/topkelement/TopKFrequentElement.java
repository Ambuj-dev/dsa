package patterns.topkelement;

import java.util.*;

public class TopKFrequentElement {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3,3},2)));
        System.out.println(Arrays.toString(topKFrequent1(new int[]{1,1,1,2,2,3,3},2)));
        List<Integer> result = TopKFrequentElement.findTopKFrequentNumbers(
                new int[] { 1, 3, 5, 12, 11, 12, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);

        result = TopKFrequentElement.findTopKFrequentNumbers(new int[] { 5, 12, 11, 3, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);

    }
//TC:O(N)
    public static  int[] topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < nums.length; i++)
            hm.put(nums[i], hm.getOrDefault(nums[i], 0) + 1);


        for (int key : hm.keySet()) {
            int frequency = hm.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        int ans[] = new int[k];
        int count = 0;
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                for (int j = 0; j < bucket[i].size(); j++) {
                    if (count == k) break;
                    ans[count++] = bucket[i].get(j);
                }
            }
            if (count == k) break;
        }

        return ans;
    }

    //TC:O(N)
    public static  int[] topKFrequent1(int[] nums, int k) {
        List<List<Integer>> bucket = new ArrayList(nums.length+1);
        for(int i = 0; i< nums.length; i++){
            bucket.add(new ArrayList<>());
        }
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < nums.length; i++)
            hm.put(nums[i], hm.getOrDefault(nums[i], 0) + 1);


        for (int key : hm.keySet()) {
            int frequency = hm.get(key);
            bucket.get(frequency).add(key);
        }

        int ans[] = new int[k];
        int count = 0;
        for (int i = bucket.size() - 1; i >= 0; i--) {
            if (bucket.get(i) != null) {
                for (int j = 0; j < bucket.get(i).size(); j++) {
                    if (count == k) break;
                    ans[count++] = bucket.get(i).get(j);
                }
            }
            if (count == k) break;
        }

        return ans;
    }
//TC: O(N+Nlogk)
    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
        // find the frequency of each number
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        for (int n : nums)
            numFrequencyMap.put(n, numFrequencyMap.getOrDefault(n, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(
                        Comparator.comparingInt(Map.Entry::getValue));

        // go through all numbers of the numFrequencyMap and push them in the minHeap, which
        // will have  top k frequent numbers.
        // If the heap size is more than k, we remove the smallest (top) number
        for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // create a list of top k numbers
        List<Integer> topNumbers = new ArrayList<>(k);
        while (!minHeap.isEmpty()) {
            topNumbers.add(minHeap.poll().getKey());
        }
        return topNumbers;
    }

}
