package patterns.dp.fibonacci;

public class MinimumJumpsToReachEnd {
    public int countMinJumps(int[] jumps) {
        return this.countMinJumpsRecursive(jumps, 0);
    }

    private int countMinJumpsRecursive(int[] jumps, int currentIndex) {
        // if we have reached the last index, we don't need any more jumps
        if( currentIndex == jumps.length - 1)
            return 0;

        if (jumps[currentIndex] == 0)
            return Integer.MAX_VALUE;

        int totalJumps = Integer.MAX_VALUE;
        int start = currentIndex + 1;
        int end = currentIndex + jumps[currentIndex];
        while(start < jumps.length && start <= end) {
            // jump one step and recurse for the remaining array
            int minJumps = countMinJumpsRecursive(jumps, start++);
            if(minJumps != Integer.MAX_VALUE)
                totalJumps = Math.min(totalJumps, minJumps + 1);
        }
        return totalJumps;
    }

    public int jump(int[] nums){
        int jump = 0;
        int pos = 0;
        int des = 0;
        for(int i =0; i< nums.length - 1; i++){
            des = Math.max(des, nums[i]+i);
            if(pos == i){
                pos = des;
                jump++;
            }
        }
        if(des < nums.length - 1) return Integer.MAX_VALUE;
        return jump;
    }
    public int countMinJumpsTabulation(int[] jumps) {
        int[] dp = new int[jumps.length];

        //initialize with infinity, except the first index which should be zero as we start from there
        for(int i=1; i<jumps.length; i++)
            dp[i] = Integer.MAX_VALUE;

        for(int start=0; start < jumps.length-1; start++) {
            for(int end=start+1; end <= start+jumps[start] && end < jumps.length; end++)
                dp[end] = Math.min(dp[end], dp[start]+1);
        }
        return dp[jumps.length-1];
    }

    public static void main(String[] args) {
        MinimumJumpsToReachEnd aj = new MinimumJumpsToReachEnd();
        int[] jumps = {2, 1, 1, 1, 4};
        System.out.println(aj.countMinJumps(jumps));
        System.out.println(aj.countMinJumpsTabulation(jumps));
        jumps = new int[]{1, 1, 3, 6, 9, 3, 0, 1, 3};
        System.out.println(aj.countMinJumps(jumps));
        System.out.println(aj.countMinJumpsTabulation(jumps));
        System.out.println(aj.jump(jumps));
        jumps = new int[]{1, 2, 1, 0, 2};
        System.out.println(aj.countMinJumps(jumps));
        System.out.println(aj.jump(jumps));
    }
}
