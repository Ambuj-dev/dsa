package patterns.modifiedbinarysearch;

public class RotationCount {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
        System.out.println(findMinWithDuplicate(new int[]{2,2,2,0,1}));
        System.out.println(findMinWithDuplicate(new int[]{3,3,7,3}));
        System.out.println(findMin2(new int[]{3,4,5,1,2}));
        System.out.println(findMin1(new int[]{3,4,5,6,1,2}));
        System.out.println(findMin1(new int[]{11,13,15,17}));


    }

    public static int findMin1(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high){
            int mid = low + ( high - low)/2;
            if( mid > 0 && nums[mid] < nums[mid - 1]) return nums[mid];
            if(mid < high && nums[mid] > nums[mid + 1]) return nums[mid+1];
            if(nums[low] <= nums[mid] && nums[mid] > nums[high])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return nums[low];
    }
    public static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            final int mid = start+(end-start) / 2;
            if (nums[mid] > nums[end])
                start = mid + 1;
            else
                end = mid;
        }

        return nums[start];
    }
    public static int findMinWithDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            final int mid = start+(end-start) / 2;
            if (nums[mid] > nums[end])
                start = mid + 1;
            else if(nums[mid] < nums[end])
                end = mid;
            else end--;
        }

        return nums[start];
    }

    public static int findMin2(int[] nums) {

        int start = 0, end = nums.length-1;

        while(start < end){

            int  mid = (start+end) / 2;
            if(mid > 0 && nums[mid] < nums[mid-1])
                return nums[mid];
            if(nums[start] <= nums[mid] && nums[mid] > nums[end])
                start = mid + 1;
            else
                end = mid - 1;
        }
        return nums[start];
    }

}
