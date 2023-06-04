package patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LargestSubArraySum {

    static int lenOfLongSubarr(int arr[], int N, int K) {

        // Variable to store the answer
        int maxlength = 0;

        for (int i = 0; i < N; i++) {

            // Variable to store sum of subarrays
            int Sum = 0;

            for (int j = i; j < N; j++) {

                // Storing sum of subarrays
                Sum += arr[j];

                // if Sum equals K
                if (Sum == K) {

                    // Update maxLength
                    maxlength = Math.max(maxlength, j - i + 1);
                }
            }
        }

        // Return the maximum length
        return maxlength;
    }

    static int lenOfLongSubarrOptimized(int arr[], int n, int k) {
        int max = 0;
        int i = 0, j = 0, sum = 0;

        while (j < n) {
            sum += arr[j];
            if (sum < k)
                j++;
            else if (sum == k) {
                max = Math.max(max, j - i + 1);
                j++;
            } else if (sum > k) {
                while (sum > k) {
                    sum -= sum - arr[i];
                    i++;
                }
                if (sum == k) {
                    max = Math.max(max, j - i + 1);
                }
                j++;

            }
        }
        return max;
    }

    static int lenOfLongSubarrOptimized1(int arr[], int n, int k) {
        int max = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (sum == k) {
                max = i + 1;
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
            if (map.containsKey(sum - k)) {
                max = Math.max(max, i - map.get(sum - k));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // int arr[] = { 10, 5, 2, 7, 1, 9 };
        int arr[] = {-5, 8, -14, 2, 4, 12};
        int n = arr.length;
        // int k = 15;
        int k = -5;
        System.out.println(lenOfLongSubarr(arr, n, k));
        System.out.println(lenOfLongSubarrOptimized(arr, n, k));
        System.out.println(lenOfLongSubarrOptimized1(arr, n, k));
    }
}


