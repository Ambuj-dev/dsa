package patterns.slidingwindow;

import java.util.*;
/*
* Given an array and a positive integer k, find the first negative integer for each window(contiguous subarray) of size k. If a window does not contain a negative integer, then print 0 for that window.
Examples:
Input : arr[] = {-8, 2, 3, -6, 10}, k = 2
Output : -8 0 -6 -6

First negative integer for each window of size k
{-8, 2} = -8
{2, 3} = 0 (does not contain a negative integer)
{3, -6} = -6
{-6, 10} = -6

Input : arr[] = {12, -1, -7, 8, -15, 30, 16, 28} , k = 3
Output : -1 -1 -7 -15 -15 0

* */
public class FirstNegativeInteger {
    static void printFirstNegativeInteger(int arr[], int n, int k)
    {
        // flag to check whether window contains
        // a negative integer or not
        boolean flag;

        // Loop for each subarray(window) of size k
        for (int i = 0; i<(n-k+1); i++)
        {
            flag = false;

            // traverse through the current window
            for (int j = 0; j<k; j++)
            {
                // if a negative integer is found, then
                // it is the first negative integer for
                // current window. Print it, set the flag
                // and break
                if (arr[i+j] < 0)
                {
                    System.out.print((arr[i+j])+" ");
                    flag = true;
                    break;
                }
            }

            // if the current window does not
            // contain a negative integer
            if (!flag)
                System.out.print("0"+" ");
        }
    }
    static void printFirstNegativeIntegerOptimized(int arr[], int n, int k)
    {
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<k; i++){
            if(arr[i]<0){
                queue.add(i);
            }
        }
        for(int i=k; i<n; i++){

            if(queue.size() != 0){
                System.out.print(arr[queue.peek()]+" ");
                while(!queue.isEmpty() && (i-k+1)>queue.peek()){
                    queue.remove();
                }
            }
            else
                System.out.print("0"+" ");
            if(arr[i]<0){
                queue.add(i);
            }

        }
        if(queue.isEmpty())
            System.out.print("0 ");
        else
            System.out.print(queue.peek()+" ");
    }
    static void printFirstNegativeIntegerOptimized1(int arr[],
                                          int n, int k)
    {
        int firstNegativeIndex = 0;
        int firstNegativeElement;

        for(int i = k - 1; i < n; i++)
        {

            // Skip out of window and positive elements
            while ((firstNegativeIndex < i ) &&
                    (firstNegativeIndex <= i - k ||
                            arr[firstNegativeIndex] >= 0))
            {
                firstNegativeIndex ++;
            }

            // Check if a negative element is
            // found, otherwise use 0
            if (arr[firstNegativeIndex] < 0)
            {
                firstNegativeElement = arr[firstNegativeIndex];
            }
            else
            {
                firstNegativeElement = 0;
            }
            System.out.print(firstNegativeElement + " ");
        }
    }

    public static void printFirstNegativeIntegerOptimized2(int arr[], int n, int k) {
        if (n < k) return;
        int windowStartPos = 0, windowCurPos = 0;
        Queue<Integer> queue = new LinkedList<>();
        while (windowCurPos < n) {

            if ((windowCurPos - windowStartPos + 1) < k) {
               if(arr[windowCurPos] < 0){
                   queue.add(arr[windowCurPos]);
               }
                windowCurPos++;
            } else if (windowCurPos - windowStartPos + 1 == k) {
                if(!queue.isEmpty()) {
                    System.out.print(queue.peek()+" ");
                    if(queue.peek() == arr[windowStartPos]){
                        queue.remove();
                    }
                }else{
                    System.out.print("0"+" ");
                }
                if(arr[windowCurPos] < 0){
                    queue.add(arr[windowCurPos]);
                }
                windowStartPos++;
                windowCurPos++;
            }

        }
    }

    public static void printFirstNegativeIntegerOptimized3(int arr[], int n, int k) {
        if (n < k) return;
        int windowStart = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int windowEnd = 0; windowEnd < n; windowEnd++) {
            if (arr[windowEnd] < 0) queue.add(arr[windowEnd]);
            if (windowEnd - windowStart + 1 == k) {
                if (!queue.isEmpty()) {
                    System.out.print(queue.peek() + " ");
                    if (queue.peek() == arr[windowStart]) {
                        queue.remove();
                    }
                } else {
                    System.out.print("0" + " ");
                }

                windowStart++;
            }

        }
    }

    // Driver program to test above functions
    public static void main(String args[])
    {
        int arr[] = {-12, -1, -7, 8, -15, 30, 16, 28};
        int n = arr.length;
        int k = 3;

        printFirstNegativeInteger(arr, n, k);
        System.out.println("Naive");

        printFirstNegativeIntegerOptimized(arr, n, k);
        System.out.println("Optimized");

        printFirstNegativeIntegerOptimized1(arr, n, k);
        System.out.println("Optimized 1");
        printFirstNegativeIntegerOptimized2(arr, n, k);
        System.out.println("Optimized 2");
        printFirstNegativeIntegerOptimized3(arr, n, k);
        System.out.println("Optimized 3");
    }
}