package array;

public class SingleNumber {

    public int singleNumber(int[] nums) {
        int ans = 0;

        for(int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }

        return ans;
    }
    public static void main(String[] args){
        System.out.println(new SingleNumber().singleNumber(new int[]{4,1,2,1,2}));
    }
}
