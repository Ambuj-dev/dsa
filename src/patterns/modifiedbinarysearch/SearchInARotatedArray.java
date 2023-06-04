package patterns.modifiedbinarysearch;

public class SearchInARotatedArray {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3));
        //System.out.println(searchWithDuplicates(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(searchWithDuplicates(new int[]{4,5,6,7,0,1,2}, 3));
        System.out.println(searchWithDuplicates(new int[]{2,2,4,4,5,6,7,0,1,2,2}, 0));
        System.out.println(searchWithDuplicates(new int[]{4,4,4,4,5,6,7,0,1,2,2,4}, 3));
        System.out.println(searchWithDuplicates(new int[]{4,4,4,4,1,2,2,4}, 2));

    }
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = start +(end-start)/2;
            if(nums[mid] == target) return mid;
            // the left side is sorted
            if(nums[start]<= nums[mid]){
                //If the element lies in left half
                if(target >= nums[start] && target < nums[mid]){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            // the right side is sorted
            else{
                if(target > nums[mid] && target <= nums[end]){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int searchWithDuplicates(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = start +(end-start)/2;
            if(nums[mid] == target) return mid;

            // the only difference from the previous solution,
            // if numbers at indexes start, mid, and end are same, we can't choose a side
            // the best we can do, is to skip one number from both ends as key !== arr[mid]
            if(start != end && nums[start] == nums[mid] && nums[end] == nums[mid]){
                start += 1;
                end -= 1;
            }
            // the left side is sorted
            if(nums[start]<= nums[mid]){
                //If the element lies in left half
                if(target >= nums[start] && target < nums[mid]){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            // the right side is sorted
            else{
                if(target > nums[mid] && target <= nums[end]){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
