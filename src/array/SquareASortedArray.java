package array;

public class SquareASortedArray {
    public int[] sortedSquares(int[] nums) {
        int n= nums.length;
        int left = 0;
        int right = n-1;
        int value = 0;
        int[] res = new int[n];
        for(int i=n-1; i>=0; i--){
            if(Math.abs(nums[left]) < Math.abs(nums[right])){
                value = nums[right--];
            }else{
                value = nums[left++];
            }
            res[i] = value * value;
        }
        return res;
    }
}
