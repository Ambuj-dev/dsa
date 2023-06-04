package patterns.slidingwindow;

// JAVA Code for Find maximum (or minimum)
// sum of a subarray of size k

public class MaxSumSubArray {

    // Returns maximum sum in a subarray of size k.
    public static int maxSum(int arr[], int n, int k) {
        // k must be smaller than n
        if (n < k) {
            System.out.println("Invalid");
            return -1;
        }

        // Compute sum of first window of size k
        int res = 0;
        for (int i = 0; i < k; i++)
            res += arr[i];

        // Compute sums of remaining windows by
        // removing first element of previous
        // window and adding last element of
        // current window.
        int curr_sum = res;
        for (int i = k; i < n; i++) {
            curr_sum += arr[i] - arr[i - k];
            res = Math.max(res, curr_sum);
        }

        return res;
    }

    public static int maxSum1(int arr[], int n, int k) {
        if (n < k) return -1;
        int windowStartPos = 0, windowCurPos = 0;
        int curr_sum = 0, res = 0;
        while (windowCurPos < n) {
            curr_sum = curr_sum + arr[windowCurPos];
            if ((windowCurPos - windowStartPos + 1) < k) {
                windowCurPos++;
            } else if (windowCurPos - windowStartPos + 1 == k) {
                res = Math.max(res, curr_sum);
                curr_sum -= arr[windowStartPos];
                windowStartPos++;
                windowCurPos++;

            }
        }
        return res;
    }



    /* Driver program to test above function */
    public static void main(String[] args) {
        int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 4;
        int n = arr.length;
        System.out.println(maxSum(arr, n, k));
    }
}
