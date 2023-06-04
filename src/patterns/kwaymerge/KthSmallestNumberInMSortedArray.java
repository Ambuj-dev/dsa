package patterns.kwaymerge;

import java.util.PriorityQueue;

public class KthSmallestNumberInMSortedArray {

    // This function takes an array of arrays as an
// argument and all arrays are assumed to be
// sorted. It returns m-th smallest element in
// the array obtained after merging the given
// arrays.
        public static int mThSmallest(int[][] arr, int m) {

            // Create a min heap. Every
            // heap node has first element of an array
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.value - b.value);
            for (int i = 0; i < arr.length; i++) {
                pq.add(new Pair(arr[i][0], i, 0));
            }

            // Now one by one get the minimum element
            // from min heap and replace it with next
            // element of its array
            int count = 0;
            int arrayNumber=0;
            int index=0;
            while (count < m && !pq.isEmpty()) {
                Pair curr = pq.poll();

                // i ==> Array Number
                // j ==> Index in the array number
                arrayNumber = curr.arrayNumber;
                index = curr.index;

                // The next element belongs to same array as current.
                if (index + 1 < arr[arrayNumber].length) {
                    pq.add(new Pair(arr[arrayNumber][index + 1], arrayNumber, index + 1));
                }
                count++;
            }
            return arr[arrayNumber][index];
        }

        // Driver Code
        public static void main(String[] args) {
            int[][] arr = { { 2, 6, 12 }, { 1, 9 }, { 23, 34, 90, 2000 } };
            int m = 4;
            System.out.println(mThSmallest(arr, m));
        }
    }

    //Class to store array number and index
    class Pair {
        int value;
        int arrayNumber;
        int index;

        // Constructor
        public Pair(int v, int i, int j) {
            value = v;
            arrayNumber = i;
            index = j;
        }
    }
