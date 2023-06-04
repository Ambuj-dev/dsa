package patterns.modifiedbinarysearch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumDiffElement {
    public static void main(String[] args) {
        //System.out.println(minimumAbsDifference(new int[]{1,3,5,7,20, 56}, 30));
        System.out.println(minimumAbsDifference(new int[]{1,3,5,7,20, 56}, 38));
        System.out.println(minimumAbsDifference(new int[]{1,3,5,7,20, 56}, 39));
        System.out.println(minimumAbsDifference(new int[]{1,3,5,7,20, 56}, 14));
        System.out.println(minimumAbsDifference(new int[]{1,3,5,7,20, 56}));
    }

    public static int minimumAbsDifference(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        if(key <= arr[start]) return arr[start];
        if(key >= arr[end]) return arr[end];
        while(start <= end){
            int mid = start + (end-start)/2;
            if(key > arr[mid]) {
                start = mid + 1;
            }else if(key < arr[mid]){
                end = mid - 1;
            }else{
                return arr[mid];
            }
        }
        // at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array
        // return the element which is closest to the 'key'
        if(arr[start] - key > key - arr[end]) return arr[end];
        else return arr[start];
    }

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);

        int minD = IntStream.range(1, arr.length).map(t -> arr[t] - arr[t - 1]).
                reduce(Integer::min).getAsInt();

        return IntStream.range(1 , arr.length).
                filter(t -> arr[t] - arr[t - 1] == minD).
                mapToObj(t -> Arrays.asList(arr[t - 1], arr[t])).
                collect(Collectors.toList());
    }
}
