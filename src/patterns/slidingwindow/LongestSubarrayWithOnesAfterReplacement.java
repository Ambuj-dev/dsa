package patterns.slidingwindow;

public class LongestSubarrayWithOnesAfterReplacement {

    public static int longestOnes(int[] nums, int k) {
        int ans = k;
        int sum = 0;
        int n = nums.length;
        for(int fast=0,slow=0;fast<n;fast++){
            if(nums[fast] == 0){
                sum++;
            }
            while(sum > k && slow <= fast){
                if(nums[slow] == 0){
                    sum--;
                }
                slow++;
            }
            ans = Math.max(ans, fast-slow+1);
        }

        return ans;

    }

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,0,0,0,0,0,0,1,1,0}, 3));
    }
}
