package patterns.twoheaps;

import java.util.*;

public class FindMaximizedCapital {
    public static void main(String[] args) {
        //System.out.println(findMaximizedCapitalOptimized(2, 0, new int[]{1,2,3}, new int[]{0,1,1}));
        System.out.println(findMaximizedCapital(10, 0, new int[]{1,2,3}, new int[]{0,1,2}));
        System.out.println(findMaximizedCapitalOptimized(10, 0, new int[]{1,2,3}, new int[]{0,1,2}));
    }
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        // Create a list of pairs (capital, profit) for all n projects
        List<int[]> projects = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            projects.add(new int[]{capital[i], profits[i]});
        }

        // Sort the list of projects by their minimum capital requirements in ascending order
        projects.sort(Comparator.comparingInt(a -> a[0]));

        // Initialize a priority queue to store the profits of the available projects
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Initialize a variable i to 0 to keep track of the current project
        int i = 0;

        // Iterate over k projects
        while (k > 0) {
            // While i is less than n and the minimum capital requirement of the current project is less than or equal to w
            while (i < n && projects.get(i)[0] <= w) {
                // Add the profit of the current project to the priority queue
                pq.offer(projects.get(i)[1]);
                i++;
            }

            // If the priority queue is empty, break out of the loop because we can't afford any more projects
            if (pq.isEmpty()) {
                break;
            }

            // Select the project with the highest profit from the priority queue and add its profit to w
            w += pq.poll();
            k--;
        }

        return w;
    }
    public static int findMaximizedCapitalOptimized(int k, int w, int[] profits, int[] capital) {
        Queue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.capital));
        Queue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.profit - a.profit);

        for (int i = 0; i < capital.length; ++i)
            minHeap.offer(new Pair(profits[i], capital[i]));

        while (k-- > 0) {
            while (!minHeap.isEmpty() && minHeap.peek().capital <= w)
                maxHeap.offer(minHeap.poll());
            if (maxHeap.isEmpty())
                break;
            w += maxHeap.poll().profit;
        }

        return w;
    }

    static class Pair{
        public int profit;
        public int capital;
        public Pair(int profit, int capital){
            this.profit = profit;
            this.capital = capital;
        }
    }

}
