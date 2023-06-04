package patterns.topkelement;

import java.util.*;
import java.util.stream.Collectors;

public class KClosestElement {
    public static void main(String[] args) {
        KClosestElement kClosestElement = new KClosestElement();
        System.out.println(kClosestElement.findClosestElements(new int[]{1,2,3,4,5}, 4, 3));
        System.out.println(kClosestElement.findClosestElements(new int[]{1,2,3,4,5}, 4, -1));
        System.out.println(kClosestElement.findClosestElements(new int[]{1,3}, 1, 2));
        System.out.println(kClosestElement.findClosestElements(new int[]{1,5,10}, 1, 4));

        System.out.println(kClosestElement.findClosestElementsLinearSearch(new int[]{1,2,3,4,5}, 4, 3));
        System.out.println(kClosestElement.findClosestElementsLinearSearch(new int[]{1,2,3,4,5}, 4, -1));
        System.out.println(kClosestElement.findClosestElementsLinearSearch(new int[]{1,3}, 1, 2));
        System.out.println(kClosestElement.findClosestElementsLinearSearch(new int[]{1,5,10}, 1, 4));

        System.out.println(kClosestElement.findClosestElementsBinarySearch(new int[]{1,2,3,4,5}, 4, 3));
        System.out.println(kClosestElement.findClosestElementsBinarySearch(new int[]{1,2,3,4,5}, 4, -1));
        System.out.println(kClosestElement.findClosestElementsBinarySearch(new int[]{1,3}, 1, 2));
        System.out.println(kClosestElement.findClosestElementsBinarySearch(new int[]{1,5,10}, 1, 4));
        System.out.println(kClosestElement.findClosestElementsBinarySearch(new int[]{1,2,3,4,5}, 2, 3));

        List<Integer> result =
                KClosestElement.findClosestElements2Pointer(new int[] { 5, 6, 7, 8, 9 }, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result =
                KClosestElement.findClosestElements2Pointer(new int[] { 2, 4, 5, 6, 9 }, 3, 6);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result =
                KClosestElement.findClosestElements2Pointer(new int[] { 2, 4, 5, 6, 9 }, 3, 1);
        System.out.println("'K' closest numbers to 'X' are: " + result);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> res = new LinkedList<>();
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while(low <= high){
            mid = low+(high-low)/2;
            if(arr[mid] == x) {
                break;
            }else if(arr[mid] > x){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        int left,right;
        if(mid > 0) {
            left = mid - 1;
            right = mid;
        }else{
            left = mid;
            right = mid+1;
        }
        while(left >= 0 && right <= arr.length - 1 && res.size() < k){
                if((x - arr[left]) <= (arr[right] - x)){
                    res.addFirst(arr[left--]);
                }else{
                    res.addLast(arr[right++]);
                }
        }
        while(res.size() < k && left >= 0){
            res.addFirst(arr[left--]);
        }
        while(res.size() < k && right <= arr.length -1){
            res.addLast(arr[right++]);
        }

        return res;
    }
    public List<Integer> findClosestElementsLinearSearch(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int left = 0; int right = arr.length - 1;
        while(right - left >= k){
            if(x- arr[left] <= arr[right] - x){
                right--;
            }else{
                left++;
            }
        }
        for(int i = left; i<=right; i++){
            ans.add(arr[i]);
        }
        return ans;
    }

    public List<Integer> findClosestElementsBinarySearch(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = left+(right-left) / 2;
            if (x - arr[mid] <= arr[mid + k] - x)
                right = mid;
            else
                left = mid + 1;
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = left; i<left+k; i++){
            ans.add(arr[i]);
        }
        return ans;
        //return Arrays.stream(arr, left, left + k).boxed().collect(Collectors.toList());
    }
    // TC: is O(logN+K∗logK). We need O(logN) for Binary Search and O(K∗logK) to insert the numbers in the Min Heap, as well as to sort the output array
    public static List<Integer> findClosestElementsHeap(int[] arr, int K, Integer X) {
        int index = binarySearch(arr, X);
        int low = index - K, high = index + K;
        low = Math.max(low, 0); // 'low' should not be less than zero
        // 'high' should not be greater the size of the array
        high = Math.min(high, arr.length - 1);

        PriorityQueue<Entry> minHeap = new PriorityQueue<>((n1, n2) -> n1.key- n2.key);
        // add all candidate elements to the min heap, sorted by their absolute difference
        // from 'X'
        for (int i = low; i <= high; i++)
            minHeap.add(new Entry(Math.abs(arr[i] - X), i));

        // we need the top 'K' elements having smallest difference from 'X'
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < K; i++)
            result.add(arr[minHeap.poll().value]);

        Collections.sort(result);
        return result;
    }
    //TC:  O(logN + K). We need O(logN) for Binary Search and O(K)for finding the K closest numbers using the two pointers.
    public static List<Integer> findClosestElements2Pointer(int[] arr, int K, Integer X) {
        List<Integer> result = new LinkedList<>();
        int index = binarySearch(arr, X);
        int leftPointer = index;
        int rightPointer = index + 1;
        for (int i = 0; i < K; i++) {
            if (leftPointer >= 0 && rightPointer < arr.length) {
                int diff1 = Math.abs(X - arr[leftPointer]);
                int diff2 = Math.abs(X - arr[rightPointer]);
                if (diff1 <= diff2)
                    result.add(0, arr[leftPointer--]); // append in the beginning
                else
                    result.add(arr[rightPointer++]); // append at the end
            } else if (leftPointer >= 0) {
                result.add(0, arr[leftPointer--]);
            } else if (rightPointer < arr.length) {
                result.add(arr[rightPointer++]);
            }
        }
        return result;
    }

    private static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (low > 0) {
            return low - 1;
        }
        return low;
    }

}
class Entry {
    int key;
    int value;

    public Entry(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
