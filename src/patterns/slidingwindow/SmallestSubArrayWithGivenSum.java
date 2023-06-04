package patterns.slidingwindow;

public class SmallestSubArrayWithGivenSum {
    public static int minSubArrayLen(int target, int[] nums) {

        if(nums == null || nums.length == 0)
            return 0;

        int start =0,end =0,sum =0, min = Integer.MAX_VALUE;

        while(end < nums.length)
        {
            sum += nums[end++];

            while(sum >= target)
            {
                if(sum == target)
                    min = Math.min(min , end-start);
                sum = sum - nums[start++];
            }
        }

        if(min != Integer.MAX_VALUE)
            return min;
        else
            return 0;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(minSubArrayLen(4, new int[]{1,4,4}));
        System.out.println(minSubArrayLen(7, new int[]{1,1,1,1,1,1}));
        System.out.println(minSubArrayLen(7, new int[]{1,1,1,1,1,1,1}));
    }
}
