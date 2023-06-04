package patterns.modifiedbinarysearch;

public class OrderAgnosticBinarySearch {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 6, 10}, 10));
        System.out.println(search(new int[]{1,2,3,4,5,6,7}, 5));
        System.out.println(search(new int[]{4, 6, 10}, 11));
        System.out.println(search(new int[]{10, 6, 4}, 10));
    }

    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        boolean isAscending = nums[start] < nums[end];

        while(start <= end){
            // calculate the middle of the current range
            int middle = start + (end-start)/2;
            if(target == nums[middle]) return middle;
            if(isAscending){
                if(target > nums[middle]){
                    start = middle + 1;
                }else{
                    end = middle - 1;
                }
            }else{
                if(target > nums[middle]){
                    end = middle -1;
                }else{
                    start = middle + 1;
                }
            }
        }
        return -1;
    }
}
