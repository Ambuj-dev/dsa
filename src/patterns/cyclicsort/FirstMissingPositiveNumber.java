package patterns.cyclicsort;

import java.util.ArrayList;
import java.util.List;

public class FirstMissingPositiveNumber {
    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1}));
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));
        System.out.println(firstMissingPositive(new int[]{7,8,9,11,12}));
        for(int x: firstKMissingPositive(new int[]{3, -1, 4, 5, 5}, 3))
            System.out.println(x);
        for(int x : firstKMissingPositive(new int[]{2,3,4}, 3))
             System.out.println(x);
        for(int x: firstKMissingPositive(new int[]{-2, -3, 4}, 2))
             System.out.println(x);
        for(int x: firstKMissingPositive(new int[]{2,1,3,6,5}, 2))
            System.out.println(x);
    }

    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        int n = nums.length;
        while(i<n){
            if(nums[i] <= 0) {
                i++;
                continue;
            }
            int j = nums[i] - 1;
            if(nums[i]<=n && nums[i] != nums[j] && nums[i]>0 ){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }else{
                i++;
            }
        }
        for(i = 0; i<n; i++){
            if(nums[i] != (i+1)){
                return i+1;
            }
        }
        return n+1;
    }

    public static int[] firstKMissingPositive(int[] nums, int k) {
        int i = 0;
        int n = nums.length;
        while(i<n){
            if(nums[i] <= 0) {
                i++;
                continue;
            }
            int j = nums[i] - 1;
            if(nums[i]<=n && nums[i] != nums[j] && nums[i]>0 ){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }else{
                i++;
            }
        }
        List<Integer> missingNumbers = new ArrayList<>();
        List<Integer> extraNumbers = new ArrayList<>();

        for(i = 0; i<n; i++){
            if(missingNumbers.size() < k) {
                if (nums[i] != (i + 1)) {
                    missingNumbers.add( i + 1);
                    extraNumbers.add(nums[i]);
                }
            }
        }
        int j = 1;
        while(missingNumbers.size()<k){
            int curNum = j+n;
            if(!extraNumbers.contains(curNum)){
                missingNumbers.add(curNum);
            }
            j++;
        }
        return missingNumbers.stream().mapToInt(Integer::intValue).toArray();
    }
    public int findKthPositive(int[] arr, int k) {
        int low=0;
        int high=arr.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]-(mid+1)>=k)
                high=mid-1;
            else
                low=mid+1;
        }
        return low+k;
    }
}
