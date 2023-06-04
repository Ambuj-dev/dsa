package patterns.modifiedbinarysearch;

public class BitonicArray {
    public static void main(String[] args) {
        System.out.println(findMaxInBitonicArray(new int[]{1,5,7,8,6,4,2}));
        System.out.println(searchInBitonicArray(new int[]{1,5,7,8,6,4,2}, 4));
        System.out.println(searchInBitonicArray(new int[]{1,5,7,8,6,4,2}, 7));
    }

    public static int findMaxInBitonicArray(int[] arr){
        int start = 0;
        int end = arr.length -1;
        while(start < end){
            int mid = start + (end-start)/2;
            //we are in the second (descending) part of the bitonic array.
            // Therefore, our required number could either be pointed out by middle or will be before middle
            if(arr[mid] > arr[mid+1]){
                end  = mid;
            //we are in the first (ascending) part of the bitonic array. Therefore, the required number will be after middle
            }else if(arr[mid] < arr[mid+1]){
                start = mid + 1;
            }
        }
        // at the end of the while loop, 'start == end'
        return arr[start];
    }
    public static int findMaxIndexInBitonicArray(int[] arr){
        int start = 0;
        int end = arr.length -1;
        while(start < end){
            int mid = start + (end-start)/2;
            if(arr[mid] > arr[mid+1]){
                end  = mid;
            }else if(arr[mid] < arr[mid+1]){
                start = mid + 1;
            }
        }
        // at the end of the while loop, 'start == end'
        return start;
    }
    public static int searchInBitonicArray(int[] arr, int key){
        int maxIndex = findMaxIndexInBitonicArray(arr);
        int keyIndex = binarySearch(arr, key, 0, maxIndex);
        if(keyIndex != -1) return keyIndex;
        return binarySearch(arr, key, maxIndex+1, arr.length - 1);
    }

    public static int binarySearch(int[] arr, int key, int start, int end){
        boolean isAscending = arr[start] < arr[end];
        while(start <= end){
            int mid = start+(end-start)/2;
            if(arr[mid] == key) return mid;
            if(isAscending) {
                if (arr[mid] < key) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }else{
                if (arr[mid] < key) {
                   end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
}
