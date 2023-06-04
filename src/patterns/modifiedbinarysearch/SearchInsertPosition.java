package patterns.modifiedbinarysearch;

public class SearchInsertPosition {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println(searchInsert(new int[]{1, 3, 8, 10, 15, 20}, 10));
        System.out.println(searchInsert(new int[]{1, 3, 8, 10, 15, 20}, 9));
        System.out.println(searchInsert(new int[]{1, 3, 8, 10, 15, 20}, 21));
    }
    public static int searchInsert(int[] arr, int key){
        if (key < arr[0]) // if the 'key' is smaller than the smallest element
            return -1;
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid = start+(end-start)/2;
            if(arr[mid] > key){
                end = mid - 1;
            }else if(arr[mid] < key){
                start = mid+1;
            }else{
                return mid;
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop,
        // 'start == end+1' we are not able to find the element in the given array, so the
        // next smaller number will be arr[end]
        return start;
    }
}
