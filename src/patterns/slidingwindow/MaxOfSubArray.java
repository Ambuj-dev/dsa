package patterns.slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

public class MaxOfSubArray {

    // Method to find the maximum for
    // each and every contiguous
    // subarray of size K.
    static void printKMax(int arr[], int N, int K) {
        int j, max;

        for (int i = 0; i <= N - K; i++) {

            max = arr[i];

            for (j = 1; j < K; j++) {
                if (arr[i + j] > max)
                    max = arr[i + j];
            }
            System.out.print(max + " ");
        }
    }

    static void printKMaxOptimized(int arr[], int N, int K) {
        Deque<Integer> deque = new LinkedList<Integer>();
        int i = 0;
        int j = 0;
        while (j < N) {
            // For every element, the previous
            // smaller elements are useless so
            // remove them from deque
            while (!deque.isEmpty()
                    && arr[j] >= deque.peekLast())
                // Remove from rear
                deque.removeLast();
            // Add new element at rear of queue
            deque.addLast(arr[j]);
            if (j - i + 1 < K) {
                j++;
            } else if (j - i + 1 == K) {
                System.out.print(deque.peekFirst() + " ");
                // Remove the elements which
                // are out of this window
                if (deque.peekFirst() == arr[i]) {
                    deque.removeFirst();
                }
                i++;
                j++;
            }

        }

    }
    static void printKMaxOptimized1(int arr[], int N, int K) {
        LinkedList<Integer> list = new LinkedList<>();
        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < N; windowEnd++) {
            // For every element, the previous
            // smaller elements are useless so
            // remove them from deque
            while (!list.isEmpty()
                    && arr[windowEnd] >= list.peekLast())
                // Remove from rear
                list.removeLast();
            // Add new element at rear of queue
            list.addLast(arr[windowEnd]);
            if (windowEnd - windowStart + 1 == K) {
                System.out.print(list.peekFirst() + " ");
                // Remove the elements which
                // are out of this window
                if (list.peekFirst() == arr[windowStart]) {
                    list.removeFirst();
                }
                windowStart++;
            }

        }

    }


    // Driver's code
    public static void main(String args[]) {
        //int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int arr[] = {12, 1, 78, 90, 57, 89, 56};
        int K = 3;

        // Function call
        printKMax(arr, arr.length, K);
        System.out.println("");
        printKMaxOptimized(arr, arr.length, K);
        System.out.println("");
        printKMaxOptimized1(arr, arr.length, K);
    }
}

// This code is contributed by Sumit Ghosh

